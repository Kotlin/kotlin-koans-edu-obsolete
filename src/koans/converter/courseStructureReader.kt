package koans.converter

import java.io.File

fun readCourse(koansDir: File, filesMap: FilesMap): Course {
    val structureReader = StructureReader()

    val lessons = koansDir.mapSubDirectoriesAndRecord(filesMap) {
        lessonDir ->

        val tasks = lessonDir.mapSubDirectoriesAndRecord(filesMap) {
            taskDir ->
            readTask(taskDir, filesMap)
        }
        val lessonStructure = structureReader.readLessonStructure(lessonDir.structureFile())
        val sortedTasks = tasks.sortAccordingToStructure(lessonStructure.examples, Task::name)
        Lesson(lessonDir.name, sortedTasks)
    }
    val courseStructure = structureReader.readCourseStructure(koansDir.structureFile())

    val sortedLessons = lessons.sortAccordingToStructure(courseStructure.folders, Lesson::title)
    return Course(sortedLessons, COURSE_DESCRIPTION, COURSE_NAME, COURSE_AUTHORS, COURSE_LANGUAGE)
}

private fun readTask(taskDir: File, filesMap: FilesMap): Task {
    val solutionFile = taskDir.subFile(SOLUTION_KT)
    val solutions = solutionFile.readText().getSolutionsInTaskWindows()
    val taskFile = taskDir.subFile(TASK_KT)

    val code = taskFile.readText()
    val textRanges = code.getTaskWindowsFromText()
    val placeholders: List<Placeholder> = textRanges.zip(solutions).map {
        val (range, solution) = it

        val lineNumber = range.line + 2 // 2 lines for import directive
        Placeholder(lineNumber, range.start, range.length, "", solution.escape())
    }
    val mainTaskFile = TaskFile(placeholders, taskFile.name)
    filesMap.record(mainTaskFile, taskFile)

    fun String.isOtherSourceFile(): Boolean {
        return this.endsWith(".kt") && (this !in setOf(SOLUTION_KT, TASK_KT, TEST_KT))
    }
    val otherTaskFiles = taskDir.filterSubDirectories { it.name.isOtherSourceFile() }.mapAndRecord(filesMap) {
        taskFile ->
        TaskFile(listOf(), taskFile.name)
    }
    val taskFiles = arrayListOf<TaskFile>() + mainTaskFile + otherTaskFiles
    return Task(taskDir.name, taskFiles.toMap { it.name to it })
}

private fun <T> List<T>.sortAccordingToStructure(structure: List<String>, getName: T.() -> String): List<T> {
    val elementByName = this.map { Pair(it.getName(), it) }.toMap()
    return structure.map { elementByName[it] ?: throw IllegalArgumentException("Unknown lesson / task: $it") }
}