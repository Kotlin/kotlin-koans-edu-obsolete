package koans.converter

import java.io.File
import java.util.*

fun convertForWebDemo(parentDir: File, koansDir: File, links: Properties) {

    val linksMap = links.stringPropertyNames().toMap { Pair("($it)", "(${links.getProperty(it)})") }
    copyFolderAndTransformFiles(koansDir, parentDir) {
        fileName, fileText ->
        when (fileName) {
            "Solution.kt" -> fileText.removeTaskWindowTags()
            "Task.kt" -> fileText.removeTaskWindowTagsWithWhitespaces()
            "task.md" -> fileText.replaceLinks(linksMap)
            else -> fileText
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
        when {
            file.shouldNotBeCopied() -> return@forEach
            file.isDirectory -> copyFolderAndTransformFiles(file, to.subFile(file.name), transform)
            else -> to.subFile(file.name).writeText(transform(file.name, file.readText()))
        }
    }
}

fun File.shouldNotBeCopied() = name == LINKS_PROPERTIES