package org.plaehn.adventofcode.common

import com.google.common.graph.Graph


// Cf. https://en.wikipedia.org/wiki/Bron%E2%80%93Kerbosch_algorithm
@Suppress("UnstableApiUsage")
object ValueGraphBronKerboschExt {

    fun <N : Any> Graph<N>.findMaximumCliques(): Set<Set<N>> =
        bronKerbosch(
            mutableSetOf(),
            nodes().toMutableSet(),
            mutableSetOf()
        )

    private fun <N : Any> Graph<N>.bronKerbosch(
        clique: MutableSet<N>,
        candidates: MutableSet<N>,
        notClique: MutableSet<N>
    ): Set<Set<N>> {
        if (candidates.isEmpty() && notClique.isEmpty()) {
            return setOf(clique)
        }
        val maximumCliques = mutableSetOf<Set<N>>()
        val iter = candidates.iterator()
        iter.forEach { candidate ->
            val adjacentNodes = adjacentNodes(candidate)
            maximumCliques.addAll(
                bronKerbosch(
                    (clique + setOf(candidate)).toMutableSet(),
                    (candidates.intersect(adjacentNodes)).toMutableSet(),
                    (notClique.intersect(adjacentNodes)).toMutableSet()
                )
            )
            iter.remove()
            notClique.add(candidate)
        }
        return maximumCliques
    }
}
