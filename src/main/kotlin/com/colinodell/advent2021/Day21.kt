package com.colinodell.advent2021

import kotlin.math.max
import kotlin.math.min

class Day21(private val player1Start: Int, private val player2Start: Int) {
    fun solvePart1(): Int {
        var game = Game(Player(player1Start), Player(player2Start))
        val dice = DeterministicDice()
        while (game.winningScore() < 1000) {
            game = game.nextTurn(dice.roll(3))
        }

        return dice.timesRolled * game.losingScore()
    }

    fun solvePart2() = 0

    private inner class Player(val position: Int, val score: Int = 0) {
        fun move(rolled: Int): Player = (((position + rolled - 1) % 10) + 1).let { pos -> Player(pos, score + pos) }
    }

    private inner class Game(val player1: Player, val player2: Player, val player1Turn: Boolean = true) {
        fun nextTurn(rolled: Int): Game {
            return if (player1Turn) {
                Game(player1.move(rolled), player2, false)
            } else {
                Game(player1, player2.move(rolled), true)
            }
        }

        fun winningScore() = max(player1.score, player2.score)
        fun losingScore() = min(player1.score, player2.score)
    }

    private class DeterministicDice() {
        var timesRolled: Int = 0
        fun roll(times: Int) = (0 until times).sumOf { ++timesRolled }
    }
}