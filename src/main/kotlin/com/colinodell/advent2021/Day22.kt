package com.colinodell.advent2021


class Day22(input: List<String>) {
    private val initializationProcedureRegion = Cuboid(Vector3(-50, -50, -50), Vector3(50, 50, 50))
    private val procedure = parseInput(input)
    private val initializationProcedure = procedure.mapKeys { it.key.intersect(initializationProcedureRegion) }.filter { it.key.isValid() }

    fun solvePart1() = initializeAndCountLitCuboids(initializationProcedure)
    fun solvePart2() = initializeAndCountLitCuboids(procedure)

    private fun initializeAndCountLitCuboids(instructions: Map<Cuboid, Boolean>): Long {
        val initialized = mutableListOf<Pair<Cuboid, Boolean>>()

        instructions.forEach { (cuboid, isOn) ->
            // Invert any cuboids intersecting with this one
            initialized
                .map { it.first.intersect(cuboid) to !it.second }
                .filter { it.first.isValid() }
                .run { initialized.addAll(this) }

            // Add this cuboid, but only if it's on
            if (isOn) {
                initialized.add(cuboid to true)
            }
        }

        // Calculate the number of lit points
        return initialized.sumOf { (cuboid, isOn) -> cuboid.getVolume() * (if (isOn) 1 else -1) }
    }

    private fun parseInput(input: List<String>): Map<Cuboid, Boolean> {
        val map = mutableMapOf<Cuboid, Boolean>()

        // Parse each input line with regex
        input.map {
            "(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)".toRegex().find(it)?.let {
                val (action, x1, x2, y1, y2, z1, z2) = it.destructured
                map[Cuboid(Vector3(x1.toInt(), y1.toInt(), z1.toInt()), Vector3(x2.toInt(), y2.toInt(), z2.toInt()))] = action == "on"
            }
        }

        return map
    }
}