import java.util.*

fun getList(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    <answer>Collections.sort(arrayList, { x, y -> <taskWindow>y - x</taskWindow> })</answer>
    return arrayList
}