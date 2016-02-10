package koans.converter

import java.io.File

class FilesMap {
    private val courseDir = hashMapOf<Course, File>()
    private val lessonsDirs = hashMapOf<Lesson, File>()
    private val taskDirs = hashMapOf<Task, File>()
    private val taskFiles = hashMapOf<TaskFile, File>()

    operator fun get(course: Course) = courseDir[course] ?: error(course)
    operator fun get(lesson: Lesson) = lessonsDirs[lesson] ?: error(lesson)
    operator fun get(task: Task) = taskDirs[task] ?: error(task)
    operator fun get(taskFile: TaskFile) = taskFiles[taskFile] ?: error(taskFile)

    private fun error(element: Any?) = throw IllegalArgumentException("No file for $element")

    fun record(element: Any?, file: File) {
        when (element) {
            is Course -> courseDir[element] = file
            is Lesson -> lessonsDirs[element] = file
            is Task -> taskDirs[element] = file
            is TaskFile -> taskFiles[element] = file
        }
    }
}

fun <R> File.mapSubDirectoriesAndRecord(filesMap: FilesMap, transform: (File) -> R): List<R> {
    return listFiles().filter(File::isDirectory).mapAndRecord(filesMap, transform)
}

fun File.filterSubDirectories(predicate: (File) -> Boolean): List<File> = listFiles().filter(predicate)

fun <R> List<File>.mapAndRecord(filesMap: FilesMap, transform: (File) -> R) = map {
    val result = transform(it)
    filesMap.record(result, it)
    result
}

fun File.getSourceFiles(): List<File> = listFiles().filter { it.isFile && it.name.isSourceCodeFileName() }