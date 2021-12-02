package com.colinodell.advent2021

class Day02(private val instructions: List<String>) {
    fun solvePart1(): Int {
        val submarine = Submarine()
        for (instruction in instructions) {
            submarine.move(instruction)
        }

        return submarine.position * submarine.depth
    }

    class Submarine {
        var position = 0
        var depth = 0

        fun move(instruction: String) {
            // Split string into direction and distance
            val split = instruction.split(" ")
            val direction = split[0]
            val distance = split[1].toInt()

            when (direction) {
                "forward" -> position += distance
                "up" -> depth -= distance
                "down" -> depth += distance
            }
        }
    }
}