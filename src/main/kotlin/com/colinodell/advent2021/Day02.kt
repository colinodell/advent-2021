package com.colinodell.advent2021

class Day02(private val instructions: List<String>) {
    fun solvePart1(): Int {
        val submarine = Submarine()
        for (instruction in instructions) {
            submarine.move(instruction)
        }

        return submarine.position.x * submarine.position.y
    }

    class Submarine {
        var position = Vector2(0, 0)

        fun move(instruction: String) {
            // Split string into direction and distance
            val split = instruction.split(" ")
            val direction = split[0]
            val distance = split[1].toInt()

            when (direction) {
                "forward" -> position += Vector2(distance, 0)
                "up" -> position -= Vector2(0, distance)
                "down" -> position += Vector2(0, distance)
            }
        }
    }
}