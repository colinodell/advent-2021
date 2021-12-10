package com.colinodell.advent2021

class Day09 (input: List<String>) {
    private val heightMap: Grid<Int>

    init {
        heightMap = mutableMapOf()
        for (y in input.indices) {
            for (x in 0 until input[y].length) {
                heightMap[Vector2(x, y)] = Character.getNumericValue(input[y][x])
            }
        }
    }

    fun solvePart1() = lowestPoints().values.sumOf { it + 1 }

    fun solvePart2() = lowestPoints().map { findBasinSize(it.key) }.sortedDescending().take(3).reduce(Int::times)

    private fun lowestPoints() = heightMap.filter { allNeighborsHigherThan(it.key, it.value) }
    private fun allNeighborsHigherThan(v: Vector2, height: Int) = heightMap.neighborsOf(v).all { n -> n.value > height }
    private fun findBasinSize(v: Vector2): Int {
        val visited = mutableSetOf<Vector2>()
        val queue = mutableSetOf(v)
        var size = 0
        while (queue.isNotEmpty()) {
            val current = queue.first()
            queue.remove(current)
            if (visited.contains(current)) {
                continue
            }
            visited.add(current)
            size++
            queue.addAll(heightMap.neighborsOf(current).filter { n -> n.value < 9 }.keys)
        }
        return size
    }
}