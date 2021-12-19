package com.colinodell.advent2021

import kotlin.math.absoluteValue

data class Vector3(val x: Int, val y: Int, val z: Int) {
    operator fun plus(other: Vector3) = Vector3(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Vector3) = Vector3(x - other.x, y - other.y, z - other.z)

    fun dot(other: Vector3) = x * other.x + y * other.y + z * other.z

    fun cross(other: Vector3) = Vector3(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x)

    val absoluteValue by lazy { Vector3(x.absoluteValue, y.absoluteValue, z.absoluteValue) }

    fun toArray() = arrayOf(x, y, z)

    fun manhattanDistance(other: Vector3): Int {
        with((this - other).absoluteValue) {
            return x + y + z
        }
    }

    override fun toString() = "($x, $y, $z)"
}
