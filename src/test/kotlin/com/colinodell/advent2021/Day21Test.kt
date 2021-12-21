package com.colinodell.advent2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 21")
class Day21Test {
    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            assertThat(Day21(4, 8).solvePart1()).isEqualTo(739785)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day21(6, 8).solvePart1()
            assertThat(answer).isEqualTo(757770)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            assertThat(Day21(4, 8).solvePart2()).isEqualTo(444356092776315)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day21(6, 8).solvePart2()
            assertThat(answer).isEqualTo(712381680443927)
        }
    }
}