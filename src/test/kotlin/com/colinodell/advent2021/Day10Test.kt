package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import com.colinodell.advent2021.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {
    private val sample = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """.trimIndent().trim().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day10(sample).solvePart1()
            assertThat(answer).isEqualTo(26397)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day10(resourceAsListOfString("day10.txt")).solvePart1()
            assertThat(answer).isEqualTo(318099)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day10(sample).solvePart2()
            assertThat(answer).isEqualTo(288957)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day10(resourceAsListOfString("day10.txt")).solvePart2()
            assertThat(answer).isEqualTo(2389738699)
        }
    }
}