package org.plaehn.adventofcode.common


infix fun Long.concat(next: Long) =
    (this.toString() + next.toString()).toLong()

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