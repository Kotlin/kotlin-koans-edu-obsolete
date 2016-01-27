package koans.converter

import java.io.File

fun File.subFile(name: String) = File("$path/$name")

fun File.structureFile() = subFile(MANIFEST_JSON)

fun String.addPackageName(packageName: String) = "package $packageName\n\n" + this

fun String.addPackageNameAndImportForTests(packageName: String) = "package $packageName.tests\n\nimport $packageName.*\n" + this

fun String.isSourceCodeFileName() = CODE_FILE_EXTENSIONS.any { this.endsWith(it) }