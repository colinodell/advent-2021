package com.colinodell.advent2021

class Day16(input: String) {
    private val transmission = decodePacket(BitString(input))

    fun solvePart1() = transmission.versionSum()

    fun solvePart2() = transmission.value()

    interface Packet {
        val version: Int
        fun versionSum(): Int
        fun value(): Long
    }

    data class Literal(override val version: Int, val value: Long) : Packet {
        override fun versionSum() = version
        override fun value() = value
    }

    data class Operator(override val version: Int, private val type: Int, val subPackets: List<Packet>) : Packet
    {
        override fun versionSum() = version + subPackets.sumOf { it.versionSum() }
        override fun value() = when (type) {
            0 -> subPackets.sumOf { it.value() }
            1 -> subPackets.map { it.value() }.reduce { a, b -> a * b}
            2 -> subPackets.minOf { it.value() }
            3 -> subPackets.maxOf { it.value() }
            5 -> if (subPackets[0].value() > subPackets[1].value()) 1 else 0
            6 -> if (subPackets[0].value() < subPackets[1].value()) 1 else 0
            7 -> if (subPackets[0].value() == subPackets[1].value()) 1 else 0
            else -> throw IllegalArgumentException("Unsupported operator type: $type")
        }
    }

    companion object {
        fun decodePacket(bits: String): Packet = decodePacket(BitString(bits))

        private fun decodePacket(bits: BitString): Packet {
            val version = bits.takeInt(3)
            val type = bits.takeInt(3)

            return when (type) {
                4 -> Literal(version, parseLiteral(bits))
                else -> Operator(version, type, parseSubPackets(bits))
            }
        }

        private fun parseLiteral(bits: BitString): Long {
            var value = 0L
            do {
                val isLastNumberGroup = bits.take(1) == "0"
                value = value * 16 + bits.takeInt(4)
            } while (!isLastNumberGroup)

            return value
        }

        private fun parseSubPackets(bits: BitString): List<Packet> {
            return when (bits.takeInt(1)) {
                0 -> bits.subsequence(bits.takeInt(15)).whileNotAtEnd { decodePacket(it) }
                1 -> (0 until bits.takeInt(11)).map { decodePacket(bits) }
                else -> throw IllegalArgumentException("Unsupported operation length type")
            }
        }
    }

    private class BitString(hexString: String) {
        private var bits = hexString.map { it.digitToInt(16).toString(2).padStart(4, '0') }.joinToString("")
        private var cursor = 0

        fun take(len: Int) = bits.substring(cursor, cursor + len).also { cursor += len }
        fun takeInt(len: Int) = take(len).toInt(2)

        fun subsequence(len: Int): BitString {
            val sub = bits.substring(cursor, cursor + len)

            return BitString("").apply { bits = sub }.also { cursor += len }
        }

        fun <T> whileNotAtEnd(fn: (BitString) -> T): List<T> {
            val result = mutableListOf<T>()
            while (cursor != bits.length) {
                result.add(fn(this))
            }

            return result
        }
    }
}