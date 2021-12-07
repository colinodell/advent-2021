package com.colinodell.advent2021

import kotlin.math.abs

class Day07 (inputString: String) {
    private val positions = inputString.trim().split(",").map { it.toInt() }.sorted()

    fun solvePart1(): Int = calculateOptimalFuelUsed { it }
    fun solvePart2(): Int = calculateOptimalFuelUsed { triangularNumber(it) }

    // Calculates the optimal amount of fuel by evaluating the `cost` function at each possible position.
    private fun calculateOptimalFuelUsed(cost: (Int) -> Int): Int {
        // Input was pre-sorted to quickly obtain these
        val min = positions.first()
        val max = positions.last()

        return (min..max).minOf { candidatePosition ->
            positions.sumOf { cost(abs(it - candidatePosition)) }
        }
    }

    // Calculate the triangular number for n (see https://oeis.org/A000217)
    private fun triangularNumber(n: Int) = (n * (n + 1)) / 2
}