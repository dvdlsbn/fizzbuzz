package com.example.usecase

import com.example.domain.FizzBuzzNumber
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class FizzbuzzNumberConverterUseCaseTest {

    private val fizzbuzzNumberConverterUseCase = FizzbuzzNumberConverterUseCase()

    @ParameterizedTest(name = "{0} should throw IllegalStateException")
    @MethodSource("exceptions")
    fun `fizzbuzzNumberConverterUseCase returns errors if wrong values`(
        numberToConvert: Int,
        expected: IllegalArgumentException
    ) {
        val exception = assertThrows<IllegalArgumentException>() {
            fizzbuzzNumberConverterUseCase.execute(numberToConvert, (3 to "fizz") to (5 to "buzz"))
        }
        assert(exception.message == expected.message)
    }

    @ParameterizedTest(name = "{0} should return {2} for configuration {1}")
    @MethodSource("numbersList")
    fun `Assert FizzBuzzThatNumber extension works for values between 0 and 100`(
        numberToConvert: Int,
        fizzBuzzConfiguration: Pair<Pair<Int, String>, Pair<Int, String>>,
        fizzBuzzNumber: FizzBuzzNumber,
        expected: List<String>
    ) {

        mockkStatic("com.example.usecase.ExtensionsKt")
        every { numberToConvert.fizzBuzzThatNumber(fizzBuzzConfiguration) } returns fizzBuzzNumber
        assertEquals(
            expected,
            fizzbuzzNumberConverterUseCase.execute(100, fizzBuzzConfiguration)
        )
    }

    @ParameterizedTest(name = "{0} should return {2} for configuration {1}")
    @MethodSource("numbers")
    fun `Mock extension in usecase works for values between 0 and 100`(
        numberToConvert: Int,
        fizzBuzzConfiguration: Pair<Pair<Int, String>, Pair<Int, String>>,
        expected: FizzBuzzNumber
    ) {
        mockk<FizzBuzzNumber> {
            assertEquals(numberToConvert.fizzBuzzThatNumber(fizzBuzzConfiguration), expected)
        }
    }

    companion object {
        @JvmStatic
        fun exceptions() = listOf(
            arrayOf(-1, IllegalArgumentException("FizzBuzz app counting starts from 1")),
            arrayOf(0, IllegalArgumentException("FizzBuzz app counting starts from 1")),
            arrayOf(120, IllegalArgumentException("FizzBuzz app can't count beyond 100"))
        )

        private val fizzBuzzConfigurationList = listOf(
            (3 to "fizz") to (5 to "buzz"),
            (2 to "deux") to (4 to "quatre"),
            (1 to "un") to (6 to "six"),
            (7 to "sept") to (8 to "huit")
        )

        @JvmStatic
        fun numbers() = mutableListOf<Array<Any>>()
            .also { list ->
                for (number in 1..100) {
                    for (fizzBuzzConfiguration in fizzBuzzConfigurationList) {
                        list.add(
                            arrayOf(
                                number,
                                fizzBuzzConfiguration,
                                getFizzBuzzConfiguration(fizzBuzzConfiguration, number)
                            )
                        )
                    }
                }
            }

        @JvmStatic
        fun numbersList() = mutableListOf<Array<Any>>()
            .also { list ->
                for (number in 1..100) {
                    for (fizzBuzzConfiguration in fizzBuzzConfigurationList) {
                        list.add(
                            arrayOf(
                                number,
                                fizzBuzzConfiguration,
                                getFizzBuzzConfiguration(fizzBuzzConfiguration, number),
                                mutableListOf<String>()
                                    .apply {
                                        for (number in 0..100) this.add(
                                            number
                                                .fizzBuzzThatNumber(fizzBuzzConfiguration)
                                                .fizzBuzzNumber
                                        )
                                    }.toList()
                            )
                        )
                    }
                }
            }

        private fun getFizzBuzzConfiguration(
            fizzBuzzConfiguration: Pair<Pair<Int, String>, Pair<Int, String>>,
            number: Int
        ) = fizzBuzzConfiguration.toList().sortedBy { it.first }
            .let { configuration ->
                FizzBuzzNumber(
                    when {
                        number == 0 -> number.toString()
                        number % (configuration[1].first * configuration[0].first) == 0 -> configuration[0].second + configuration[1].second
                        number % configuration[1].first == 0 -> configuration[1].second
                        number % configuration[0].first == 0 -> configuration[0].second
                        else -> number.toString()
                    },
                    when {
                        number == 0 -> true
                        number % (configuration[1].first * configuration[0].first) == 0 -> false
                        number % configuration[1].first == 0 -> false
                        number % configuration[0].first == 0 -> false
                        else -> true
                    }
                )
            }
    }
}
