package com.example.usecase

class FizzbuzzNumberConverterUseCase {
    fun execute(
        numberPicked: Int,
        fizzBuzzConfiguration: Pair<Pair<Int, String>, Pair<Int, String>>
    ): List<String> =
        mutableListOf<String>()
            .apply {
                require(numberPicked <= 100) { "FizzBuzz app can't count beyond 100" }
                require(numberPicked > 0) { "FizzBuzz app counting starts from 1" }
                for (number in 0..numberPicked) this.add(
                    number
                        .fizzBuzzThatNumber(fizzBuzzConfiguration)
                        .fizzBuzzNumber
                )
            }.toList()
}
