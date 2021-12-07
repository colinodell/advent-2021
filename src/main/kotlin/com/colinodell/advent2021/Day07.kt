package com.colinodell.advent2021

import kotlin.math.abs

class Day07 (inputString: String) {
    private val input = inputString.trim().split(",").map { it.toInt() }

    fun solvePart1(): Int = calculateOptimalFuelUsed { it }
    fun solvePart2(): Int = calculateOptimalFuelUsed { triangularNumber(it) }

    private fun calculateOptimalFuelUsed(cost: (Int) -> Int): Int {
        val min = input.minOrNull()!!
        val max = input.maxOrNull()!!

        var bestResult = Int.MAX_VALUE

        // For each possible position, calculate the total fuel required for all crabs.
        for (candidatePosition in min..max) {
            // The cost is calculated by passing the distance to the candidate position into the `cost` function.
            input
                .map { cost(abs(it - candidatePosition)) }
                .sum()
                .let {
                    if (it < bestResult) {
                        bestResult = it
                    }
                }
        }

        return bestResult
    }

    // Calculate the triangular number for n (see https://oeis.org/A000217)
    private fun triangularNumber(n: Int) = (n * (n + 1)) / 2
}