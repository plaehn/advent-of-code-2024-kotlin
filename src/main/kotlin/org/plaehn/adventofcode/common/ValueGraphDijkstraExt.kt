package org.plaehn.adventofcode.common

import com.google.common.graph.ValueGraph


@Suppress("UnstableApiUsage")
object ValueGraphDijkstraExt {

    fun <N : Any> ValueGraph<N, Int>.shortestPath(
        start: N,
        end: N,
        keepAllPrevious: Boolean = false
    ): List<N> =
        shortestPaths(start, keepAllPrevious) { it == end }.first()

    fun <N : Any> ValueGraph<N, Int>.shortestPaths(
        start: N,
        keepAllPrevious: Boolean = false,
        endNodePredicate: (N) -> Boolean
    ): List<List<N>> {
        val shortestPathTree: Map<N, List<N>?> = computeShortestPathTree(start, keepAllPrevious)

        fun pathsTo(start: N, end: N): List<List<N>> {
            if (shortestPathTree[end] == null) return listOf(listOf(end))

            val previous: List<N> = shortestPathTree[end]!!
            return previous.map { prev ->
                val pathsFromStartToPrev = pathsTo(start, prev)
                pathsFromStartToPrev.map { path ->
                    path.toMutableList() + mutableListOf(end)

                }
            }.flatten()
        }

        return nodes()
            .filter(endNodePredicate)
            .flatMap { pathsTo(start, it) }
            .filter { it.first() == start }
    }

    // Cf. https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    private fun <N : Any> ValueGraph<N, Int>.computeShortestPathTree(
        start: N,
        keepAllPrevious: Boolean
    ): Map<N, List<N>?> {
        val visited: MutableSet<N> = mutableSetOf()

        val delta = nodes().associateWith { Int.MAX_VALUE }.toMutableMap()
        delta[start] = 0

        val previous: MutableMap<N, MutableList<N>?> = nodes().associateWith { null }.toMutableMap()

        while (visited != nodes()) {
            val node = delta
                .filter { !visited.contains(it.key) }
                .minByOrNull { it.value }!!
                .key

            successors(node).minus(visited).forEach { neighbor ->
                val newPath = delta.getValue(node) + edgeValue(node, neighbor!!).get()

                if (newPath < delta.getValue(neighbor) || (keepAllPrevious && newPath == delta.getValue(neighbor))) {
                    delta[neighbor] = newPath
                    if (previous[neighbor] != null && keepAllPrevious) {
                        previous[neighbor] = (previous[neighbor]!! + mutableListOf(node)).toMutableList()
                    } else {
                        previous[neighbor] = mutableListOf(node)
                    }
                }
            }

            visited.add(node)
        }

        return previous.toMap()
    }
}
