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

    data class Literal(override val version: Int, val value: Long) : Packet
    {
        override fun versionSum(): Int = version
        override fun value(): Long = value
    }

    data class Operator(override val version: Int, val subPackets: List<Packet>, val operation: (List<Packet>) -> Long) : Packet
    {
        override fun versionSum(): Int = version + subPackets.sumOf { it.versionSum() }
        override fun value(): Long = operation(subPackets)
    }

    companion object {
        fun decodePacket(bits: String): Packet = decodePacket(BitString(bits))

        private fun decodePacket(bits: BitString): Packet {
            val version = bits.next(3).toInt(2)
            val typeId = bits.next(3).toInt(2)

            return when (typeId) {
                0 -> parseOperator(version, bits) { it.sumOf { it.value() }}
                1 -> parseOperator(version, bits) { it.map { it.value() }.reduce { a, b -> a * b} }
                2 -> parseOperator(version, bits) { it.minOf { it.value() } }
                3 -> parseOperator(version, bits) { it.maxOf { it.value() } }
                4 -> parseLiteral(version, bits)
                5 -> parseOperator(version, bits) { if (it[0].value() > it[1].value()) 1 else 0 }
                6 -> parseOperator(version, bits) { if (it[0].value() < it[1].value()) 1 else 0 }
                7 -> parseOperator(version, bits) { if (it[0].value() == it[1].value()) 1 else 0 }
                else -> throw IllegalArgumentException("Unknown typeId: $typeId")
            }
        }

        private fun parseLiteral(version: Int, bits: BitString): Literal {
            var value = ""
            do {
                val isLastNumberGroup = bits.next(1) == "0"
                value += bits.next(4)
            } while (!isLastNumberGroup)

            return Literal(version, value.toLong(2))
        }

        private fun parseOperator(version: Int, bits: BitString, operation: (List<Packet>) -> Long): Operator {
            val lengthTypeId = bits.next(1).toInt(2)

            return if (lengthTypeId == 0) {
                val subPacketsBitLength = bits.next(15).toInt(2)
                val subPacketBits = bits.subsequence(subPacketsBitLength)
                val subPackets = mutableListOf<Packet>()
                while (!subPacketBits.isAtEnd()) {
                    subPackets.add(decodePacket(subPacketBits))
                }
                Operator(version, type, subPackets)
            } else {
                val subPacketsCount = bits.next(11).toInt(2)
                Operator(version, type, (0 until subPacketsCount).map { decodePacket(bits) })
            }
        }
    }

    private class BitString(hexString: String) {
        private var bits = hexString.map { it.digitToInt(16).toString(2).padStart(4, '0') }.joinToString("")
        private var cursor = 0

        fun next(len: Int): String {
            return bits.substring(cursor, cursor + len).also { cursor += len }
        }

        fun subsequence(len: Int): BitString {
            val sub = bits.substring(cursor, cursor + len)

            return BitString("").apply { bits = sub }.also { cursor += len }
        }

        fun isAtEnd(): Boolean = cursor == bits.length
    }
}