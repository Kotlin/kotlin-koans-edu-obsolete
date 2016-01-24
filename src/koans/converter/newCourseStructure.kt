package koans.converter

data class Course(
        val lessons: List<Lesson>,
        val description: String,
        val name: String,
        val authors: List<String>,
        val language: String)

data class Lesson(val title: String, val task_list: List<Task>)

data class Task(val name: String, val task_files: Map<String, TaskFile>)

data class TaskFile(val placeholders: List<Placeholder>, val name: String)

data class Placeholder(
        val line: Int,
        val start: Int,
        val length: Int,
        val hint: String,
        val possible_answer: String
)
