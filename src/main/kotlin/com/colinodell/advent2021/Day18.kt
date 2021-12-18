package com.colinodell.advent2021

class Day18 (private val input: List<String>) {

    fun solvePart1() = sumAndFindMagnitude(input)
    fun solvePart2() = getHighestMagnitudeOfTwo(input)

    data class SnailfishPairElement(var value: Int, var depth: Int)

    private fun parse(input: String): MutableList<SnailfishPairElement> {
        var depth = 0

        val result = mutableListOf<SnailfishPairElement>()
        for (char in input) {
            when (char) {
                '[' -> depth++
                ']' -> depth--
                ',' -> continue
                else -> result.add(SnailfishPairElement(Character.getNumericValue(char), depth))
            }
        }

        return result
    }

    private fun parseAndReduce(lines: Iterable<String>): List<SnailfishPairElement> {
        val lines = lines.map { parse(it) }.toMutableList()

        while (explodeIfNeeded(lines.first()) || splitIfNeeded(lines.first()) || addNextLineIfNeeded(lines)) {
            // keep reducing
        }

        return lines.first()
    }

    private fun explodeIfNeeded(current: MutableList<SnailfishPairElement>): Boolean {
        val toExplode = current.withIndex().firstOrNull { it.value.depth > 4 } ?: return false

        // Add the pair's left value to the first regular number on the left, if it exists
        if (toExplode.index > 0) {
            current[toExplode.index-1].value += toExplode.value.value
        }

        // Add the pair's right value to the first regular number on the right, if it exists
        if (toExplode.index + 2 < current.size) {
            current[toExplode.index+2].value += current[toExplode.index+1].value
        }

        // Replace the exploded pair with 0
        current[toExplode.index].apply { value = 0; depth-- }
        current.removeAt(toExplode.index + 1)

        return true
    }

    private fun splitIfNeeded(current: MutableList<SnailfishPairElement>): Boolean {
        val toSplit = current.withIndex().firstOrNull { it.value.value >= 10 } ?: return false

        toSplit.value.depth++
        val newPair = SnailfishPairElement(toSplit.value.value / 2 + toSplit.value.value % 2, toSplit.value.depth)
        toSplit.value.value /= 2

        current.add(toSplit.index + 1, newPair)

        return true
    }

    private fun addNextLineIfNeeded(lines: MutableList<MutableList<SnailfishPairElement>>): Boolean {
        if (lines.size == 1) {
            return false
        }

        with(lines.first()) {
            addAll(lines.removeAt(1))
            forEach { it.depth++ }
        }

        return true
    }

    private fun sumAndFindMagnitude(input: List<String>): Int {
        val numbers = parseAndReduce(input).toMutableList()

        for (depth in 4 downTo 0) {
            while (numbers.size > 1 && reduceMagnitudeIfNeeded(numbers, depth)) {
                // keep reducing
            }
        }

        return numbers[0].value
    }

    private fun reduceMagnitudeIfNeeded(numbers: MutableList<SnailfishPairElement>, depth: Int): Boolean
    {
        val element = numbers.withIndex().firstOrNull { it.value.depth == depth } ?: return false

        element.value.depth--
        element.value.value = 3 * element.value.value + 2 * numbers[element.index + 1].value
        numbers.removeAt(element.index + 1)

        return true
    }

    private fun getHighestMagnitudeOfTwo(input: List<String>) =
        input
            .permutationPairs()
            .includingReversePairs()
            .maxOf { sumAndFindMagnitude(listOf(it.first, it.second)) }
}