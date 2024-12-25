package org.plaehn.adventofcode.common

import com.google.common.graph.Graph


// Cf. https://en.wikipedia.org/wiki/Bron%E2%80%93Kerbosch_algorithm
@Suppress("UnstableApiUsage")
object ValueGraphBronKerboschExt {

    fun <N : Any> Graph<N>.findMaximalCliques(): Set<Set<N>> =
        bronKerbosch(State(candidates = nodes()))

    private fun <N : Any> Graph<N>.bronKerbosch(currentState: State<N>): Set<Set<N>> =
        if (currentState.isMaximalClique()) {
            setOf(currentState.clique)
        } else {
            currentState.candidates.fold(currentState) { state, candidate ->
                val newMaximalCliques = bronKerbosch(
                    State(
                        state.clique + candidate,
                        state.candidates intersect adjacentNodes(candidate),
                        state.notClique intersect adjacentNodes(candidate)
                    )
                )
                state.copy(
                    candidates = state.candidates - candidate,
                    notClique = state.notClique + candidate,
                    maximalCliques = state.maximalCliques + newMaximalCliques
                )
            }.maximalCliques
        }

    data class State<N : Any>(
        val clique: Set<N> = setOf(),
        val candidates: Set<N>,
        val notClique: Set<N> = setOf(),
        val maximalCliques: Set<Set<N>> = setOf()
    ) {
        fun isMaximalClique() = candidates.isEmpty() && notClique.isEmpty()
    }
}
