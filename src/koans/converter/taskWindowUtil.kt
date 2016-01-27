package koans.converter

import java.util.*

data class TextRange(val line: Int, val start: Int, val length: Int)

private val TASK_WINDOW = "taskWindow"
private val OPEN = "<$TASK_WINDOW>"
private val CLOSED = "</$TASK_WINDOW>"
private val EMPTY_TASK_WINDOW = "$OPEN    $CLOSED"

fun String.getTaskWindowsFromText(): List<TextRange> {
    val textRanges = ArrayList<TextRange>()
    for ((index, initialLine) in lines().withIndex()) {
        var line = initialLine
        while (line.contains(OPEN)) {
            val taskWindowStart = line.indexOf(OPEN)
            line = line.replaceFirst(OPEN, "")
            val taskWindowEnd = line.indexOf(CLOSED)
            line = line.replaceFirst(CLOSED, "")
            textRanges.add(TextRange(index, taskWindowStart, taskWindowEnd - taskWindowStart))
        }
    }
    return textRanges
}

fun String.removeTaskWindowTags() = replace(EMPTY_TASK_WINDOW, "").replace(OPEN, "").replace(CLOSED, "")

fun String.removeTaskWindowTagsWithWhitespaces() = replace(EMPTY_TASK_WINDOW, "")

fun String.getSolutionsInTaskWindows(): List<String> {
    return split(OPEN).filter { it.contains(CLOSED) }.map { it.split(CLOSED).first() }
}