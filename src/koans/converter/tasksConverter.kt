package koans.converter

import org.pegdown.Extensions
import org.pegdown.PegDownProcessor
import java.io.File

fun convertTasks(parentDir: File, course: Course, filesMap: FilesMap) {
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
            val packageName = "$lessonDirName.$taskDirName"

            val taskInMD = taskDirectory.subFile(TASK_EE_MD).let { if (it.exists()) it else taskDirectory.subFile(TASK_MD) }
            copyFileTaskAndTransform(taskInMD, TASK_HTML) { convertMarkdownToHtml(this) }

            copyFileTaskAndTransform(taskDirectory.subFile(TEST_KT), TESTS_KT) { addPackageNameAndImportForTests(packageName) }

            for ((name, taskFile) in task.task_files) {

                copyFileTaskAndTransform(filesMap[taskFile], filesMap[taskFile].name) {
                    (if (taskFile.placeholders.isEmpty()) this else removeTaskWindowTags()).addPackageName(packageName)
                }
            }
        }
    }
}

fun convertMarkdownToHtml(markdownText: String): String {
    val processor = PegDownProcessor(Extensions.FENCED_CODE_BLOCKS)
    return GFMNodeSerializer().toHtml(processor.parseMarkdown(markdownText.toCharArray()));
}