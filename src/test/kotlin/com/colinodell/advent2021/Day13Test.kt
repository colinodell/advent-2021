package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import com.colinodell.advent2021.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 13")
class Day13Test {
    private val sample = """
        6,10
        0,14
        9,10
        0,3
        10,4
        4,11
        6,0
        6,12
        4,1
        0,13
        10,12
        3,4
        3,0
        8,4
        1,10
        2,14
        8,10
        9,0
        
        fold along y=7
        fold along x=5
    """.trimIndent().trim()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day13(sample).solvePart1()
            assertThat(answer).isEqualTo(17)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day13(resourceAsText("day13.txt")).solvePart1()
            assertThat(answer).isEqualTo(669)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day13(sample).solvePart2()
            val expected = """
                #####
                #...#
                #...#
                #...#
                #####
            """.trimIndent()
            assertThat(answer).isEqualTo(expected)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day13(resourceAsText("day13.txt")).solvePart2()
            val expected = """
                #..#.####.####.####..##..#..#..##....##
                #..#.#....#.......#.#..#.#..#.#..#....#
                #..#.###..###....#..#....#..#.#.......#
                #..#.#....#.....#...#....#..#.#.......#
                #..#.#....#....#....#..#.#..#.#..#.#..#
                .##..####.#....####..##...##...##...##.
            """.trimIndent()
            assertThat(answer).isEqualTo(expected)
        }
    }
}