package com.example.fizzbuzzletmecounttothatnumber.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fizzbuzzletmecounttothatnumber.R
import com.example.fizzbuzzletmecounttothatnumber.databinding.FragmentSecondBinding
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.LIMIT_INT
import com.example.fizzbuzzletmecounttothatnumber.ui.adapter.FizzBizzListAdapter
import com.example.fizzbuzzletmecounttothatnumber.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SecondFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    private var maxValue = 100

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fizzbuzzRecyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.fizzbuzzRecyclerview.adapter = FizzBizzListAdapter()
        binding.fizzbuzzRecyclerview.isNestedScrollingEnabled = true

        maxValue = arguments?.getInt(LIMIT_INT.name) ?: 100
        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = maxValue

        binding.numberPicker.setOnValueChangedListener { picker, _, _ ->
            arguments?.let { bundle ->
                (binding.fizzbuzzRecyclerview.adapter as FizzBizzListAdapter)
                    .updateList(
                        fizzBuzzList = if (picker.value in 1..maxValue) {
                            viewModel
                                .countToLimitInFizzbuzzWay(numberPicked = picker.value, bundle)
                        } else {
                            emptyList()
                        }
                    )
            }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}