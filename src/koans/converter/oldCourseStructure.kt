package koans.converter

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

class StructureReader {
    private val mapper = ObjectMapper()

    fun readLessonStructure(file: File): LessonStructure {
        return readStructure(file)
    }

    fun readCourseStructure(file: File): CourseStructure {
        return readStructure(file)
    }

    private inline fun <reified S : Any> readStructure(file: File): S {
        val json = file.readText()
        return mapper.readValue(json, S::class.java)
    }

    data class CourseStructure(
            var taskFolder: Boolean = false,
            val levels: List<LevelDescription> = listOf(),
            var folders: List<String> = listOf(),
            var files: List<FileDescription> = listOf()
    )

    data class LevelDescription(
            var projectsNeeded: Int = 0,
            var color: String = ""
    )

    data class FileDescription(
            var modifiable: Boolean= false,
            var hidden: Boolean = false,
            var skipInTestVersion: Boolean = false,
            var filename: String = "",
            var type: String = ""
    )

    data class LessonStructure(
            var sequential: Boolean = false,
            var examples: List<String> = emptyList(),
            var files: List<FileDescription> = listOf()
    )
}