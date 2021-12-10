package com.colinodell.advent2021

import kotlin.math.abs
import kotlin.math.max

data class Vector2 (val x: Int, val y: Int) {
    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)

    operator fun minus(other: Vector2) = Vector2(x - other.x, y - other.y)

    override fun toString() = "($x, $y)"

    fun neighbors() = listOf(
        Vector2(x - 1, y),
        Vector2(x + 1, y),
        Vector2(x, y - 1),
        Vector2(x, y + 1)
    )
}

// A line that is at some multiple of 45 degrees (horizontal, vertical, or diagonal)
data class Line(val start: Vector2, val end: Vector2) {
    val points : List<Vector2> by lazy {
        val xDiff = end.x - start.x
        val yDiff = end.y - start.y
        val stepCount = max(abs(xDiff), abs(yDiff))

        val xStep = xDiff / stepCount
        val yStep = yDiff / stepCount

        (0 .. stepCount).map { Vector2(start.x + it * xStep, start.y + it * yStep) }
    }

    val isHorizontal : Boolean by lazy { start.y == end.y }
    val isVertical : Boolean by lazy { start.x == end.x }
    val isDiagonal : Boolean by lazy { ! (isHorizontal || isVertical) }
}

typealias Grid<T> = Map<Vector2, T>
fun <T> Grid<T>.neighborsOf(point: Vector2): Map<Vector2, T> {
    return point.neighbors().filter { containsKey(it) }.associateWith { get(it)!! }
}