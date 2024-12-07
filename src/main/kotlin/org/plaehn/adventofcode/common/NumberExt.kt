package org.plaehn.adventofcode.common


infix fun Long.concat(next: Long): Long =
    shiftThisLeft(next) + next

private fun Long.shiftThisLeft(next: Long): Long {
    var first = this
    var tmp = next
    while (tmp > 0) {
        tmp /= 10
        first *= 10
    }
    return first
}

fun Iterable<Int>.product(): Int = reduce(Int::times)

fun Iterable<Long>.product(): Long = reduce(Long::times)

fun Long.leastCommonMultiple(other: Long): Long =
    this * other / this.greatestCommonDivisor(other)

tailrec fun Long.greatestCommonDivisor(other: Long): Long =
    if (other == 0L) {
        this
    } else {
        other.greatestCommonDivisor(this % other)
    }