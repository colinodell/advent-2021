package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 3")
class Day03Test {
    private val sample = listOf("00100","11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day03(sample).solvePart1()
            assertThat(answer).isEqualTo(198)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day03(resourceAsListOfString("day03.txt")).solvePart1()
            assertThat(answer).isEqualTo(3009600)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day03(sample).solvePart2()
            assertThat(answer).isEqualTo(230)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day03(resourceAsListOfString("day03.txt")).solvePart2()
            assertThat(answer).isEqualTo(6940518)
        }
    }
}