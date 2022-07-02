package com.example.fizzbuzzletmecounttothatnumber.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fizzbuzzletmecounttothatnumber.R
import com.example.fizzbuzzletmecounttothatnumber.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setTitle(R.string.app_name)
        binding.toolbar.setSubtitle(R.string.let_me_count_that_number)

    }
}

enum class FieldToCapture {
    FIRST_INT, SECOND_INT, FIZZ_STRING, BUZZ_STRING, LIMIT_INT
}