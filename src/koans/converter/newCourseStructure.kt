package koans.converter

class Course(
        val lessons: List<Lesson>,
        val description: String,
        val name: String,
        val authors: List<String>,
        val language: String)

class Lesson(val title: String, val task_list: List<Task>)

class Task(val name: String, val task_files: Map<String, TaskFile>)

class TaskFile(val placeholders: List<Placeholder>, val name: String)

class Placeholder(
        val line: Int,
        val start: Int,
        val length: Int,
        val hint: String,
        val possible_answer: String
)
