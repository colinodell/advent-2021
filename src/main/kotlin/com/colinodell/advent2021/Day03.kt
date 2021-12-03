package com.colinodell.advent2021

class Day03 (private val input: List<String>) {
    private val bitSize = input[0].length

    fun solvePart1(): Int {
        var gammaRate = ""

        for (bitPosition in 0 until bitSize) {
            gammaRate += input.mostCommonBit(bitPosition)
        }

        val gammaRateDecimal = Integer.parseInt(gammaRate, 2)
        val epsilonRateDecimal = (2 shl bitSize - 1) - gammaRateDecimal - 1

        return gammaRateDecimal * epsilonRateDecimal
    }

    fun solvePart2(): Int {
        var answer = 1

        for (getMostCommon in listOf(true, false)) {
            var numbers = input.toMutableList()

            for (bitPosition in 0 until bitSize) {
                val desiredBit = if (getMostCommon) numbers.mostCommonBit(bitPosition) else numbers.leastCommonBit(bitPosition)
                // Remove all numbers that don't have the desired bit
                numbers = numbers.filter { it.get(bitPosition) == desiredBit }.toMutableList()

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

    private fun List<String>.leastCommonBit(bitPosition: Int): Char {
        val onesCount = this.count { it[bitPosition] == '1' }
        val zeroesCount = this.size - onesCount

        return if (onesCount < zeroesCount) '1' else '0'
    }
}