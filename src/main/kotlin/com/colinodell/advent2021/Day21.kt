package com.colinodell.advent2021

class Day21(private val playerStartingPositions: Iterable<Int>) {
    fun solvePart1() = Game(playerStartingPositions, DeterministicDice(1000)).play()

    private interface Dice {
        var timesRolled: Int
        fun roll(times: Int): Int
    }

    private class Player(private var position: Int, var score: Int = 0) {
        fun move(rolled: Int) {
            position = ((position + rolled - 1) % 10) + 1
            score += position
        }
    }

    private class Game(playerStartingPositions: Iterable<Int>, private val dice: Dice) {
        private val players = playerStartingPositions.map { Player(it) }.toList()

        fun play(): Int {
            while(true) {
                for (player in players) {
                    val rolled = dice.roll(3)
                    player.move(rolled)
                    if (player.score >= 1000) {
                        return dice.timesRolled * players.filter { it != player }.first().score
                    }
                }
            }
        }
    }

    private class DeterministicDice(private val sides: Int) : Dice {
        override var timesRolled: Int = 0
        override fun roll(times: Int) = (0 until times).sumOf { ++timesRolled }
    }
}