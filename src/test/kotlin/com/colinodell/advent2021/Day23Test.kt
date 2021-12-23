package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 23")
class Day23Test {
    private val part1Sample = """
        #############
        #...........#
        ###B#C#B#D###
          #A#D#C#A#
          #########
    """.trimIndent().trim().split("\n")

    private val part2Sample = """
        #############
        #...........#
        ###B#C#B#D###
          #D#C#B#A#
          #D#B#A#C#
          #A#D#C#A#
          #########
    """.trimIndent().trim().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day23(part1Sample).solve()
            assertThat(answer).isEqualTo(12521)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day23(resourceAsListOfString("day23.txt")).solve()
            assertThat(answer).isEqualTo(13520)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day23(part2Sample).solve()
            assertThat(answer).isEqualTo(44169)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day23(resourceAsListOfString("day23b.txt")).solve()
            assertThat(answer).isEqualTo(48708)
        }
    }
}