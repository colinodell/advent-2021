package com.colinodell.advent2021

class Day24(input: List<String>) {
    private val blockVariables = input
        // Every chunk of 18 lines is an almost-identical block, but with three small differences, so let's capture those
        .chunked(18)
        .map { it.joinToString("") }
        .map { Regex("-?\\d+").findAll(it).map { it.value.toInt() }.toList() }
        .map { listOf(it[2], it[3], it[9]).map(Int::toLong) }

    // Extract some useful information from the input program
    private val numberOfDigits = blockVariables.size
    private val divZ = blockVariables.map { it[0] }
    private val addX = blockVariables.map { it[1] }
    private val addY = blockVariables.map { it[2] }
    private val maxZAtEachDigit = divZ.indices.map { i -> divZ.drop(i).reduce { a, b -> a * b } }

    // Use a cache to prevent unnecessary repeated calculations
    private val cache = mutableMapOf<Pair<Int, Long>, List<String>>()
    // Special non-null return value that doesn't interfere with string concatenation
    private val success = listOf("")

    // Find and store all valid model numbers
    private val validModelNumbers: List<String> = findValidModelNumbers(0, 0)!!.sorted()

    fun solvePart1() = validModelNumbers.last() // Highest valid model number
    fun solvePart2() = validModelNumbers.first() // Lowest valid model number

    private fun findValidModelNumbers(digit: Int, lastZ: Long): List<String>? {
        // Have we already calculated this?
        if (cache.containsKey(Pair(digit, lastZ))) {
            return cache[Pair(digit, lastZ)]
        }

        // If we've generated all the digits, check if the result is valid
        if (digit >= numberOfDigits) {
            // Valid model numbers must have a Z value of 0
            return if (lastZ == 0L) success else null
        }

        // If we can't divide by Z any further, there's no point in recursing any deeper
        if (lastZ > maxZAtEachDigit[digit]) {
            return null
        }

        val nextX = addX[digit] + lastZ % 26

        // If nextX is a valid digit, use that to generate the next digit; otherwise, try all digits 1-9
        val candidates = if (nextX in 1..9) listOf(nextX) else (1L..9L)
        val result = candidates.flatMap { c ->
            findValidModelNumbers(digit + 1, calculateNextZ(digit, lastZ, c)).mapIfNotNull { "$c$it" }
        }

        return result.also { cache[Pair(digit, lastZ)] = it }
    }

    private fun calculateNextZ(digit: Int, lastZ: Long, nextX: Long): Long {
        val nextZ = lastZ / divZ[digit]

        val x = addX[digit] + lastZ % 26

        return if (x == nextX) nextZ else nextZ * 26 + nextX + addY[digit]
    }
}