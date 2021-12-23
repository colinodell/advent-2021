package com.colinodell.advent2021

import java.util.*
import kotlin.Comparator
import kotlin.collections.HashSet
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

// Adapted from https://github.com/schovanec/AdventOfCode/blob/master/2021/Day23/Program.cs
class Day23(input: List<String>) {
    companion object {
        internal const val hallwayLength = 11
        internal val amphipodTypes = charArrayOf('A', 'B', 'C', 'D')
        internal val roomPositions = arrayOf(2, 4, 6, 8)
    }

    private val initialState = parse(input)
    private val goalState = initialState.copy(rooms = initialState.rooms.toCharArray().sorted().joinToString(""))

    fun solve(): Int {
        return solve(initialState, goalState)
    }

    private fun parse(input: List<String>): State {
        val hall = ".".repeat(hallwayLength)
        val rooms = mutableListOf("", "", "", "")

        for (line in input) {
            if (line.any { amphipodTypes.contains(it) }) {
                val values = line.filter { amphipodTypes.contains(it) }
                for (i in rooms.indices) {
                    rooms[i] = rooms[i] + values[i]
                }
            }
        }

        return State(hall, rooms.joinToString(""), rooms[0].length);
    }

    private fun solve(start: State, goal: State): Int {
        val bestCostPerState = mutableMapOf(start to 0)
        val visited = HashSet<State>()
        val queue = PriorityQueue<Move>(1, Comparator.comparingInt { it.cost })
        queue.add(Move(start, 0))

        while (!queue.isEmpty()) {
            val current = queue.poll()
            // Skip any states we've already visited
            if (visited.contains(current.state)) {
                continue
            }

            visited.add(current.state)

            if (current.state == goal) {
                break
            }

            val currentCost = bestCostPerState[current.state]!!

            for(next in current.state.nextPossibleMoves()) {
                val alt = currentCost + next.cost

                if (alt < bestCostPerState.getOrDefault(next.state, Int.MAX_VALUE)) {
                    bestCostPerState[next.state] = alt
                    queue.add(Move(next.state, alt))
                }
            }
        }

        return bestCostPerState[goal]!!
    }

    private data class Move(val state: State, val cost: Int)

    private data class State(val hall: String, val rooms: String, val roomSize: Int) {
        fun getRoom(room: Int): String {
            return rooms.substring(room * roomSize).take(roomSize)
        }

        fun nextPossibleMoves() = sequence {
            for(room in 0 until 4) {
                for (pos in openSpaces(room)) {
                    yieldIfNotNull(tryMoveOut(room, pos))
                }
            }

            for(i in hall.indices) {
                yieldIfNotNull(tryMoveIn(i))
            }
        }

        private fun openSpaces(room: Int) = sequence {
            val position = roomPositions[room]

            var i = position - 1
            while (i >= 0 && hall[i] == '.') {
                if (!roomPositions.contains(i)) {
                    yield(i)
                }
                i--
            }

            i = position + 1
            while (i < hall.length && hall[i] == '.') {
                if (!roomPositions.contains(i)) {
                    yield(i)
                }
                i++
            }
        }

        private fun tryMoveOut(roomIndex: Int, targetPosition: Int): Move? {
            val room = getRoom(roomIndex)
            val depth = room.indexOfAny(amphipodTypes)
            if (depth < 0) {
                return null
            }

            val steps = (targetPosition - roomPositions[roomIndex]).absoluteValue + depth + 1
            val amphipod = room[depth]

            val updatedHall = hall.replaceAt(targetPosition, amphipod)
            val updatedRoom = room.replaceAt(depth, '.')

            return Move(update(updatedHall, roomIndex, updatedRoom), steps * getEnergyCost(amphipod))
        }

        private fun tryMoveIn(hallwayPosition: Int): Move? {
            val amphipod = hall[hallwayPosition]
            val goalRoomIndex = amphipodTypes.indexOf(amphipod)
            if (goalRoomIndex < 0) {
                return null
            }

            val goalPosition = roomPositions[goalRoomIndex]
            val start = if (goalPosition > hallwayPosition) hallwayPosition + 1 else hallwayPosition - 1
            val min = min(goalPosition, start)
            val max = max(goalPosition, start)
            if (!hall.substring(min, max).all { it == '.' }) {
                return null
            }

            val room = getRoom(goalRoomIndex)
            if (room.any { it != '.' && it != amphipod }) {
                return null
            }

            val depth = room.lastIndexOf('.')
            val steps = (max - min + 1) + depth + 1
            val updatedHall = hall.replaceAt(hallwayPosition, '.')
            val updatedRoom = room.replaceAt(depth, amphipod)

            return Move(update(updatedHall, goalRoomIndex, updatedRoom), steps * getEnergyCost(amphipod))
        }

        private fun update(updatedHall: String, roomIndex: Int, updatedRoom: String): State {
            val updatedRooms = rooms.replaceAt(roomIndex * roomSize, roomSize, updatedRoom)
            return this.copy(hall = updatedHall, rooms = updatedRooms)
        }

        private fun getEnergyCost(amphipod: Char): Int {
            return when (amphipod) {
                'A' -> 1
                'B' -> 10
                'C' -> 100
                'D' -> 1000
                else -> throw IllegalArgumentException("Unknown amphipod type: $amphipod")
            }
        }
    }
}
