<answer>class DateRange(val start: MyDate, val endInclusive: MyDate)<taskWindow> {
    operator fun contains(item: MyDate): Boolean = start <= item && item <= endInclusive
}</taskWindow></answer>

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in DateRange(first, last)
}