package com.colinodell.advent2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Vector2")
class Vector2Test {
    @Test
    fun `Constructor and Properties`() {
        val vector = Vector2(1, 2)
        assertThat(vector.x).isEqualTo(1)
        assertThat(vector.y).isEqualTo(2)
    }

    @Test
    fun `Addition`() {
        val vector = Vector2(1, 2) + Vector2(3, 4)
        assertThat(vector.x).isEqualTo(4)
        assertThat(vector.y).isEqualTo(6)
    }

    @Test
    fun `Subtraction`() {
        val vector = Vector2(4, 6) - Vector2(3, 4)
        assertThat(vector.x).isEqualTo(1)
        assertThat(vector.y).isEqualTo(2)
    }

    @Test
    fun `String Representation`() {
        val vector = Vector2(1, 2)
        assertThat(vector.toString()).isEqualTo("(1, 2)")
    }
}

@Nested
@DisplayName("Line")
class LineTest {
    @Test
    fun `Example 1`() {
        val line = Line(Vector2(1, 1), Vector2(1, 3))
        assertThat(line.points).containsExactly(
            Vector2(1, 1),
            Vector2(1, 2),
            Vector2(1, 3),
        )
        assertThat(line.isHorizontal).isFalse()
        assertThat(line.isVertical).isTrue()
        assertThat(line.isDiagonal).isFalse()
    }

    @Test
    fun `Example 2`() {
        val line = Line(Vector2(9, 7), Vector2(7, 7))
        assertThat(line.points).containsExactly(
            Vector2(9, 7),
            Vector2(8, 7),
            Vector2(7, 7),
        )
        assertThat(line.isHorizontal).isTrue()
        assertThat(line.isVertical).isFalse()
        assertThat(line.isDiagonal).isFalse()
    }

    @Test
    fun `Example 3`() {
        val line = Line(Vector2(1, 1), Vector2(3, 3))
        assertThat(line.points).containsExactly(
            Vector2(1, 1),
            Vector2(2, 2),
            Vector2(3, 3),
        )
        assertThat(line.isHorizontal).isFalse()
        assertThat(line.isVertical).isFalse()
        assertThat(line.isDiagonal).isTrue()
    }

    @Test
    fun `Example 4`() {
        val line = Line(Vector2(9, 7), Vector2(7, 9))
        assertThat(line.points).containsExactly(
            Vector2(9, 7),
            Vector2(8, 8),
            Vector2(7, 9),
        )
        assertThat(line.isHorizontal).isFalse()
        assertThat(line.isVertical).isFalse()
        assertThat(line.isDiagonal).isTrue()
    }
}