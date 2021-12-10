package com.colinodell.advent2021

class Day10 (private val input: List<String>) {
    private val syntax = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    private val syntaxErrorPoints = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val autocompletePoints = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    fun solvePart1() = input.sumOf { syntaxErrorScore(it) }
    fun solvePart2() = input.filter { syntaxErrorScore(it) == 0 }.map { autocompleteScore(it) }.sorted().middle()

    private fun syntaxErrorScore(line: String): Int {
        val openerQueue = mutableListOf<Char>()
        for (char in line) when {
            // Is this an opener? Those can be added at any time
            syntax.keys.contains(char) -> openerQueue.add(char)
            // Otherwise, if this is a closer that doesn't match the last opener, return the error point value
            syntax[openerQueue.removeLast()] != char -> return syntaxErrorPoints[char]!!
        }

        return 0
    }

    private fun autocompleteScore(line: String): Long {
        val openerQueue = mutableListOf<Char>()
        for (char in line) when {
            syntax.keys.contains(char) -> openerQueue.add(char)
            else -> openerQueue.removeLastOrNull()
        }

        var score: Long = 0

        while (!openerQueue.isEmpty()) {
            val lastOpener = openerQueue.removeLast()
            score = score * 5 + autocompletePoints[syntax[lastOpener]]!!
        }

        return score
    }

    private fun <T> List<T>.middle(): T {
        val middle = size / 2
        return if (size % 2 == 0) {
            get(middle - 1)
        } else {
            get(middle)
        }
    }
}