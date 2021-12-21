package com.colinodell.advent2021

class Day20(input: List<String>) {
    private val algorithm: String = input.first().map(::toOnesOrZeros).joinToString("")
    private val grid: Grid<Char> = input.drop(2).toGrid(::toOnesOrZeros)

    fun solvePart1() = apply(2).count { it.value == '1' }
    fun solvePart2() = apply(50).count { it.value == '1' }

    private fun apply(times: Int): Grid<Char> {
        var grid = grid.toMap()
        val padding = 1
        var default = '0'

        for (i in 0 until times) {
            val newGrid = mutableMapOf<Vector2, Char>()

            // For each known cell in the infinite grid...
            (grid.keys.minOf { it.y } - padding .. grid.keys.maxOf { it.y } + padding).forEach { y ->
                (grid.keys.minOf { it.x } - padding .. grid.keys.maxOf { it.x } + padding).forEach { x ->
                    // Calculate the binary number for the cell
                    val binaryNumber =
                        Vector2(x,y)
                            .allNeighborsIncludingSelf()
                            .map { grid[it] ?: default }
                            .joinToString("")
                            .toInt(2)
                    // Apply the algorithm using that number
                    newGrid[Vector2(x, y)] = algorithm[binaryNumber]
                }
            }

            // Save the completed grid
            grid = newGrid
            // Update the next default value
            default = if (default == '0') algorithm.first() else algorithm.last()
        }

        return grid
    }

    private fun toOnesOrZeros(c: Char) = if (c == '#') '1' else '0'
}