package com.colinodell.advent2021

class Day12 (input: List<String>) {
    private val caves: Map<String, Set<String>> = parseInput(input)

    fun solvePart1() = countDistinctPaths("start", "end", VisitSmallCavesExactlyOnce())

    fun solvePart2() = countDistinctPaths("start", "end", VisitOneSmallCaveTwice())

    private fun countDistinctPaths(current: String, end: String, strategy: Strategy): Int {
        if (current == end) {
            return 1
        }

        return strategy.nextSteps(current).sumOf { countDistinctPaths(it, end, strategy.copy()) }
    }

    interface Strategy {
        fun nextSteps(current: String): Collection<String>
        fun copy(): Strategy
    }

    inner class VisitSmallCavesExactlyOnce : Strategy {
        private val visited = mutableSetOf<String>()

        override fun nextSteps(current: String): Collection<String> {
            if (isSmallCave(current)) {
                visited.add(current)
            }

            return caves[current]!!.filter { !visited.contains(it) }
        }

        override fun copy(): Strategy = VisitSmallCavesExactlyOnce().also { it.visited.addAll(visited) }
    }

    inner class VisitOneSmallCaveTwice : Strategy {
        private val visited = mutableSetOf<String>()
        private var caveVisitedTwice: Boolean = false

        override fun nextSteps(current: String): Collection<String> {
            if (isSmallCave(current)) {
                if (visited.contains(current)) {
                    caveVisitedTwice = true
                } else {
                    visited.add(current)
                }
            }

            return caves[current]!!.filter { canVisit(it) }
        }

        private fun canVisit(cave: String) = when {
            !visited.contains(cave) -> true // Any cave can be visited once
            !caveVisitedTwice && !setOf("start", "end").contains(cave) -> true // Any cave except "start" and "end" can be visited twice
            else -> false // Otherwise, we can't visit this cave
        }

        override fun copy(): Strategy = VisitOneSmallCaveTwice().also { it.visited.addAll(visited); it.caveVisitedTwice = caveVisitedTwice }
    }

    private fun isSmallCave(s: String) = s.all { it.isLowerCase() }

    private fun parseInput(input: List<String>) = buildMap<String, MutableSet<String>> {
        input.forEach {
            val (a, b) = it.split("-")
            this.computeIfAbsent(a) { mutableSetOf() }.add(b)
            this.computeIfAbsent(b) { mutableSetOf() }.add(a)
        }
    }
}