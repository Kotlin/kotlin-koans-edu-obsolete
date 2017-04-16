package koans.converter

import com.fasterxml.jackson.databind.ObjectMapper
import koans.converter.Settings.CONVERT_EE
import koans.converter.Settings.CONVERT_WB
import koans.converter.Settings.CONVERT_CG
import koans.converter.Settings.CONVERTED_DIR_EE
import koans.converter.Settings.CONVERTED_DIR_WB
import koans.converter.Settings.LINKS_ENABLED
import koans.converter.Settings.LINKS_PROPERTIES
import koans.converter.Settings.PACK_ZIP
import koans.converter.Settings.SOURCE_DIR
import koans.converter.Settings.ZIP_FILE_NAME
import java.io.File
import java.io.FileInputStream
import java.util.*

fun main(args: Array<String>) {
    val koansDir = File(SOURCE_DIR)

    if (CONVERT_EE) convertEE(koansDir)
    if (CONVERT_WB) convertWD(koansDir)
    if (CONVERT_CG) convertCG(koansDir)
}

fun readLinks(): Properties {
    val links = Properties()
    if (LINKS_ENABLED) {
        FileInputStream(File("$SOURCE_DIR/$LINKS_PROPERTIES")).also { links.load(it) }
    }
    return links
}

private fun convertEE(koansDir: File) {
    val filesMap = FilesMap()
    val links = readLinks()
    val course = readCourse(koansDir, filesMap)
    val convertedKoansDir = newDir(CONVERTED_DIR_EE)
    writeJson(course, convertedKoansDir.subFile(COURSE_JSON))

    convertTasks(convertedKoansDir, course, filesMap, links)

    if (PACK_ZIP) {
        packToZip(convertedKoansDir.path, ZIP_FILE_NAME)
    }
}

private fun convertWD(koansDir: File) {
    val links = readLinks()
    val convertedForWD = newDir(CONVERTED_DIR_WB)
    convertForWebDemo(koansDir, convertedForWD, links)
}


fun writeJson(course: Course, jsonFile: File) {
    val mapper = ObjectMapper()
    mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, course)
}