package koans.converter

import java.io.File

fun File.subFile(name: String) = File("$path/$name")

fun File.structureFile() = subFile(MANIFEST_JSON)

fun newDir(name: String) = File(name).apply {
    if (exists()) deleteRecursively()
    mkdir()
}

fun String.addPackageName(fileName: String, packageName: String) =
        "package $packageName" + (if (fileName.endsWith("java")) ";" else "") + "\n\n" + this

fun String.addPackageNameAndImportForTests(fileName: String, packageName: String) =
        ("import $packageName.*\n" + this).addPackageName(fileName, "$packageName.tests")

fun String.isSourceCodeFileName() = CODE_FILE_EXTENSIONS.any { this.endsWith(it) } && !contains("-wb")

enum class Mode { WEB_DEMO, EDUCATIONAL_PLUGIN }

fun String.transformUtilFile(mode: Mode) = replace("Mode.UNDEFINED", "Mode." + mode.name)

fun String.transformName(mode: Mode) = transformExtension().removeModeSuffix(mode)

private fun String.transformExtension() = when {
    endsWith(KT_TXT) -> replace(KT_TXT, KT_EXT)
    endsWith(JAVA_TXT) -> replace(JAVA_TXT, JAVA_EXT)
    else -> this
}

private fun String.removeModeSuffix(mode: Mode): String {
    val suffix = if (mode == Mode.WEB_DEMO) "-wb" else "-ee"
    return if (contains(suffix)) replace(suffix, "") else this
}

fun String.replaceImports(mode: Mode, taskName: String = "") =
        replace("import LOCAL.", if (mode == Mode.WEB_DEMO) "import " else "import $taskName.")

fun String.removePackageDeclarations(): String {
    val lines = split("\n")
    if (lines.size < 2) return this

    val packageDirective = lines[0]
    if (!packageDirective.startsWith("package ")) return this

    val packageName = packageDirective.substringAfter("package ")
    if (packageName in VALID_PACKAGES) return this
    // used in collections in 'kotlin koans'
    val subPackageName = packageName.substringBeforeLast(".")

    return lines.subList(2, lines.size).joinToString("\n")
            .replace("import $packageName.*\n", "")
            .replace("import $subPackageName.*\n", "")
            .replace("import $packageName.", "import ")
            .replace("import $subPackageName.", "import ")
            .trimStart()
}