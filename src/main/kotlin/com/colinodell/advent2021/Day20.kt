package com.colinodell.advent2021

class Day20(input: List<String>) {
    private val algorithm: String = input.first().map(::toOnesOrZeros).joinToString("")
    private val grid: Grid<Char> = input.drop(2).toGrid(::toOnesOrZeros)

    fun solvePart1() = solve(2)
    fun solvePart2() = solve(50)

    private data class Image(val pixels: Grid<Char>, val default: Char)

    private fun solve(times: Int) =
        (0 until times)
            .fold(Image(grid, '0')) { image, _ -> enhance(image) }
            .pixels.count { it.value == '1' }

    private fun enhance(enhancement: Image): Image {
        val (pixels, default) = enhancement

        val newGrid = pixels.mapAllPositions(padding = 1) {
            algorithm[
                it.key.allNeighborsIncludingSelf()
                    .map { pixels[it] ?: default }
                    .joinToString("")
                    .toInt(2)
            ]
        }

        return Image(newGrid, if (default == '0') algorithm.first() else algorithm.last())
    }

    private fun toOnesOrZeros(c: Char) = if (c == '#') '1' else '0'
}