package com.colinodell.advent2021

class Day04 (val input: String) {
   fun solvePart1(): Int {
       val result = BingoGame(input).findWinner().first()

       return result.number * result.board.sumOfUnusedSquares()
    }

    fun solvePart2(): Int {
        val result = BingoGame(input).findWinner().last()

        return result.number * result.board.sumOfUnusedSquares()
    }

    private inner class BingoGame(input: String) {
        val numbersDrawn: List<Int>
        var boards = emptySet<Board>()

        init {
            val sections = input.split("\n\n")

            numbersDrawn = sections[0].split(",").map { it.toInt() }

            for (i in 1 until sections.size) {
                boards += Board(sections[i])
            }
        }

        fun findWinner() = sequence {
            // Start calling numbers
            for (number in numbersDrawn) {
                // Mark them on each board
                for (board in boards) {
                    board.markIfExists(number)
                    if (board.isWinner()) {
                        yield(GameResult(board, number))
                        // Remove the board from future rounds
                        boards -= board
                    }
                }
            }
        }
    }

    private inner class Board(input: String) {
        val squares = mutableMapOf<Vector2, Square>()

        init {
            for (row in 0..4) {
                // Split the row into individual numbers
                val numbers = input.split("\n")[row].split(" ").filter { it !== "" }.map { it.toInt() }
                for (col in 0..4) {
                    squares[Vector2(row, col)] = Square(numbers[col])
                }
            }
        }

        fun markIfExists(number: Int) = squares.values.filter { it.number == number }.forEach { it.used = true }

        fun sumOfUnusedSquares(): Int = squares.values.filter { !it.used }.sumOf { it.number }

        fun isWinner(): Boolean {
            // Check rows and columns at the same time
            for (i in 0..4) {
                val foundInRow = (0..4).count { j -> squares[Vector2(i, j)]!!.used }
                val foundInCol = (0..4).count { j -> squares[Vector2(j, i)]!!.used }

                if (foundInRow == 5 || foundInCol == 5) {
                    return true
                }
            }

            return false
        }
    }

    private data class Square (val number: Int, var used: Boolean = false)

    private data class GameResult(val board: Board, val number: Int)
}