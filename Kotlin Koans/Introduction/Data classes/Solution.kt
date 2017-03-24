package introduction.data_classes

/*<answer><taskWindow>*/data class Person(val name: String, val age: Int)/*</taskWindow></answer>*/

fun getPeople(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}