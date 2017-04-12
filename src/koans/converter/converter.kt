package koans.converter

import com.fasterxml.jackson.databind.ObjectMapper
import koans.converter.Settings.CONVERT_EE
import koans.converter.Settings.CONVERT_WB
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
    convert()
}

fun convert() {
    val filesMap = FilesMap()
    val koansDir = File(SOURCE_DIR)

    val links = Properties()
    if (LINKS_ENABLED) {
        FileInputStream(File("$SOURCE_DIR/$LINKS_PROPERTIES")).also { links.load(it) }
    }

    if (CONVERT_EE) {
        val course = readCourse(koansDir, filesMap)
        val convertedKoansDir = newDir(CONVERTED_DIR_EE)
        writeJson(course, convertedKoansDir.subFile(COURSE_JSON))

        convertTasks(convertedKoansDir, course, filesMap, links)

        if (PACK_ZIP) {
            packToZip(convertedKoansDir.path, ZIP_FILE_NAME)
        }
    }

    if (CONVERT_WB) {
        val convertedForWD = newDir(CONVERTED_DIR_WB)
        convertForWebDemo(koansDir, convertedForWD, links)
    }
}

fun writeJson(course: Course, jsonFile: File) {
    val mapper = ObjectMapper()
    mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, course)
}