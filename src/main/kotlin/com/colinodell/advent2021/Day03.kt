package com.colinodell.advent2021

class Day03 (private val input: List<String>) {
    private val bitSize = input.first().length

    fun solvePart1(): Int {
        var gammaRateAsString = (0 until bitSize).concatenationOf { column -> input.mostCommonBit(column) }

        // Convert binary string to decimal
        val gammaRateDecimal = Integer.parseInt(gammaRateAsString, 2)
        // Clever way to find the epsilon rate, since the sum of both rates will always equal
        // one less than the maximum value that can be stored in `bitSize` bits
        val epsilonRateDecimal = (1 shl bitSize) - gammaRateDecimal - 1

        return gammaRateDecimal * epsilonRateDecimal
    }

    fun solvePart2(): Int {
        // Set to 1, since we'll be multiplying the answer as we go
        var answer = 1

        // First we operate with the most common bit, then we operate with the least common bit
        for (mostCommon in listOf(true, false)) {
            // Clone the list of numbers
            var numbers = input.toMutableList()

            // At each bit position...
            for (bitPosition in 0 until bitSize) {
                // Remove all numbers that don't have the desired bit
                numbers = numbers.filter { (it.get(bitPosition) == numbers.mostCommonBit(bitPosition)) == mostCommon }.toMutableList()

                // Only one number left? It must be our answer
                if (numbers.size == 1) {
                    answer *= numbers[0].toInt(2)
                    break
                }
            }
        }

        return answer
    }

    // Using input data, return the most common bit at the given column
    private fun List<String>.mostCommonBit(column: Int): Char {
        val onesCount = this.count { it[column] == '1' }
        val zeroesCount = this.size - onesCount

        return if (onesCount >= zeroesCount) '1' else '0'
    }

    // Using the current iterable, execute the given function on each element and concatenate the returned chars into a string
    private inline fun <T> Iterable<T>.concatenationOf(selector: (T) -> Char): String {
        var result = ""
        for (element in this) {
            result += selector(element)
        }
        return result
    }
}