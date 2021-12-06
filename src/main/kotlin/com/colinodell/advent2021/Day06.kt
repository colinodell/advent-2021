package com.colinodell.advent2021

class Day06(private val input: String) {
    fun solvePart1(): Long = simulate(80)
    fun solvePart2(): Long = simulate(256)

    // Simulates the growth of the fish (`input`) after so many days
    private fun simulate(days: Int): Long {
        val fish = LongArray(9) { 0 }
        input.trim().split(",").map { it.toInt() }.forEach { fish[it]++ }

        for (i in 0 until days) {
            // Each fish that had a timer of 0 creates new fish with a timer of 8
            fish.rotateLeft()
            // Those existing fish don't just disappear; they have their timers reset to 6
            fish[6] += fish[8]
        }

        return fish.sum()
    }

    // Rotate the array to the left
    // Any value that was in the first position is wrapped around and placed at the end
    private fun LongArray.rotateLeft() {
        val shifted = first()
        copyInto(this, startIndex = 1)
        this[size - 1] = shifted
    }
}
