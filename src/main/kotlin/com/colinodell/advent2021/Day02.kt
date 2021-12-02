package com.colinodell.advent2021

class Day02(private val instructions: List<String>) {
    fun solvePart1(): Int {
        val submarine = Submarine(NaiveStrategy())
        for (instruction in instructions) {
            submarine.move(instruction)
        }

        return submarine.position.x * submarine.position.y
    }

    fun solvePart2(): Int {
        val submarine = Submarine(CorrectStrategy())
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

    inner class CorrectStrategy : MovementStrategy {
        private var aim = 0

        override fun move(direction: String, distance: Int): Vector2 {
            if (direction == "forward") {
                return Vector2(distance, distance * aim)
            }

            if (direction == "up") {
                aim -= distance
                return Vector2(0, 0)
            } else if (direction == "down") {
                aim += distance
                return Vector2(0, 0)
            }

            throw IllegalArgumentException("Unknown direction: $direction")
        }
    }
}