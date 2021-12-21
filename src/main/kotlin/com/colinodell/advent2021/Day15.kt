package com.colinodell.advent2021

import java.util.PriorityQueue
import kotlin.math.abs

class Day15(input: List<String>) {
    private val cavern = input.toGrid(Character::getNumericValue)

    fun solvePart1() = a_star(cavern, cavern.topLeft(), cavern.bottomRight())
    fun solvePart2() = cavern.tile(5).let {
        a_star(it, it.topLeft(), it.bottomRight())
    }

    private fun a_star(grid: Grid<Int>, start: Vector2, goal: Vector2): Int {
        val h = fun (v: Vector2) = abs(v.x - goal.x) + abs(v.y - goal.y)

        // The cost of the cheapest path
        val gScore = mutableMapOf(start to 0)

        // Our current best guess as to how far we are from the goal
        val fScore = mutableMapOf(start to h(start))

        val openSet = PriorityQueue<Vector2>(1) { a, b ->
            gScore[a]!! - gScore[b]!!
        }
        openSet.add(start)

        val cameFrom = mutableMapOf<Vector2, Vector2>()

        while (openSet.isNotEmpty()) {
            val current = openSet.poll()
            if (current == goal) {
                return gScore[current]!!
            }

            for (neighbor in current.neighbors()) {
                if (!grid.containsKey(neighbor) || grid[neighbor] == 0) {
                    continue
                }

                val tentativeGScore = gScore[current]!! + grid[neighbor]!!
                if (tentativeGScore >= gScore.getOrDefault(neighbor, Int.MAX_VALUE)) {
                    continue
                }
                cameFrom[neighbor] = current
                gScore[neighbor] = tentativeGScore
                fScore[neighbor] = tentativeGScore + h(neighbor)
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor)
                }
            }
        }

        throw IllegalStateException("No path found")
    }

    private fun Grid<Int>.tile(times: Int): Grid<Int> {
        val newGrid = mutableMapOf<Vector2, Int>()

        val width = width()
        val height = height()

        this.forEach { (pos, value) ->
            for (xDiff in 0 until times) {
                for (yDiff in 0 until times) {
                    newGrid[Vector2(pos.x + xDiff * width, pos.y + yDiff * height)] = (value + xDiff + yDiff).let { if (it > 9) it - 9 else it }
                }
            }
        }

        return newGrid
    }
}