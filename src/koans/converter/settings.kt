package koans.converter

import java.io.FileInputStream
import java.util.*
import kotlin.reflect.KProperty

object Settings {
    private val propertiesFile = "course.properties"
    private val properties = Properties()

    init {
        with(FileInputStream(propertiesFile)) {
            properties.load(this)
            close()
        }
    }

    private operator fun Properties.getValue(thisRef: Any?, property: KProperty<*>): String =
            getProperty(property.name) ?: throw IllegalStateException("No value for '${property.name}' property in $propertiesFile")

    private val booleanProperties = object {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean =
                properties.getValue(thisRef, property).toBoolean()
    }

    val CONVERT_WB by booleanProperties
    val CONVERT_EE by booleanProperties
    val PACK_ZIP by booleanProperties

    val SOURCE_DIR by properties
    val CONVERTED_DIR_EE by properties
    val CONVERTED_DIR_WB by properties
    val ZIP_FILE_NAME by properties

    val LINKS_ENABLED by booleanProperties
    val LINKS_PROPERTIES by properties

    val COURSE_DESCRIPTION by properties
    val COURSE_NAME by properties
    val COURSE_LANGUAGE by properties
    val COURSE_AUTHORS = properties.getProperty("COURSE_AUTHORS")?.split(", ")?.toList() ?: emptyList()
}

val LINKS_PROPERTIES = "links.properties"

// old
val MANIFEST_JSON = "manifest.json"
val TASK_KT_TXT = "Task.kt.txt"
val TASK_MD = "task.md"
val TASK_EE_MD = "task-ee.md"
val SOLUTION_KT = "Solution.kt"
val TEST_KT = "Test.kt"

val KT_EXT = ".kt"
val JAVA_EXT = ".java"
val KT_TXT = ".kt.txt"
val JAVA_TXT = ".java.txt"
val CODE_FILE_EXTENSIONS = setOf(KT_EXT, JAVA_EXT, KT_TXT, JAVA_TXT)
val UTIL_FILE = "koansTestUtil.kt"

// new
val COURSE_JSON = "course.json"
val TESTS_KT = "tests.kt"
val TASK_HTML = "task.html"
val LESSON_DIR = "lesson"
val TASK_DIR = "task"
val NEW_UTIL_DIR = "util"

// Most of the package names are removed, except the following:
val VALID_PACKAGES = setOf("koans.util")