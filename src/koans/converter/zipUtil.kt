package koans.converter

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

fun packToZip(sourceDirPath: String, zipFilePath: String) {
    File(zipFilePath).let { if (it.exists()) it.delete() }
    val p: Path = Files.createFile(Paths.get(zipFilePath))

    ZipOutputStream(Files.newOutputStream(p)).use {
        stream ->
        val pp = Paths.get(sourceDirPath)
        Files.walk(pp).filter { path -> !Files.isDirectory(path) }.forEach { path ->
            val sp: String = path.toAbsolutePath().toString().replace(pp.toAbsolutePath().toString() + "/", "").replace(path.fileName.toString(), "")

            val zipEntry = ZipEntry(sp + "/" + path.fileName.toString())

            stream.putNextEntry(zipEntry)
            stream.write(Files.readAllBytes(path))
            stream.closeEntry()
        }
    }
}
