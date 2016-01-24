package koans.converter

import java.io.File

fun String.escape() = replace("\n", "\\n")

fun File.subFile(name: String) = File("$path/$name")

fun File.structureFile() = subFile(MANIFEST_JSON)

fun String.addPackageName(lesson: String, task: String) = "package $lesson.$task\n\n" + this