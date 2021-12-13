package com.colinodell.advent2021

class Day13 (input: String) {
    private val dots: Set<Vector2>
    private val folds: List<Pair<Char, Int>>

    init {
        val (dotsPart, foldsPart) = input.split("\n\n")
        dotsPart.split("\n").map {
            val (x, y) = it.split(",")
            Vector2(x.toInt(), y.toInt())
        }.toSet().let { dots = it }


        foldsPart.split("\n").map {
            val (dir, dist) = it.replace("fold along ", "").split("=")
            Pair(dir[0], dist.toInt())
        }.let { folds = it }
    }

    fun solvePart1() = fold(1).size
    fun solvePart2() = print(fold(folds.size))

    fun fold(limit: Int = 1): Set<Vector2> {
        val dots = dots.toMutableSet()

        var i = 0

        for ((dir, amount) in folds) {
            i++
            when (dir) {
                'x' -> {
                    val (toFold, toKeep) = dots.partition { it.x > amount }
                    dots.removeAll(toFold)
                    toFold.map { it.copy(x = it.x - 2*(it.x - amount)) }.forEach { dots.add(it) }
                }
                'y' -> {
                    val (toFold, toKeep) = dots.partition { it.y > amount }
                    dots.removeAll(toFold)
                    toFold.map { it.copy(y = it.y - 2* (it.y - amount)) }.forEach { dots.add(it) }
                }
            }

            if (i == limit) break
        }

        return dots
    }

    fun print(points: Set<Vector2>): String {
        val minX = points.minOf { it.x }
        val minY = points.minOf { it.y }
        val maxX = points.maxOf { it.x }
        val maxY = points.maxOf { it.y }

        val grid = Array(maxY - minY + 1) { Array(maxX - minX + 1) { '.' } }

        for (point in points) {
            grid[point.y - minY][point.x - minX] = '#'
        }

        return grid.map { it.joinToString("") }.joinToString("\n")
    }
}