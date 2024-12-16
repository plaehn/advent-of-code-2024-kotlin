package org.plaehn.adventofcode.common

import com.google.common.graph.ValueGraph
import java.util.*

// cf. https://www.happycoders.eu/algorithms/dijkstras-algorithm-java
@Suppress("UnstableApiUsage")
object DijkstraWithPriorityQueue {

    fun <N : Any> findShortestPath(graph: ValueGraph<N, Int>, source: N, target: N): List<N>? {
        val nodeWrappers: MutableMap<N, NodeWrapper<N>> = HashMap()
        val queue = PriorityQueue<NodeWrapper<N>>()
        val shortestPathFound: MutableSet<N> = HashSet()

        // Add source to queue
        val sourceWrapper = NodeWrapper(source, 0, null)
        nodeWrappers[source] = sourceWrapper
        queue.add(sourceWrapper)

        while (!queue.isEmpty()) {
            val nodeWrapper = queue.poll()
            val node = nodeWrapper.node
            shortestPathFound.add(node)

            // Have we reached the target? --> Build and return the path
            if (node == target) {
                return buildPath(nodeWrapper)
            }

            // Iterate over all neighbors
            val neighbors = graph.successors(node)
            for (neighbor in neighbors) {
                // Ignore neighbor if shortest path already found
                if (shortestPathFound.contains(neighbor)) {
                    continue
                }

                // Calculate total distance from start to neighbor via current node
                val distance = graph.edgeValue(node, neighbor).orElseThrow { IllegalStateException() }
                val totalDistance = nodeWrapper.totalDistance + distance

                // Neighbor not yet discovered?
                var neighborWrapper: NodeWrapper<N>? = nodeWrappers[neighbor]
                if (neighborWrapper == null) {
                    neighborWrapper = NodeWrapper(neighbor, totalDistance, nodeWrapper)
                    nodeWrappers[neighbor] = neighborWrapper
                    queue.add(neighborWrapper)
                } else if (totalDistance < neighborWrapper.totalDistance) {
                    neighborWrapper.totalDistance = totalDistance
                    neighborWrapper.predecessor = nodeWrapper

                    // The position in the PriorityQueue won't change automatically;
                    // we have to remove and reinsert the node
                    queue.remove(neighborWrapper)
                    queue.add(neighborWrapper)
                }
            }
        }

        // All reachable nodes were visited but the target was not found
        return null
    }

    private fun <N> buildPath(nodeWrapper: NodeWrapper<N>?): List<N> {
        var nodeWrapper = nodeWrapper
        val path: MutableList<N> = ArrayList()
        while (nodeWrapper != null) {
            path.add(nodeWrapper.node)
            nodeWrapper = nodeWrapper.predecessor
        }
        Collections.reverse(path)
        return path
    }
}

class NodeWrapper<N>(val node: N, var totalDistance: Int, var predecessor: NodeWrapper<N>?) :
    Comparable<NodeWrapper<N>> {

    override fun compareTo(other: NodeWrapper<N>): Int {
        return Integer.compare(this.totalDistance, other.totalDistance)
    }

    // Using identity for equals and hashcode here, which is much faster.
    // It's sufficient as within the algorithm, we have only one NodeWrapper instance per node.
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}