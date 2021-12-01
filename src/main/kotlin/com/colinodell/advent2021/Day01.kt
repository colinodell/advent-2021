package com.colinodell.advent2021

class Day01(private val data: List<Int>) {
    fun solvePart1(): Int =
        data.zipWithNext {a, b -> b > a }
            .count { a -> a }

    fun solvePart2(): Int =
        data.windowed(3, 1, false) { w -> w.sum() }
            .zipWithNext {a, b -> b > a }
            .count { a -> a }
}