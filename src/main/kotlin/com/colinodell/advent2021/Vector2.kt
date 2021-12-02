package com.colinodell.advent2021

data class Vector2 (val x: Int, val y: Int) {
    operator fun plus(other: Vector2) = Vector2(x + other.x, y + other.y)

    operator fun minus(other: Vector2) = Vector2(x - other.x, y - other.y)

    override fun toString() = "($x, $y)"
}