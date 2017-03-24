package builders.function_literals_with_receiver

fun task(): List<Boolean> {

/*<answer>*/val isEven: Int.() -> Boolean = { /*<taskWindow>*/this % 2 == 0/*</taskWindow>*/ }
val isOdd: Int.() -> Boolean = { /*<taskWindow>*/this % 2 != 0/*</taskWindow>*/ }/*</answer>*/

    return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
}