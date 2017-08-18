package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int = when {
        this.year < other.year -> -1
        this.year > other.year -> 1
        this.month < other.month -> -1
        this.month > other.month -> 1
        this.dayOfMonth < other.dayOfMonth -> -1
        this.dayOfMonth > other.dayOfMonth -> 1
        else -> 0
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this)


    override fun contains(value: MyDate): Boolean = start <= value && value <= endInclusive
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current = dateRange.start
    override fun hasNext(): Boolean =
            current <= dateRange.endInclusive

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val times: Int)
