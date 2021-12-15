package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import com.colinodell.advent2021.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 15")
class Day15Test {
    private val sample = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581
    """.trimIndent().trim().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day15(sample).solvePart1()
            assertThat(answer).isEqualTo(40)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day15(resourceAsListOfString("day15.txt")).solvePart1()
            assertThat(answer).isEqualTo(366)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day15(sample).solvePart2()
            assertThat(answer).isEqualTo(315)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day15(resourceAsListOfString("day15.txt")).solvePart2()
            assertThat(answer).isEqualTo(2829)
        }
    }
}