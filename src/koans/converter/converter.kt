package koans.converter

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

fun main(args: Array<String>) {
    val filesMap = FilesMap()
    val course = readCourse(File(KOANS_DIR), filesMap)

    val convertedKoansDir = File(CONVERTED_KOANS_DIR)
    convertedKoansDir.mkdir()

    writeJson(course, convertedKoansDir.subFile(COURSE_JSON))

    convertTasks(convertedKoansDir, course, filesMap)

    packToZip(convertedKoansDir.path, ZIP_FILE_NAME)
}

fun writeJson(course: Course, jsonFile: File) {
    val mapper = ObjectMapper()
    mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, course)
}