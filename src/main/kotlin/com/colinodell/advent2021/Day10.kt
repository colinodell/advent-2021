package com.colinodell.advent2021

class Day10 (private val input: List<String>) {
    private val syntax = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    private val syntaxErrorPoints = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val autocompletePoints = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    fun solvePart1() = input.sumOf { syntaxErrorScore(it) }
    fun solvePart2() = input.filter { syntaxErrorScore(it) == 0 }.map { autocompleteScore(it) }.sorted().middle()

    fun syntaxErrorScore(line: String): Int {
        val openerQueue = mutableListOf<Char>()
        for (char in line) {
            // New openers can be added at any time
            if (syntax.keys.contains(char)) {
                openerQueue.add(char)
                continue
            }

            // Closers must match the last opener
            val lastOpener = openerQueue.removeLastOrNull()
            if (lastOpener == null || syntax[lastOpener] != char) {
                return syntaxErrorPoints[char]!!
            }
        }

        return 0
    }

    fun autocompleteScore(line: String): Long {
        val openerQueue = mutableListOf<Char>()
        for (char in line) {
            // New openers can be added at any time
            if (syntax.keys.contains(char)) {
                openerQueue.add(char)
                continue
            }

            openerQueue.removeLastOrNull()
        }

        var score: Long = 0

        while (!openerQueue.isEmpty()) {
            val lastOpener = openerQueue.removeLast()
            score *= 5
            score += autocompletePoints[syntax[lastOpener]]!!
        }

        return score
    }

    fun <T> List<T>.middle(): T {
        val middle = size / 2
        return if (size % 2 == 0) {
            get(middle - 1)
        } else {
            get(middle)
        }
    }
}