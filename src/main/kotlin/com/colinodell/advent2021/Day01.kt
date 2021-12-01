package com.colinodell.advent2021

class Day01(private val data: List<Int>) {
    fun solvePart1(): Int =
        data.zipWithNext()
            .count { it.second > it.first }

    fun solvePart2(): Int =
        data.windowed(3, 1)
            .map { it.sum() }
            .zipWithNext()
            .count { it.second > it.first }
}