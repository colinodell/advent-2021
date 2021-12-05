package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import com.colinodell.advent2021.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day05Test {
    private val sample =
        """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day05(sample).solvePart1()
            assertThat(answer).isEqualTo(5)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day05(resourceAsListOfString("day05.txt")).solvePart1()
            assertThat(answer).isEqualTo(6283)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day05(sample).solvePart2()
            assertThat(answer).isEqualTo(12)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day05(resourceAsListOfString("day05.txt")).solvePart2()
            assertThat(answer).isEqualTo(18864)
        }
    }
}