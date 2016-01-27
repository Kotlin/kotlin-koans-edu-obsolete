package koans.converter

import java.util.*

data class TextRange(val line: Int, val start: Int, val length: Int)

fun String.getTaskWindowsFromText(): List<TextRange> {
    val textRanges = ArrayList<TextRange>()
    for ((index, initialLine) in lines().withIndex()) {
        var line = initialLine
        while (line.contains("<taskWindow>")) {
            val taskWindowStart = line.indexOf("<taskWindow>")
            line = line.replaceFirst("<taskWindow>", "")
            val taskWindowEnd = line.indexOf("</taskWindow>")
            line = line.replaceFirst("</taskWindow>", "")
            textRanges.add(TextRange(index, taskWindowStart, taskWindowEnd - taskWindowStart))
        }
    }
    return textRanges
}

fun String.removeTaskWindowTags() = replace("<taskWindow>", "").replace("</taskWindow>", "")

fun String.removeTaskWindowTagsWithWhitespaces() = replace("<taskWindow>    </taskWindow>\n\n", "").replace("<taskWindow>    </taskWindow>", "")

fun String.getSolutionsInTaskWindows(): List<String> {
    return split("<taskWindow>").filter { it.contains("</taskWindow>") }.map { it.split("</taskWindow>").first() }
}