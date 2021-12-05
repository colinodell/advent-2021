package com.colinodell.advent2021

class Day05 (private val input: List<String>) {
    fun solvePart1(): Int = solve { it.isHorizontal || it.isVertical }

    fun solvePart2(): Int = solve { true }

    private fun solve(filter: (Line) -> Boolean): Int {
        return input
            .map { parseLine(it) }
            .filter(filter)
            .flatMap { it.points }
            .groupBy { it }
            .count { it.value.size >= 2 }
    }

    private fun parseLine(line: String): Line {
        val split = line.split(" -> ")
        val start = split[0].split(",")
        val end = split[1].split(",")

        return Line(
            Vector2(start[0].toInt(), start[1].toInt()),
            Vector2(end[0].toInt(), end[1].toInt())
        )
    }
}

