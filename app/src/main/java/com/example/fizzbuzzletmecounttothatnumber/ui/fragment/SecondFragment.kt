package com.example.fizzbuzzletmecounttothatnumber.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fizzbuzzletmecounttothatnumber.R
import com.example.fizzbuzzletmecounttothatnumber.databinding.FragmentSecondBinding
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.LIMIT_INT
import com.example.fizzbuzzletmecounttothatnumber.ui.adapter.FizzBizzListAdapter
import com.example.fizzbuzzletmecounttothatnumber.ui.adapter.paging.MyPagingSource
import com.example.fizzbuzzletmecounttothatnumber.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val NUMBER_OF_ITEMS_IN_PAGE = 10
private const val THREE_COLUMN = 3

@AndroidEntryPoint
class SecondFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    private var maxValue = 10000

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

        binding.fizzbuzzRecyclerview.layoutManager =
            GridLayoutManager(requireContext(), THREE_COLUMN)
        binding.fizzbuzzRecyclerview.adapter = FizzBizzListAdapter()
        binding.fizzbuzzRecyclerview.isNestedScrollingEnabled = true

        maxValue = arguments?.getInt(LIMIT_INT.name) ?: 10000
        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = maxValue

        binding.numberPicker.setOnValueChangedListener { picker, _, _ ->
            arguments
                ?.let { bundle ->
                    CoroutineScope(Dispatchers.Default)
                        .launch {
                            viewModel
                                .countToLimitInFizzbuzzWayAsync(numberPicked = picker.value, bundle)
                        }
                }

            viewModel
                .fizzbuzzlist
                .observe(viewLifecycleOwner) { fizzbuzzList ->
                    CoroutineScope(Dispatchers.Main)
                        .launch {
                            Pager(
                                config = PagingConfig(
                                    pageSize = NUMBER_OF_ITEMS_IN_PAGE,
                                    enablePlaceholders = true
                                ),
                                pagingSourceFactory = { MyPagingSource(fizzbuzzList) })
                                .flow
                                .collect {
                                    (binding.fizzbuzzRecyclerview.adapter as FizzBizzListAdapter)
                                        .submitData(it)


                                }
                            binding.fizzbuzzRecyclerview.scrollToPosition(fizzbuzzList.size - 1)
                        }
                }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController()
                .navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}