package com.example.domain

data class FizzBuzzNumber(val fizzBuzzNumber: String, val isNumber: Boolean) {
    init {
        if (isNumber) {
            require(this.fizzBuzzNumber.toInt() in 0..100) { "FizzBuzzNumber has to be between 0 and 100 " }
        }
    }
}
