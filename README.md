# FIZZBUZZ

<div align="center">
  <kbd>
    <img src="https://code.kx.com/q/img/fizzbuzz.png" />
  </kbd>
</div>

## UPDATE

- **Main is not is not up to date**, it contains the V1 version.
- Check **Fizzbuzz-count-with-coroutines-and-pager**.The new V2 version implements coroutines and pager for more efficiency.

## Description

Cute App that consists in writing all numbers from 1 to 100, and just replacing :

- All multiples of 3 by "fizz"
- All multiples of 5 by "buzz"
- And all multiples of 15 by "fizzbuzz"

## Features

- First screen Has a form that accepts five parameters : three integers int1, int2 and limit, and two strings str1 and str2.
- Second screen displays a scrollable screen with a list of strings with numbers from 1 to limit. All multiples of int1 are replaced by str1, all multiples of int2 are replaced by str2, all multiples of int1 and int2 are replaced by str1str2.

## Goals

- [x] Ready for production, stablized application with no crash whatsoever.
- [x] Easy to read, formatted and self explanatory code.
- [x] Tested, parameterized tests with more than 800 runs.

## The result
### Choices

- Multi module : easier to maintain. Only UI relies on the Android Framework. Usecase and Domain are easily interoperable with other frameworks.
- Hilt injections : to make code less coupled and respect a cleaner architecture.
- Parametrized tests: to multiply the scenarios.
- Mockk : makes easy to mock extensions function.

### Improvable

- Automatic scroll down to last number in the list
- Larger numbers.
- More rules : the two integers/strings have to be different, special characters filter, etc.
- More tests : more coverage, instrumented tests.
- Strings traduction
- A prettier app.
- A module Data including room and maybe a Firebase back-end. 
