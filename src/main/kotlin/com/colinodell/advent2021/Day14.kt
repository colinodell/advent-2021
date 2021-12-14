package com.colinodell.advent2021

class Day14(input: List<String>) {
    private val template = input[0]
    private val insertionRules = input.drop(2).map { line ->
        line.split(" -> ").let { it[0] to it[0][0] + it[1] + it[0][1] }
    }.toMap()

    fun solvePart1() = polymerizeAndCalculateAnswer(10)
    fun solvePart2() = polymerizeAndCalculateAnswer(40)

    fun polymerizeAndCalculateAnswer(rounds: Int): Long {
        var polymers = template.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }

        repeat (rounds) {
            val foo = mutableMapOf<String, Long>()
            polymers.mapKeys { insertionRules[it.key] ?: it.key }.forEach {
                it.key.windowed(2).forEach { pair ->
                    foo[pair] = (foo[pair] ?: 0L) + it.value
                }
            }

            polymers = foo
        }

        val frequencies = mutableMapOf<Char, Long>()
        polymers.forEach {
            it.key.forEach { letter ->
                frequencies[letter] = (frequencies[letter] ?: 0L) + it.value
            }
        }

        frequencies[frequencies.keys.first()] = frequencies[frequencies.keys.first()]!! + 1
        frequencies[frequencies.keys.last()] = frequencies[frequencies.keys.last()]!! + 1

        return (frequencies.maxOf { it.value }!! / 2) - (frequencies.minOf { it.value }!! / 2) + 1
    }
}