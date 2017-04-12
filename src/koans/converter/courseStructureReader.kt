package koans.converter

import koans.converter.Settings.COURSE_AUTHORS
import koans.converter.Settings.COURSE_DESCRIPTION
import koans.converter.Settings.COURSE_LANGUAGE
import koans.converter.Settings.COURSE_NAME
import java.io.File

fun readCourse(koansDir: File, filesMap: FilesMap): Course {
    val structureReader = StructureReader()

    val lessons = koansDir.mapSubDirectoriesAndRecord(filesMap) {
        lessonDir ->

        val commonLessonFiles = lessonDir.getSourceFiles()

        val tasks = lessonDir.mapSubDirectoriesAndRecord(filesMap) {
            taskDir ->
            readTask(taskDir, filesMap, commonLessonFiles)
        }
        val lessonStructure = structureReader.readLessonStructure(lessonDir.structureFile())
        val sortedTasks = tasks.sortAccordingToStructure(lessonStructure.examples, Task::name)
        Lesson(lessonDir.name, sortedTasks)
    }
    val courseStructure = structureReader.readCourseStructure(koansDir.structureFile())

    val sortedLessons = lessons.sortAccordingToStructure(courseStructure.folders, Lesson::title)
    return Course(sortedLessons, COURSE_DESCRIPTION, COURSE_NAME, COURSE_AUTHORS, COURSE_LANGUAGE)
            .apply { filesMap.record(this, koansDir) }
}

private fun readTask(taskDir: File, filesMap: FilesMap, commonLessonFiles: List<File>): Task {
    val solutionFile = taskDir.subFile(SOLUTION_KT)
    val solutions = solutionFile.readText().uncommentTags().getSolutionsInTaskWindows()
    val taskFile = taskDir.subFile(TASK_KT_TXT)

    val code = taskFile.readText().uncommentTags()
    val textRanges = code.getTaskWindowsFromText()
    val placeholders: List<Placeholder> = textRanges.zip(solutions).map {
        val (range, solution) = it

        val lineNumber = range.line //+ 2 // 2 lines for import directive
        Placeholder(lineNumber, range.start, range.length, "", solution)
    }
    fun newTaskFile(placeholders: List<Placeholder>, name: String) =
            TaskFile(placeholders, name.transformName(Mode.EDUCATIONAL_PLUGIN))

    val mainTaskFile = newTaskFile(placeholders, taskFile.name)
    filesMap.record(mainTaskFile, taskFile)

    fun String.isOtherSourceFile(): Boolean {
        return isSourceCodeFileName() && (this !in setOf(SOLUTION_KT, TASK_KT_TXT, TEST_KT))
    }
    val otherTaskFiles = taskDir.filterSubDirectories { it.name.isOtherSourceFile() }.mapAndRecord(filesMap) {
        taskFile ->
        newTaskFile(listOf(), taskFile.name)
    }
    val commonTaskFiles = commonLessonFiles.mapAndRecord(filesMap) {
        commonFile ->
        newTaskFile(listOf(), commonFile.name)
    }
    val taskFiles = arrayListOf<TaskFile>() + mainTaskFile + otherTaskFiles + commonTaskFiles
    return Task(taskDir.name, taskFiles.associate { it.name to it })
}

private fun <T> List<T>.sortAccordingToStructure(structure: List<String>, getName: T.() -> String): List<T> {
    val elementByName = this.map { Pair(it.getName(), it) }.toMap()
    return structure.map { elementByName[it] ?: throw IllegalArgumentException("Unknown lesson / task: $it") }
}