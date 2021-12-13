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
    fun solvePart2() = fold(folds.size).toStringVisualization()

    fun fold(limit: Int = 1): Set<Vector2> {
        val dots = dots.toMutableSet()

        var i = 0

        for ((dir, amount) in folds) {
            i++
            when (dir) {
                'x' -> {
                    val toFold = dots.filter { it.x > amount }
                    dots.removeAll(toFold.toSet())
                    toFold.map { it.copy(x = it.x - 2*(it.x - amount)) }.forEach { dots.add(it) }
                }
                'y' -> {
                    val toFold = dots.filter { it.y > amount }
                    dots.removeAll(toFold.toSet())
                    toFold.map { it.copy(y = it.y - 2*(it.y - amount)) }.forEach { dots.add(it) }
                }
            }

            if (i == limit) break
        }

        return dots
    }
}