package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfInt
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day01Test {
    private val sample = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day01(sample).solvePart1()
            assertThat(answer).isEqualTo(7)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day01(resourceAsListOfInt("day01.txt")).solvePart1()
            assertThat(answer).isEqualTo(1521)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day01(sample).solvePart2()
            assertThat(answer).isEqualTo(5)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day01(resourceAsListOfInt("day01.txt")).solvePart2()
            assertThat(answer).isEqualTo(1543)
        }
    }
}