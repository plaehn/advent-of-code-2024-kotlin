package org.plaehn.adventofcode.common

import com.google.common.graph.ValueGraph
import java.util.*

// Cf. https://www.happycoders.eu/algorithms/dijkstras-algorithm-java
@Suppress("UnstableApiUsage")
object ValueGraphDijkstraExt {

    fun <N : Any> ValueGraph<N, Int>.findShortestPath(
        source: N,
        target: N,
        keepAllPrevious: Boolean = false
    ): List<List<N>> {
        val nodeWrappers: MutableMap<N, NodeWrapper<N>> = HashMap()
        val queue = PriorityQueue<NodeWrapper<N>>()
        val shortestPathFound: MutableSet<N> = HashSet()

        // Add source to queue
        val sourceWrapper = NodeWrapper(source, 0, emptySet())
        nodeWrappers[source] = sourceWrapper
        queue.add(sourceWrapper)

        while (!queue.isEmpty()) {
            val nodeWrapper = queue.poll()
            val node = nodeWrapper.node
            shortestPathFound.add(node)

            // Have we reached the target? --> Build and return the path
            if (node == target) {
                return buildPaths(nodeWrapper).reversed()
            }

            // Iterate over all neighbors
            val neighbors = successors(node)
            for (neighbor in neighbors) {
                // Ignore neighbor if shortest path already found
                if (shortestPathFound.contains(neighbor)) {
                    continue
                }

                // Calculate total distance from start to neighbor via current node
                val distance = edgeValue(node, neighbor).orElseThrow { IllegalStateException() }
                val totalDistance = nodeWrapper.totalDistance + distance

                // Neighbor not yet discovered?
                var neighborWrapper: NodeWrapper<N>? = nodeWrappers[neighbor]
                if (neighborWrapper == null) {
                    neighborWrapper = NodeWrapper(neighbor, totalDistance, setOf(nodeWrapper))
                    nodeWrappers[neighbor] = neighborWrapper
                    queue.add(neighborWrapper)
                } else if (totalDistance < neighborWrapper.totalDistance || (keepAllPrevious && totalDistance == neighborWrapper.totalDistance)) {
                    neighborWrapper.totalDistance = totalDistance
                    neighborWrapper.predecessors += nodeWrapper.predecessors

                    // The position in the PriorityQueue won't change automatically;
                    // we have to remove and reinsert the node
                    queue.remove(neighborWrapper)
                    queue.add(neighborWrapper)
                }
            }
        }

        // All reachable nodes were visited but the target was not found
        return emptyList()
    }

    private fun <N : Any> buildPaths(nodeWrapper: NodeWrapper<N>): List<List<N>> =
        nodeWrapper.predecessors
            .map { predecessor ->
                buildPaths(predecessor).toSet().flatMap { it + listOf(predecessor.node) }
            }
    //  .map { it + listOf(nodeWrapper.node) }
}

data class NodeWrapper<N>(
    val node: N,
    var totalDistance: Int,
    var predecessors: Set<NodeWrapper<N>>
) :
    Comparable<NodeWrapper<N>> {

    override fun compareTo(other: NodeWrapper<N>): Int =
        this.totalDistance.compareTo(other.totalDistance)

    // Using identity for equals and hashcode here, which is much faster.
    // It's sufficient as within the algorithm, we have only one NodeWrapper instance per node.
    override fun equals(other: Any?) =
        super.equals(other)

    override fun hashCode() =
        super.hashCode()
}