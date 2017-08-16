package koans.converter

import koans.converter.Settings.CONVERTED_DIR_CG
import java.io.*
import java.util.*

fun convertCG(koansDir: File) {
    val links = readLinks()
    val outputFolder = newDir(CONVERTED_DIR_CG)
    convertForCodinGame(outputFolder, koansDir, links)
}

val failing = listOf(
        "kotlin-koans-0-3",
        "kotlin-koans-0-6",
        "kotlin-koans-0-10",
        "kotlin-koans-1-0",
        "kotlin-koans-1-1",
        "kotlin-koans-1-2",
        "kotlin-koans-1-3",
        "kotlin-koans-1-5",
        "kotlin-koans-2-11",
        "kotlin-koans-3-2",
        "kotlin-koans-4-1",
        "kotlin-koans-5-0")

fun convertForCodinGame(outputFolder: File, koansDir: File, links: Properties) {
    val filesMap = FilesMap()
    val linksMap = links.stringPropertyNames().associate { Pair("]($it)", "](${links.getProperty(it)})") }
    val course = readCourse(koansDir, filesMap)
    val ymlFile = outputFolder.subFile("techio.yml")

    val projects = mutableListOf<Pair<String, String>>()
    ymlFile.printWriter().use { yml ->
        yml.println("title: Kotlin Koans")
        yml.println("plan:")
        course.lessons.forEachIndexed { lessonIndex, lesson ->
            val lessonOutputFolder by lazy {
                yml.println("  - title: ${lesson.title}")
                yml.println("    plan:")
                outputFolder.subFile(lesson.title).also { it.mkdir() }
            }

            val subProjects = mutableListOf<String>()
            lesson.task_list.forEachIndexed task@ { taskIndex, task ->
                val taskInputFolder = filesMap[task]
                val taskInputFile = taskInputFolder.subFile("task.md").takeIf { it.exists() }
                        ?: taskInputFolder.subFile("task_cg.md").takeIf { it.exists() }
                        ?: return@task

                val projectId = "kotlin-koans-$lessonIndex-$taskIndex"
                if (projectId in failing)
                    return@task

                projects.add(projectId to "${lesson.title}/${task.name}")
                subProjects.add(task.name)

                val taskOutputFolder = lessonOutputFolder.subFile(task.name)
                val taskProjectSrcFolder = taskOutputFolder.subFile("src")
                val taskProjectTestFolder = taskOutputFolder.subFile("test")
                taskOutputFolder.mkdirs()
                taskProjectSrcFolder.mkdirs()
                taskProjectTestFolder.mkdirs()

// This block would be needed if CG would have been supporting parent/child pom.xml
// For now we just copy separate self-contained pom.xml into the folder
/*
                val taskMaven = koansDir.canonicalFile.parentFile.subFile("files/codingame/task_pom.xml")
                val mavenText = taskMaven.readText()
                        .replace("{LESSONINDEX}", lessonIndex.toString())
                        .replace("{TASKINDEX}", taskIndex.toString())
                taskOutputFolder.subFile("pom.xml").writeText(mavenText)
*/
                val mavenFile = koansDir.canonicalFile.parentFile.subFile("files/codingame/template_pom.xml")
                val mavenText = mavenFile.readText()
                        .replace("<artifactId>kotlin-koans</artifactId>", "<artifactId>$projectId</artifactId>")
                taskOutputFolder.subFile("pom.xml").writeText(mavenText)

                task.task_files.values.forEach { taskFile ->
                    val inputFile = filesMap[taskFile]
                    val taskText = inputFile.readText().removeTaskWindowTags().removePackageDeclarations()
                    taskProjectSrcFolder.subFile(taskFile.name).writeText(taskText)
                }

                val testText = taskInputFolder.subFile("Test.kt").readText().removePackageDeclarations()
                val testClassName = testText.lines().first { "class " in it }.substringAfter("class").substringBefore("{").trim(' ', '(', ')')
                val testFunName = testText.lines().first { "@Test" in it }.substringAfter("@Test fun ").substringBefore("()")
                taskProjectTestFolder.subFile("Test.kt").writeText(testText)
                koansDir.subFile("koansTestUtil.kt").copyTo(taskProjectTestFolder.subFile("koansTestUtil.kt"))

                val sourceText = taskInputFile.readText()

                // @[Return Hello World!]({"project": "name", "stubs": ["src/main/kotlin/Hello.kt"], "command": "hello.tests.HelloTest#testAssert"})
                val transformedText = sourceText.replaceLinks(linksMap) + "\n\n" +
                        """@[Task]({"project": "kotlin-koans", "stubs": ["${taskProjectSrcFolder.relativeTo(outputFolder)}/Task.kt"], "command": "\"${taskOutputFolder.relativeTo(outputFolder)}\" $testClassName"})"""

                taskOutputFolder.subFile("task.md").writeText(transformedText)
                val relativeFolder = taskOutputFolder.relativeTo(outputFolder)
                yml.println("    - title: ${task.name}")
                yml.println("      statement: $relativeFolder/task.md")
            }

// Don't create lesson poms, no parent/child support
/*
            val modulesText = subProjects.map { "        <module>$it</module>" }.joinToString("\n", "<modules>\n", "\n    </modules>")
            val lessonMaven = koansDir.canonicalFile.parentFile.subFile("files/codingame/lesson_pom.xml")
            val mavenText = lessonMaven.readText().replace("<modules/>", modulesText).replace("{LESSONINDEX}", lessonIndex.toString())
            lessonOutputFolder.subFile("pom.xml").writeText(mavenText)
*/
        }

        yml.println("projects:")
        yml.println("  kotlin-koans:")
        yml.println("    root: /")
        yml.println("    runner: techio/kotlin-maven3-junit4-runner:latest")
    }

// Don't generate root pom.xml
/*
    val modulesText = course.lessons.map { "        <module>${it.title}</module>" }.joinToString("\n", "<modules>\n", "\n    </modules>")
    val mavenFile = koansDir.canonicalFile.parentFile.subFile("files/codingame/course_pom.xml")
    val mavenText = mavenFile.readText().replace("<modules/>", modulesText)
    outputFolder.subFile("pom.xml").writeText(mavenText)
*/

    println("Done")
}
