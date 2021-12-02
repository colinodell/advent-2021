package com.colinodell.advent2021

class Day02(private val instructions: List<String>) {
    fun solvePart1(): Int {
        val submarine = Submarine(NaiveStrategy())
        for (instruction in instructions) {
            submarine.move(instruction)
        }

        return submarine.position.x * submarine.position.y
    }

    class Submarine (var movementStrategy: MovementStrategy) {
        var position = Vector2(0, 0)

        fun move(instruction: String) {
            // Split string into direction and distance
            val split = instruction.split(" ")
            val direction = split[0]
            val distance = split[1].toInt()

            position += movementStrategy.move(direction, distance)
        }
    }

    interface MovementStrategy {
        // Returns a vector representing the change in position
        fun move(direction: String, distance: Int): Vector2
    }

    inner class NaiveStrategy : MovementStrategy {
        override fun move(direction: String, distance: Int): Vector2 {
            return when (direction) {
                "forward" -> Vector2(distance, 0)
                "up" -> Vector2(0, -distance)
                "down" -> Vector2(0, distance)
                else -> throw IllegalArgumentException("Unknown direction: $direction")
            }
        }
    }
}