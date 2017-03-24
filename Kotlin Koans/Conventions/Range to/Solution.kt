package conventions.range_to

/*<answer>*/operator fun MyDate.rangeTo(other: MyDate) = /*<taskWindow>*/DateRange(this, other)/*</taskWindow></answer>*/

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in first..last
}