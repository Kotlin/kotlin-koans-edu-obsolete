class DateRange(val start: MyDate, val endInclusive: MyDate)<taskWindow>    </taskWindow>

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in DateRange(first, last)
}