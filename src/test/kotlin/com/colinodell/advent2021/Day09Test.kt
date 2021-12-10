package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import com.colinodell.advent2021.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day09Test {
    private val sample = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent().trim().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day09(sample).solvePart1()
            assertThat(answer).isEqualTo(15)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day09(resourceAsListOfString("day09.txt")).solvePart1()
            assertThat(answer).isEqualTo(436)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day09(sample).solvePart2()
            assertThat(answer).isEqualTo(1134)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day09(resourceAsListOfString("day09.txt")).solvePart2()
            assertThat(answer).isEqualTo(1317792)
        }
    }
}