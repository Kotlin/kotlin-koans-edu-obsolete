package koans.converter

import koans.converter.Settings.CONVERTED_DIR_WB
import koans.converter.Settings.SOURCE_DIR
import java.io.File
import java.util.*

fun convertForWebDemo(sourceDir: File, targetDir: File, links: Properties) {

    val linksMap = links.stringPropertyNames().associate { Pair("]($it)", "](${links.getProperty(it)})") }
    copyFolderAndTransformFiles(sourceDir, targetDir) {
        fileName, fileText ->
        when (fileName) {
            "koansTestUtil.kt" -> fileText.transformUtilFile(Mode.WEB_DEMO)
            "Solution.kt" -> fileText.uncommentTags().removeTaskWindowTags().removePackageDeclarations()
            "Task.kt" -> fileText.removeTaskWindowTagsWithWhitespaces()
            "task.md" -> fileText.replaceLinks(linksMap)
            else -> if (fileName.isSourceCodeFileName()) fileText.removePackageDeclarations() else fileText
        }
    }
    generateTasksIfNecessary(sourceDir)
}

fun String.replaceLinks(links: Map<String, String>): String {
    val linksInThisString = links.filterKeys { this.contains(it) }.keys
    return linksInThisString.fold(this) { text, link -> text.replace(link, links[link]!!) }
}

fun copyFolderAndTransformFiles(from: File, to: File, transform: (String, String) -> String) {

    if (from.isDirectory && !to.exists()) to.mkdir()

    from.listFiles().forEach {
        file ->
        val fileName = file.name.transformName(Mode.WEB_DEMO)
        when {
            file.shouldNotBeCopied() -> return@forEach
            file.isDirectory -> copyFolderAndTransformFiles(file, to.subFile(fileName), transform)
            else -> to.subFile(fileName).writeText(transform(fileName, file.readText()))
        }
    }
}

fun File.shouldNotBeCopied() = name == LINKS_PROPERTIES || name.contains("-ee")

fun generateTasksIfNecessary(sourceDir: File) {
    if (!sourceDir.isDirectory) return
    val files = sourceDir.listFiles()
    if (files.none { it.name == SOLUTION_KT }) {
        files.forEach(::generateTasksIfNecessary)
        return
    }

    val taskFile = getCorrespondingTargetDir(sourceDir).subFile(TASK_KT)
    if (taskFile.exists()) return

    val sourceSolution: File = sourceDir.subFile(SOLUTION_KT)
    taskFile.writeText(sourceSolution.readText().removePackageDeclarations().uncommentTags().removeSolutions())
}

fun getCorrespondingTargetDir(sourceTaskDir: File): File {
    val targetPath = File(CONVERTED_DIR_WB).path + sourceTaskDir.path.substringAfter(File(SOURCE_DIR).path)
    val file = File(targetPath)
    if (!file.exists() || !file.isDirectory) {
        throw IllegalArgumentException("Can't find the corresponding target path: ${sourceTaskDir.path}")
    }
    return file
}