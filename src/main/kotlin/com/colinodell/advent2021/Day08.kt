package com.colinodell.advent2021

class Day08 (input: List<String>) {
    private val inputs = input.map { Entry(it) }
    private val digits = setOf(
        Digit(0, "abcefg"),
        Digit(1, "cf"),
        Digit(2, "acdeg"),
        Digit(3, "acdfg"),
        Digit(4, "bcdf"),
        Digit(5, "abdfg"),
        Digit(6, "abdefg"),
        Digit(7, "acf"),
        Digit(8, "abcdefg"),
        Digit(9, "abcdfg"),
    )
    private val digitsBySegmentLength = digits.groupBy { it.segments.length }

    fun solvePart1(): Int = inputs.sumOf { it.outputValues.count { v -> digitsBySegmentLength[v.length]!!.size == 1 } }

    private data class Digit(val value: Int, val segments: String)

    private inner class Entry(line: String)
    {
        val signalPatterns: List<String>
        val outputValues: List<String>

        init {
            with (line.split(" | ")) {
                signalPatterns = this[0].split(" ")
                outputValues = this[1].split(" ")
            }
        }
    }
}