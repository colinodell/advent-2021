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