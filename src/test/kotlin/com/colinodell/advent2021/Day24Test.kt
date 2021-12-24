package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 24")
class Day24Test {
    private val day24 = Day24(resourceAsListOfString("day24.txt"))

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Actual Data`() {
            assertThat(day24.solvePart1()).isEqualTo("99298993199873")
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Actual Data`() {
            assertThat(day24.solvePart2()).isEqualTo("73181221197111")
        }
    }
}