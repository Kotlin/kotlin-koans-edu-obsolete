package koans.converter

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.FileInputStream
import java.util.*

fun main(args: Array<String>) {
    val filesMap = FilesMap()
    val course = readCourse(File(KOANS_DIR), filesMap)

    val convertedKoansDir = File(CONVERTED_KOANS_DIR)
    convertedKoansDir.mkdir()

    writeJson(course, convertedKoansDir.subFile(COURSE_JSON))

    val links = Properties()
    FileInputStream(File(LINKS_PROPERTIES)).use { links.load(it) }

    convertTasks(convertedKoansDir, course, filesMap, links)

    packToZip(convertedKoansDir.path, ZIP_FILE_NAME)
}

fun writeJson(course: Course, jsonFile: File) {
    val mapper = ObjectMapper()
    mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, course)
}