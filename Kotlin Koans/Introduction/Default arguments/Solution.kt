package introduction.default_arguments

/*<answer><taskWindow>*/fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false)/*</taskWindow>*/ =
        (if (toUpperCase) name.toUpperCase() else name) + number/*</answer>*/

fun useFoo() = listOf(
        foo("a"),
        foo("b", number = 1),
        foo("c", toUpperCase = true),
        foo(name = "d", number = 2, toUpperCase = true)
)