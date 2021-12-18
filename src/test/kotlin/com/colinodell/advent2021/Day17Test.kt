package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import com.colinodell.advent2021.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 17")
class Day17Test {
    private val sample = "target area: x=20..30, y=-10..-5"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day17(sample).solvePart1()
            assertThat(answer).isEqualTo(45)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day17(resourceAsText("day17.txt")).solvePart1()
            assertThat(answer).isEqualTo(23005)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day17(sample).solvePart2()
            assertThat(answer).isEqualTo(112)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day17(resourceAsText("day17.txt")).solvePart2()
            assertThat(answer).isEqualTo(2040)
        }
    }
}