package koans.converter

import java.io.File

fun File.subFile(name: String) = File("$path/$name")

fun File.structureFile() = subFile(MANIFEST_JSON)

fun String.addPackageName(lesson: String, task: String) = "package $lesson.$task\n\n" + this

fun String.isSourceCodeFileName() = CODE_FILE_EXTENSIONS.any { this.endsWith(it) }