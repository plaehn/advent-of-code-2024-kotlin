package org.plaehn.adventofcode.common

import com.google.common.graph.ValueGraph

fun <N : Any> ValueGraph<N, Int>.findAllPaths(start: N, end: N): List<List<N>> {
    val visited = mutableSetOf<N>()
    val path = mutableListOf<N>()
    val allPaths = mutableListOf<List<N>>()
    dfs(start, end, visited, path, allPaths)
    return allPaths
}

private fun <N : Any> ValueGraph<N, Int>.dfs(
    start: N,
    end: N,
    visited: MutableSet<N>,
    currentPath: MutableList<N>,
    allPaths: MutableList<List<N>>
) {
    visited.add(start)
    currentPath.add(start)

    if (start == end) {
        allPaths.add(currentPath.toList())
    }

    successors(start)
        .filter { it !in visited }
        .forEach { unvisited ->
            dfs(unvisited, end, visited, currentPath, allPaths)
        }

    currentPath.removeLast()
    visited.remove(start)

    if (currentPath.isEmpty()) return
}
