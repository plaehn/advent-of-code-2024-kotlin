package org.plaehn.adventofcode.common

fun <T> List<List<T>>.transpose(): List<List<T>> =
    (this[0].indices).map { i -> (this.indices).map { j -> this[j][i] } }

// Cf. https://youtrack.jetbrains.com/issue/KT-41648
fun <T> Iterable<T>.chunked(predicate: (T, T) -> Boolean): List<List<T>> {
    val underlyingIterable = this
    return sequence {
        val buffer = mutableListOf<T>()
        var last: T? = null
        for (current in underlyingIterable) {
            val shouldSplit = last?.let { predicate(it, current) } ?: false
            if (shouldSplit) {
                yield(buffer.toList())
                buffer.clear()
            }
            buffer.add(current)
            last = current
        }
        if (buffer.isNotEmpty()) {
            yield(buffer)
        }
    }.toList()
}