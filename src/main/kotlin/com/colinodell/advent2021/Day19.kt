package com.colinodell.advent2021

// I really struggled with this one, so I ended up adapting the solution from the following:
//   - https://bit.ly/3Ei8rOw
//   - https://github.com/ThomasBollmeier/advent-of-code-kotlin-2021
class Day19(input: String) {
    private val scanners = input.split("\n\n").map { s ->
        Scanner(s.split("\n").filter { !it.startsWith("---") }.map { line ->
            line.split(",").map { it.toInt() }.let {
                Vector3(it[0], it[1], it[2])
            }
        })
    }

    fun solvePart1() = solution.flatMap { it.first.beacons }.distinct().size
    fun solvePart2() = solution.permutationPairs().maxOf { (a, b) -> a.second.manhattanDistance(b.second) }

    private val solution: List<Pair<Scanner, Vector3>> by lazy {
        val located = mutableListOf<Pair<Scanner, Vector3>>()
        val locatedIndices = mutableSetOf<Int>()
        located.add(Pair(scanners[0], Vector3(0, 0, 0)))
        locatedIndices.add(0)
        var locatedIdx = 0

        while (locatedIdx < located.size) {
            val (a, _) = located[locatedIdx]

            for ((i, b) in scanners.withIndex()) {
                if (i in locatedIndices) {
                    continue
                }

                a.findOverlap(b).ifNotNull {
                    locatedIndices.add(i)
                    located.add(Pair(it.partner, it.relativePosition))
                }
            }

            locatedIdx++
        }

        located
    }

    private data class Overlap(val partner: Scanner, val relativePosition: Vector3, val commonBeacons: Set<Vector3>)

    private inner class Scanner(val beacons: List<Vector3>) {
        fun findOverlap(other: Scanner): Overlap? {
            for (t in transforms) {
                for (i in beacons.indices) {
                    for (j in other.beacons.indices) {
                        val overlap = calcOverlap(other, t, i, j)
                        if (overlap.commonBeacons.size >= 12) {
                            return overlap
                        }
                    }
                }
            }

            return null
        }

        private fun calcOverlap(other: Scanner, t: (Vector3) -> Vector3, i: Int, j: Int): Overlap {
            // Transform and move the beacons
            var s = other.transform(t)
            val dr = beacons[i] - s.beacons[j]
            s = s.move(dr)
            val overlapPositions = beacons.toSet() intersect s.beacons.toSet()

            return Overlap(s, dr, overlapPositions)
        }

        private fun transform(t: (Vector3) -> Vector3) = Scanner(beacons.map(t))

        private fun move(dr: Vector3) = Scanner(beacons.map { it + dr })
    }

    private val transforms by lazy {
        val vectors = listOf(
            Vector3(1, 0, 0),
            Vector3(-1, 0 , 0),
            Vector3(0, 1, 0),
            Vector3(0, -1, 0),
            Vector3(0, 0, 1),
            Vector3(0, 0, -1)
        )

        val result = mutableListOf<(Vector3) -> Vector3>()

        for (vi in vectors) {
            for (vj in vectors) {
                if (vi.dot(vj) == 0) {
                    val vk = vi.cross(vj)

                    val t = arrayOf(vi.toArray(), vj.toArray(), vk.toArray())

                    result.add { v ->
                        val r = arrayOf(v.x, v.y, v.z)

                        val x = (0..2).fold(0) { acc, i ->
                            acc + t[0][i] * r[i]
                        }
                        val y = (0..2).fold(0) { acc, i ->
                            acc + t[1][i] * r[i]
                        }
                        val z = (0..2).fold(0) { acc, i ->
                            acc + t[2][i] * r[i]
                        }

                        Vector3(x, y, z)
                    }
                }
            }
        }

        result
    }
}
