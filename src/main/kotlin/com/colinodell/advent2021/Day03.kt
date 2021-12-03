package com.colinodell.advent2021

class Day03 (private val input: List<String>) {
    private val bitSize = input[0].length

    fun solvePart1(): Int {
        var gammaRate = ""

        // Construct a new binary string with the most common bit at each position
        for (bitPosition in 0 until bitSize) {
            gammaRate += input.mostCommonBit(bitPosition)
        }

        // Convert binary string to decimal
        val gammaRateDecimal = Integer.parseInt(gammaRate, 2)
        // Clever way to find the epsilon rate, since the sum of both rates will always equal
        // one less than the maximum value that can be stored in `bitSize` bits
        val epsilonRateDecimal = (2 shl bitSize - 1) - gammaRateDecimal - 1

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

    private fun List<String>.mostCommonBit(bitPosition: Int): Char {
        val onesCount = this.count { it[bitPosition] == '1' }
        val zeroesCount = this.size - onesCount

        return if (onesCount >= zeroesCount) '1' else '0'
    }
}