package com.example.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class FizzbuzzNumberConverterUseCase {
    suspend fun execute(
        numberPicked: Int,
        fizzBuzzConfiguration: Pair<Pair<Int, String>, Pair<Int, String>>
    ) = CoroutineScope(Dispatchers.Default)
        .async {
            return@async mutableListOf<String>()
                .apply {
                    require(numberPicked <= 10000) { "FizzBuzz app can't count beyond 10000" }
                    for (number in 0..numberPicked) {
                        this.add(
                            number
                                .fizzBuzzThatNumber(fizzBuzzConfiguration)
                                .await()
                                .fizzBuzzNumber
                        )
                    }
                }
                .toList()

        }
}
