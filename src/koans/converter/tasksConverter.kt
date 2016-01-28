package koans.converter

import java.io.File
import java.util.*

fun convertTasks(parentDir: File, course: Course, filesMap: FilesMap, links: Properties) {
    for ((lessonIndex, lesson) in course.lessons.withIndex()) {
        val lessonDirName = "$LESSON_DIR${lessonIndex + 1}"
        val lessonDir = parentDir.subFile(lessonDirName)
        lessonDir.mkdir()

        for ((taskIndex, task) in lesson.task_list.withIndex()) {
            val taskDirName = "$TASK_DIR${taskIndex + 1}"
            val taskDir = lessonDir.subFile(taskDirName)
            taskDir.mkdir()

            fun copyFileTaskAndTransform(oldFile: File, newFileName: String, transform: String.() -> String = { this }) {
                val newFile = taskDir.subFile(newFileName)
                newFile.writeText(oldFile.readText().transform())
            }

            val taskDirectory = filesMap[task]
            val packageName = "$taskDirName"

            val taskInMD = taskDirectory.subFile(TASK_EE_MD).let { if (it.exists()) it else taskDirectory.subFile(TASK_MD) }
            copyFileTaskAndTransform(taskInMD, TASK_HTML) { convertMarkdownToHtml(this, links) }

            copyFileTaskAndTransform(taskDirectory.subFile(TEST_KT), TESTS_KT) { addPackageNameAndImportForTests(TEST_KT, packageName) }

            for ((name, taskFile) in task.task_files) {

                copyFileTaskAndTransform(filesMap[taskFile], filesMap[taskFile].name) {
                    (if (taskFile.placeholders.isEmpty()) this else removeTaskWindowTags()).addPackageName(taskFile.name, packageName)
                }
            }
        }
    }
}