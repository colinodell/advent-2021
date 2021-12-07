package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 7")
class Day07Test {
    private val sample = "16,1,2,0,4,2,7,1,2,14"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day07(sample).solvePart1()
            assertThat(answer).isEqualTo(37)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day07(resourceAsText("day07.txt")).solvePart1()
            assertThat(answer).isEqualTo(328187)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day07(sample).solvePart2()
            assertThat(answer).isEqualTo(168)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day07(resourceAsText("day07.txt")).solvePart2()
            assertThat(answer).isEqualTo(91257582)
        }
    }
}