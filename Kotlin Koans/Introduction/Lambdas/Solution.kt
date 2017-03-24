package introduction.lambdas

fun containsEven(collection: Collection<Int>): Boolean = collection.any { /*<taskWindow>*/it % 2 == 0/*</taskWindow>*/ }
