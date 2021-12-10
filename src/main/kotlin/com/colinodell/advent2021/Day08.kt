package com.colinodell.advent2021

class Day08 (input: List<String>) {
    private val inputs = input.map { Entry(it) }

    private val uniqueSegmentLengths = setOf(2, 3, 4, 7) // 1, 7, 4, and 8 each have a unique segment length compared to other numbers

    fun solvePart1(): Int = inputs.sumOf { it.outputValues.count { v -> uniqueSegmentLengths.contains(v.length) } }

    // I was not clever enough to solve this for myself, so I adapted the solution from @mkloserboer
    // which can be found here: https://github.com/mklosterboer/AdventOfCode2021/tree/ae559ea4d28c1b5624ae82ce0fc0b910abc2c873/AdventOfCode2021/Problems/Day08
    fun solvePart2(): Int = inputs.sumOf { it.translateOutputValue().toInt() }

    private inner class Entry(line: String) {
        val signalPatterns: List<String>
        val outputValues: List<String>
        val knownSegments: MutableMap<Char, Char> = mutableMapOf()

        init {
            with (line.split(" | ")) {
                signalPatterns = this[0].split(" ")
                outputValues = this[1].split(" ")
            }

            solveKnownSegments()
        }

        private fun solveKnownSegments() {
            val counts = "abcdefg".associateWith { 0 }.toMutableMap()
            signalPatterns.forEach() { pattern ->
                pattern.forEach { letter ->
                    counts[letter] = counts[letter]!! + 1
                }
            }

            knownSegments['e'] = counts.filter { it.value == 4 }.keys.first()
            knownSegments['b'] = counts.filter { it.value == 6 }.keys.first()
            knownSegments['f'] = counts.filter { it.value == 9 }.keys.first()
            knownSegments['c'] = signalPatterns.first { it.length == 2 }.replace(knownSegments['f']!!.toString(), "").first()
        }

        fun translateOutputValue(): String {
            return outputValues.map {
                when {
                    isZero(it) -> "0"
                    isOne(it) -> "1"
                    isTwo(it) -> "2"
                    isThree(it) -> "3"
                    isFour(it) -> "4"
                    isFive(it) -> "5"
                    isSix(it) -> "6"
                    isSeven(it) -> "7"
                    isEight(it) -> "8"
                    isNine(it) -> "9"
                    else -> throw IllegalArgumentException("Failed to decode: $it")
                }
            }.joinToString("")
        }

        private fun isZero(input: String) = input.length == 6 && input.contains(knownSegments['c']!!) && input.contains(knownSegments['e']!!)
        private fun isOne(input: String) = input.length == 2
        private fun isTwo(input: String) = input.length == 5 && !input.contains(knownSegments['f']!!)
        private fun isThree(input: String) = input.length == 5 && input.contains(knownSegments['c']!!) && input.contains(knownSegments['f']!!)
        private fun isFour(input: String) = input.length == 4
        private fun isFive(input: String) = input.length == 5 && input.contains(knownSegments['b']!!)
        private fun isSix(input: String) = input.length == 6 && !input.contains(knownSegments['c']!!) && input.contains(knownSegments['e']!!)
        private fun isSeven(input: String) = input.length == 3
        private fun isEight(input: String) = input.length == 7
        private fun isNine(input: String) = input.length == 6 && !input.contains(knownSegments['e']!!)
    }
}