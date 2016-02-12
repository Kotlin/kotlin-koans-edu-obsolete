package koans.converter

import java.io.File
import java.util.*

fun convertTasks(parentDir: File, course: Course, filesMap: FilesMap, links: Properties) {
    copyUtilFile(parentDir, course, filesMap)

    for ((lessonIndex, lesson) in course.lessons.withIndex()) {
        val lessonDirName = "$LESSON_DIR${lessonIndex + 1}"
        val lessonDir = parentDir.subFile(lessonDirName)
        lessonDir.mkdir()

        for ((taskIndex, task) in lesson.task_list.withIndex()) {
            val taskDirName = "$TASK_DIR${taskIndex + 1}"
            val taskDir = lessonDir.subFile(taskDirName)
            taskDir.mkdir()

            fun copyFileTaskAndTransform(oldFile: File, newFileName: String, transform: String.() -> String) {
                val newFile = taskDir.subFile(newFileName)
                copyFileTaskAndTransform(oldFile, newFile, transform)
            }

            fun String.replaceImports() = replaceImports(Mode.EDUCATIONAL_PLUGIN, taskDirName)

            val taskDirectory = filesMap[task]
            val packageName = "$taskDirName"

            val taskInMD = taskDirectory.subFile(TASK_EE_MD).let { if (it.exists()) it else taskDirectory.subFile(TASK_MD) }
            copyFileTaskAndTransform(taskInMD, TASK_HTML) { convertMarkdownToHtml(this, links) }

            copyFileTaskAndTransform(taskDirectory.subFile(TEST_KT), TESTS_KT) {
                addPackageNameAndImportForTests(TEST_KT, packageName).replaceImports()
            }

            for ((name, taskFile) in task.task_files) {

                copyFileTaskAndTransform(filesMap[taskFile], filesMap[taskFile].name.transformName(Mode.EDUCATIONAL_PLUGIN)) {
                    (if (taskFile.placeholders.isEmpty()) this else removeTaskWindowTags())
                            .addPackageName(taskFile.name, packageName)
                            .replaceImports()
                }
            }
        }
    }
}

private fun copyUtilFile(parentDir: File, course: Course, filesMap: FilesMap) {
    val utilFile = filesMap[course].subFile(UTIL_FILE)
    val newUtilDir = parentDir.subFile(NEW_UTIL_DIR)
    newUtilDir.mkdir()
    val newUtilFile = newUtilDir.subFile(UTIL_FILE)
    copyFileTaskAndTransform(utilFile, newUtilFile, { transformUtilFile(Mode.EDUCATIONAL_PLUGIN) })
}

private fun copyFileTaskAndTransform(oldFile: File, newFile: File, transform: String.() -> String = { this }) {
    newFile.writeText(oldFile.readText().transform())
}