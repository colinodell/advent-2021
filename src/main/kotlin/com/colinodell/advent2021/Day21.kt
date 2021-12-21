package com.colinodell.advent2021

import java.util.Stack
import kotlin.math.max
import kotlin.math.min

class Day21(private val player1Start: Int, private val player2Start: Int) {
    val universesPerRoll = arrayOf(0, 0, 0, 1, 3, 6, 7, 6, 3, 1)

    fun solvePart1(): Int {
        var game = Game(Player(player1Start), Player(player2Start))
        val dice = DeterministicDice()
        while (game.winningScore() < 1000) {
            game = game.nextTurn(dice.roll(3))
        }

        return dice.timesRolled * game.losingScore()
    }

    fun solvePart2(): Long {
        val games = Stack<Game>()
        games.push(Game(Player(player1Start), Player(player2Start)))
        var winCountPlayer1 = 0L
        var winCountPlayer2 = 0L

        while (!games.empty()) {
            val game = games.pop()
            for (rolled in 3..9) {
                val nextGame = game.nextTurn(rolled)
                if (nextGame.winningScore() < 21) {
                    games.push(nextGame)
                    continue
                }

                if (nextGame.player1Wins()) {
                    winCountPlayer1 += nextGame.universes
                } else {
                    winCountPlayer2 += nextGame.universes
                }
            }
        }

        return max(winCountPlayer1, winCountPlayer2)
    }


    private inner class Player(val position: Int, val score: Int = 0) {
        fun move(rolled: Int): Player = (((position + rolled - 1) % 10) + 1).let { pos -> Player(pos, score + pos) }
    }

    private inner class Game(val player1: Player, val player2: Player, val player1Turn: Boolean = true, val universes: Long = 1) {
        fun nextTurn(rolled: Int): Game {
            val newUniverses = if (rolled < universesPerRoll.size) {
                universes * universesPerRoll[rolled]
            } else {
                universes
            }

            return if (player1Turn) {
                Game(player1.move(rolled), player2, false, newUniverses)
            } else {
                Game(player1, player2.move(rolled), true, newUniverses)
            }
        }

        fun winningScore() = max(player1.score, player2.score)
        fun losingScore() = min(player1.score, player2.score)
        fun player1Wins() = player1.score > player2.score
    }

    private class DeterministicDice() {
        var timesRolled: Int = 0
        fun roll(times: Int) = (0 until times).sumOf { ++timesRolled }
    }
}