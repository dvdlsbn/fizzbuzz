package com.example.domain

data class FizzBuzzNumber(val fizzBuzzNumber: String, val isNumber: Boolean) {
    init {
        if (isNumber) {
            require(this.fizzBuzzNumber.toInt() in 0..10000) { "FizzBuzzNumber has to be between 0 and 1_000_000 " }
        }
    }
}
