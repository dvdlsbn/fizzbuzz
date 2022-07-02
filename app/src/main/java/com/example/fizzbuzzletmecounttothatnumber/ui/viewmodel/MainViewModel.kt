package com.example.fizzbuzzletmecounttothatnumber.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.BUZZ_STRING
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.FIRST_INT
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.FIZZ_STRING
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.SECOND_INT
import com.example.usecase.FizzbuzzNumberConverterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val FIZZ = "fizz"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fizzbuzzNumberConverterUseCase: FizzbuzzNumberConverterUseCase
) :
    ViewModel() {

    private val _fizzbuzzlist = MutableLiveData<List<String>>()
    val fizzbuzzlist: LiveData<List<String>> = _fizzbuzzlist

    fun countToLimitInFizzbuzzWayAsync(numberPicked: Int, fizzBuzzConfiguration: Bundle) =

        viewModelScope.launch(Dispatchers.Default) {
            _fizzbuzzlist.postValue(
                fizzbuzzNumberConverterUseCase.execute(
                    numberPicked = numberPicked,
                    fizzBuzzConfiguration = Pair(
                        Pair(
                            fizzBuzzConfiguration.getInt(FIRST_INT.name),
                            fizzBuzzConfiguration.getString(FIZZ_STRING.name) ?: FIZZ
                        ),
                        Pair(
                            fizzBuzzConfiguration.getInt(SECOND_INT.name),
                            fizzBuzzConfiguration.getString(BUZZ_STRING.name) ?: FIZZ
                        )
                    )
                )
                    .await()
            )
        }
}
