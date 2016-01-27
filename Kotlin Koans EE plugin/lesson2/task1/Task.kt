package lesson2.task1

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    
}

fun compare(date1: MyDate, date2: MyDate) = date1 < date2