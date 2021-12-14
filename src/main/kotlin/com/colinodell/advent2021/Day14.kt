package com.colinodell.advent2021

class Day14(input: List<String>) {
    private val template = input[0]
    private val insertionRules = input.drop(2).map {
        it.split(" -> ").let { it[0] to it[0][0] + it[1] + it[0][1] }
    }.toMap()

    fun solvePart1() = polymerize(10).groupingBy { it }.eachCount().let { it.maxOf { it.value } - it.minOf { it.value } }

    fun polymerize(rounds: Int): String {
        var polymer = template
        repeat (rounds) {
            polymer = polymer.windowed(2).map { insertionRules[it] ?: it }.unwindow()
        }

        // Get most frequent letter
        val counts = polymer.groupingBy { it }.eachCount()

        return polymer
    }

    fun List<String>.unwindow(): String {
        val sb = StringBuilder()

        for (i in indices) {
            if (i == 0) {
                sb.append(this[i])
            } else {
                sb.append(this[i].substring(1))
            }
        }

        return sb.toString()
    }
}