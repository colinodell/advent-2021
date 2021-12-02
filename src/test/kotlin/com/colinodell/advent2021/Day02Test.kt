package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day02Test {
    private val sample = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day02(sample).solvePart1()
            assertThat(answer).isEqualTo(150)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day02(resourceAsListOfString("day02.txt")).solvePart1()
            assertThat(answer).isEqualTo(1451208)
        }
    }
}