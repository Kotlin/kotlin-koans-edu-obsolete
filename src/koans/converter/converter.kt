package koans.converter

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.FileInputStream
import java.util.*

fun main(args: Array<String>) {
    val filesMap = FilesMap()
    val koansDir = File(KOANS_DIR)
    val course = readCourse(koansDir, filesMap)

    val convertedKoansDir = newDir(CONVERTED_KOANS_DIR_EE)

    writeJson(course, convertedKoansDir.subFile(COURSE_JSON))

    val links = Properties()
    FileInputStream(File("$KOANS_DIR/$LINKS_PROPERTIES")).use { links.load(it) }

    convertTasks(convertedKoansDir, course, filesMap, links)

    packToZip(convertedKoansDir.path, ZIP_FILE_NAME)

    val convertedForWD = newDir(CONVERTED_KOANS_DIR_WD)
    convertForWebDemo(convertedForWD, koansDir, links)
}

fun writeJson(course: Course, jsonFile: File) {
    val mapper = ObjectMapper()
    mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, course)
}