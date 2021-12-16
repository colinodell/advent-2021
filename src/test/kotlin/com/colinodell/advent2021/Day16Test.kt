package com.colinodell.advent2021

import com.colinodell.advent2021.Resources.resourceAsListOfString
import com.colinodell.advent2021.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 16")
class Day16Test {
    @Nested
    @DisplayName("Packet Decoding")
    inner class PacketDecoding {
        @Test
        fun `Decodes literal packet`() {
            val packet = Day16.decodePacket("D2FE28")
            assertThat(packet.version).isEqualTo(6)
            assertThat(packet is Day16.Literal && packet.value == 2021L).isTrue()
        }

        @Test
        fun `Decodes Length Type 0 Operator Packets`() {
            val packet = Day16.decodePacket("38006F45291200")
            assertThat(packet.version).isEqualTo(1)
            assertThat(packet).isInstanceOf(Day16.Operator::class.java)
            if (packet is Day16.Operator) {
                assertThat(packet.subPackets.size).isEqualTo(2)
                val subPacket0 = packet.subPackets[0]
                assertThat(subPacket0 is Day16.Literal && subPacket0.value == 10L).isTrue
                val subPacket1 = packet.subPackets[1]
                assertThat(subPacket1 is Day16.Literal && subPacket1.value == 20L).isTrue
            }
        }

        @Test
        fun `Decodes Length Type 1 Operator Packets`() {
            val packet = Day16.decodePacket("EE00D40C823060")
            assertThat(packet.version).isEqualTo(7)
            assertThat(packet).isInstanceOf(Day16.Operator::class.java)
            if (packet is Day16.Operator) {
                assertThat(packet.subPackets.size).isEqualTo(3)
                val subPacket0 = packet.subPackets[0]
                assertThat(subPacket0 is Day16.Literal && subPacket0.value == 1L).isTrue
                val subPacket1 = packet.subPackets[1]
                assertThat(subPacket1 is Day16.Literal && subPacket1.value == 2L).isTrue
                val subPacket2 = packet.subPackets[2]
                assertThat(subPacket2 is Day16.Literal && subPacket2.value == 3L).isTrue
            }
        }

    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day16("A0016C880162017C3686B18A3D4780").solvePart1()
            assertThat(answer).isEqualTo(31)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day16(resourceAsText("day16.txt")).solvePart1()
            assertThat(answer).isEqualTo(897)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches Sample Data`() {
            val answer = Day16("9C0141080250320F1802104A08").solvePart2()
            assertThat(answer).isEqualTo(1L)
        }

        @Test
        fun `Matches Actual Data`() {
            val answer = Day16(resourceAsText("day16.txt")).solvePart2()
            assertThat(answer).isEqualTo(9485076995911L)
        }
    }
}