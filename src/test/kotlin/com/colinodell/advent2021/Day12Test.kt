package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {
    private val sample = """
        start-A
        start-b
        A-c
        A-b
        b-d
        A-end
        b-end
    """.trimIndent().trim().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day12(sample).solvePart1()
            assertThat(answer).isEqualTo(10)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day12(resourceAsListOfString("day12.txt")).solvePart1()
            assertThat(answer).isEqualTo(4775)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day12(sample).solvePart2()
            assertThat(answer).isEqualTo(36)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day12(resourceAsListOfString("day12.txt")).solvePart2()
            assertThat(answer).isEqualTo(152480)
        }
    }
}