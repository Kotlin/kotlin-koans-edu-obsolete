package koans.converter

import org.pegdown.Extensions
import org.pegdown.PegDownProcessor
import java.io.File
import java.nio.file.Files

fun convertTasks(parentDir: File, course: Course, filesMap: FilesMap) {
    for ((lessonIndex, lesson) in course.lessons.withIndex()) {
        val lessonDirName = "$LESSON_DIR${lessonIndex + 1}"
        val lessonDir = parentDir.subFile(lessonDirName)
        lessonDir.mkdir()

        for ((taskIndex, task) in lesson.task_list.withIndex()) {
            val taskDirName = "$TASK_DIR${taskIndex + 1}"
            val taskDir = lessonDir.subFile(taskDirName)
            taskDir.mkdir()

            val taskInMD = filesMap[task].subFile(TASK_MD)
            val taskInHTML = taskDir.subFile(TASK_HTML)
            taskInHTML.writeText(convertMarkdownToHtml(taskInMD))

            val oldTestFile = filesMap[task].subFile(TEST_KT)
            val newTestFile = taskDir.subFile(TESTS_KT)
            newTestFile.writeText(oldTestFile.readText().addPackageName(lessonDirName, taskDirName))

            for ((name, taskFile) in task.task_files) {
                val file = filesMap[taskFile]
                val newTaskText = file.readText().let {
                    if (taskFile.placeholders.isEmpty()) it else it.removeTaskWindowTags()
                }
                val fileName = file.name.let { if (it == TASK_KT) TASK_ANSWER_KT else it }
                taskDir.subFile(fileName).writeText(newTaskText.addPackageName(lessonDirName, taskDirName))
            }
        }
    }
}

fun convertMarkdownToHtml(file: File): String {
    val processor = PegDownProcessor(Extensions.FENCED_CODE_BLOCKS)
    val helpInMarkdown = String(Files.readAllBytes(file.toPath()))
    return GFMNodeSerializer().toHtml(processor.parseMarkdown(helpInMarkdown.toCharArray()));
}