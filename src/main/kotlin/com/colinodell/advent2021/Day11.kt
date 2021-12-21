package com.colinodell.advent2021

class Day11 (input: List<String>) {
    private val grid = input.toGrid { char -> Octopus(Character.getNumericValue(char)) }

    fun solvePart1() = (0 until 100).sumOf { runStepAndCountFlashes() }
    fun solvePart2() = generateSequence { runStepAndCountFlashes().takeIf { it != grid.size } }.count() + 1

    inner class Octopus(var energyLevel: Int)

    fun runStepAndCountFlashes(): Int {
        var flashes = 0
        // Increment all energy levels by 1
        grid.forEach { it.value.energyLevel++}

        val alreadyFlashed = mutableSetOf<Octopus>()
        do {
            // Find all octopuses with an energy level greater than 9
            val toFlash = grid.filter { it.value.energyLevel > 9 && !alreadyFlashed.contains(it.value) }
            // Flash them
            flashes += toFlash.size
            alreadyFlashed.addAll(toFlash.values)
            // Reset all flashed octopuses to 0 and increment their diagonal neighbors
            toFlash.forEach {
                it.value.energyLevel = 0
                grid.neighborsIncludingDiagonalsOf(it.key).filter {! alreadyFlashed.contains(it.value)}.forEach { it.value.energyLevel++ }
            }
        } while (grid.any { it.value.energyLevel > 9 })

        return flashes
    }
}