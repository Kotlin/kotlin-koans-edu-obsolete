package introduction.object_expressions

import java.util.*

fun getList(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)

/*<answer>*/Collections.sort(arrayList, /*<taskWindow>*/object : Comparator<Int> {
    override fun compare(x: Int, y: Int) = y - x
}/*</taskWindow>*/)/*</answer>*/

    return arrayList
}