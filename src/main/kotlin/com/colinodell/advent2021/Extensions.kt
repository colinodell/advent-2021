package com.colinodell.advent2021

fun <T> Iterable<T>.permutationPairs(): Sequence<Pair<T, T>> = sequence {
    for (i in withIndex()) {
        for (j in withIndex()) {
            if (i.index < j.index) {
                yield(Pair(i.value, j.value))
            }
        }
    }
}

fun <T> Sequence<Pair<T,T>>.includingReversePairs(): Sequence<Pair<T,T>> = sequence {
    forEach {
        yield(Pair(it.first, it.second))
        yield(Pair(it.second, it.first))
    }
}

fun <T,R> T?.ifNotNull(block: (T) -> R) {
    if (this != null) {
        block(this)
    }
}

fun <T,R> Iterable<T>?.mapIfNotNull(block: (T) -> R): List<R> {
    if (this == null) {
        return emptyList()
    }
    return map(block)
}

suspend fun <T : Any> SequenceScope<T>.yieldIfNotNull(t: T?) = if (t != null) yield(t) else Unit

fun String.replaceAt(index: Int, char: Char) =
    this.substring(0, index) + char + this.substring(index + 1)

fun String.replaceAt(index: Int, length: Int, replacement: String) =
    this.substring(0, index) + replacement + this.substring(index + length)