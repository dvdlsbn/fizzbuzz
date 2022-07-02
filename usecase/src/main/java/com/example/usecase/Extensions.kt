package com.example.usecase

import com.example.domain.FizzBuzzNumber
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

fun Int.fizzBuzzThatNumber(fizzBuzzConfiguration: Pair<Pair<Int, String>, Pair<Int, String>>)
        : Deferred<FizzBuzzNumber> {
    val currentNumber = this
    return CoroutineScope(Dispatchers.Default).async {
        val sortedList = fizzBuzzConfiguration.toList().sortedBy { it.first }
        val (firstInt, fizz) = sortedList[1]
        val (secontInt, buzz) = sortedList[0]

        return@async when {
            currentNumber == 0 -> FizzBuzzNumber(currentNumber.toString(), true)
            currentNumber % (secontInt * firstInt) == 0 -> FizzBuzzNumber(
                buzz + fizz,
                false
            )
            currentNumber % firstInt == 0 -> FizzBuzzNumber(fizz, false)
            currentNumber % secontInt == 0 -> FizzBuzzNumber(buzz, false)
            else -> FizzBuzzNumber(currentNumber.toString(), true)
        }
    }
}
