package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
class Day11Test {
    private val sample = """
        5483143223
        2745854711
        5264556173
        6141336146
        6357385478
        4167524645
        2176841721
        6882881134
        4846848554
        5283751526
    """.trimIndent().trim().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day11(sample).solvePart1()
            assertThat(answer).isEqualTo(1656)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day11(resourceAsListOfString("day11.txt")).solvePart1()
            assertThat(answer).isEqualTo(1615)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day11(sample).solvePart2()
            assertThat(answer).isEqualTo(195)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day11(resourceAsListOfString("day11.txt")).solvePart2()
            assertThat(answer).isEqualTo(249)
        }
    }
}