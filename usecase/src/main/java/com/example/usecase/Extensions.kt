package com.example.usecase

import com.example.domain.FizzBuzzNumber

fun Int.fizzBuzzThatNumber(fizzBuzzConfiguration: Pair<Pair<Int, String>, Pair<Int, String>>): FizzBuzzNumber {
    val sortedList = fizzBuzzConfiguration.toList().sortedBy { it.first }
    val (firstInt, fizz) = sortedList[1]
    val (secontInt, buzz) = sortedList[0]


    return when {
        this == 0 -> FizzBuzzNumber(this.toString(), true)
        this % (secontInt * firstInt) == 0 -> FizzBuzzNumber(buzz + fizz, false)
        this % firstInt == 0 -> FizzBuzzNumber(fizz, false)
        this % secontInt == 0 -> FizzBuzzNumber(buzz, false)
        else -> FizzBuzzNumber(this.toString(), true)
    }
}
