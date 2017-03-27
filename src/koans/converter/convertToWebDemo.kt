package koans.converter

import java.io.File
import java.util.*

fun convertForWebDemo(parentDir: File, koansDir: File, links: Properties) {

    val linksMap = links.stringPropertyNames().associate { Pair("]($it)", "](${links.getProperty(it)})") }
    copyFolderAndTransformFiles(koansDir, parentDir) {
        fileName, fileText ->
        when (fileName) {
            "koansTestUtil.kt" -> fileText.transformUtilFile(Mode.WEB_DEMO)
            "Solution.kt" -> fileText.uncommentTags().removeTaskWindowTags().removePackageDeclarations()
            "Task.kt" -> fileText.removeTaskWindowTagsWithWhitespaces()
            "task.md" -> fileText.replaceLinks(linksMap)
            else -> if (fileName.isSourceCodeFileName()) fileText.removePackageDeclarations() else fileText
        }
    }
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