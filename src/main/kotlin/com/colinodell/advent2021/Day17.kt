package com.colinodell.advent2021

import kotlin.math.abs

class Day17(input: String) {
    private val targetArea: Region

    init {
        "target area: x=(-?\\d+)..(-?\\d+), y=(-?\\d+)..(-?\\d+)".toRegex().matchEntire(input)!!.groupValues.drop(1).map { it.toInt() }.let {
            targetArea = Region(Vector2(it[0], it[2]), Vector2(it[1], it[3]))
        }
    }

    fun solvePart1() = triangularNumber(targetArea.topLeft.y * -1 - 1)
    fun solvePart2() =
        (1..targetArea.bottomRight.x).cartesianProduct(targetArea.topLeft.y..abs(targetArea.topLeft.y))
        .map { Vector2(it.first, it.second) }
        .count { v -> fire(v).first { it in targetArea || missed(targetArea, it) } in targetArea }

    private fun fire(initialVelocity: Vector2) = sequence {
        var currentPosition = Vector2(0, 0)
        var currentVelocity = initialVelocity

        while (true) {
            currentPosition += currentVelocity
            currentVelocity += Vector2(
                if (currentVelocity.x > 0) -1 else if (currentVelocity.x < 0) 1 else 0,
                -1
            )

            yield (currentPosition)
        }
    }

    private fun missed(targetArea: Region, position: Vector2): Boolean {
        return position.x > targetArea.bottomRight.x || position.y < targetArea.topLeft.y
    }

    // Calculate the triangular number for n (see https://oeis.org/A000217)
    private fun triangularNumber(n: Int) = (n * (n + 1)) / 2

    private fun <T, U> Iterable<T>.cartesianProduct(other: Iterable<U>): Iterable<Pair<T, U>> {
        return flatMap { left -> other.map { right -> left to right } }
    }
}