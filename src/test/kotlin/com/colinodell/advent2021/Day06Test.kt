package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfInt
import com.colinodell.advent2021.Resources.resourceAsListOfString
import com.colinodell.advent2021.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 6")
class Day06Test {
    private val sample = "3,4,3,1,2"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day06(sample).solvePart1()
            assertThat(answer).isEqualTo(5934)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day06(resourceAsText("day06.txt")).solvePart1()
            assertThat(answer).isEqualTo(352195)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day06(sample).solvePart2()
            assertThat(answer).isEqualTo(26984457539)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day06(resourceAsText("day06.txt")).solvePart2()
            assertThat(answer).isEqualTo(1600306001288)
        }
    }
}