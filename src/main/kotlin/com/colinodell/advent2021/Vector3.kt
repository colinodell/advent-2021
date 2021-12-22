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

data class Cuboid(private val start: Vector3, private val end: Vector3) {
    fun isValid() = start.x <= end.x && start.y <= end.y && start.z <= end.z

    fun getVolume(): Long {
        return if (isValid()) {
            (end.x - start.x + 1L) * (end.y - start.y + 1L) * (end.z - start.z + 1L)
        } else {
            0L
        }
    }

    fun intersect(other: Cuboid): Cuboid {
        return Cuboid(
            Vector3(
                maxOf(start.x, other.start.x),
                maxOf(start.y, other.start.y),
                maxOf(start.z, other.start.z)
            ),
            Vector3(
                minOf(end.x, other.end.x),
                minOf(end.y, other.end.y),
                minOf(end.z, other.end.z)
            )
        )
    }
}
