package com.colinodell.advent2021

class Day09 (input: List<String>) {
    private val heightMap = input.toGrid(Character::getNumericValue)

    fun solvePart1() = lowestPoints().values.sumOf { it + 1 }

    fun solvePart2() = lowestPoints().map { findBasinSize(it.key) }.sortedDescending().take(3).reduce(Int::times)

    private fun lowestPoints() = heightMap.filter { allNeighborsHigherThan(it.key, it.value) }
    private fun allNeighborsHigherThan(v: Vector2, height: Int) = heightMap.neighborsOf(v).all { n -> n.value > height }
    private fun findBasinSize(v: Vector2): Int {
        val visited = mutableSetOf<Vector2>()
        val queue = mutableListOf(v)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (visited.contains(current)) {
                continue
            }
            visited.add(current)
            queue.addAll(heightMap.neighborsOf(current).filter { n -> n.value < 9 }.keys)
        }
        return visited.size
    }
}