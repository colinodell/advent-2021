package com.colinodell.advent2021

class Day25 (private val input: List<String>) {
    fun solvePart1(): Int {
        val seaFloor = SeaFloor(input.toGrid { it }.filter { it.value != '.' }.toMutableMap())

        var steps = 0
        do {
            steps++
        } while (seaFloor.tick())

        return steps
    }

    private class SeaFloor (private val cucumbers: MutableMap<Vector2, Char>) {
        private val modX: Int = cucumbers.maxOf { it.key.x }!! + 1
        private val modY: Int = cucumbers.maxOf { it.key.y }!! + 1

        fun tick() = (move('>') + move('v')) > 0

        private fun move(direction: Char): Int {
            return cucumbers.keys
                .filter { pos ->
                    cucumbers[pos] == direction && !cucumbers.containsKey(nextPosition(pos, direction))
                }.onEach { pos ->
                    cucumbers.remove(pos)
                    cucumbers[nextPosition(pos, direction)] = direction
                }.size
        }

        private fun nextPosition(position: Vector2, direction: Char) = when (direction) {
            '>' -> Vector2((position.x + 1) % modX, position.y)
            'v' -> Vector2(position.x, (position.y + 1) % modY)
            else -> throw IllegalArgumentException("Invalid direction: $direction")
        }
    }
}