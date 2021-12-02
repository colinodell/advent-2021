package com.colinodell.advent2021

class Day02(private val instructions: List<String>) {
    fun solvePart1(): Int = Submarine(NaiveStrategy()).navigate(instructions)
    fun solvePart2(): Int = Submarine(CorrectStrategy()).navigate(instructions)

    class Submarine (var movementStrategy: MovementStrategy) {
        var position = Vector2(0, 0)

        // Moves the submarine according to the instructions and returns the product of the horizontal position and depth
        fun navigate(instructions: List<String>): Int {
            for (instruction in instructions) {
                // Split string into direction and distance
                val split = instruction.split(" ")
                position += movementStrategy.move(split[0], split[1].toInt())
            }

            return position.x * position.y
        }
    }

    interface MovementStrategy {
        // Returns a vector representing the change in horizontal position (x) and depth (y)
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

    inner class CorrectStrategy : MovementStrategy {
        private var aim = 0

        override fun move(direction: String, distance: Int): Vector2 {
            return when (direction) {
                "forward" -> Vector2(distance, distance * aim)
                "up" -> adjustAim(-distance)
                "down" -> adjustAim(distance)
                else -> throw IllegalArgumentException("Unknown direction: $direction")
            }
        }

        private fun adjustAim(amount: Int): Vector2 {
            aim += amount
            return Vector2(0, 0)
        }
    }
}