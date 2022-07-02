package com.example.fizzbuzzletmecounttothatnumber.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fizzbuzzletmecounttothatnumber.R
import com.example.fizzbuzzletmecounttothatnumber.databinding.FragmentFirstBinding
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.BUZZ_STRING
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.FIRST_INT
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.FIZZ_STRING
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.LIMIT_INT
import com.example.fizzbuzzletmecounttothatnumber.ui.activity.FieldToCapture.SECOND_INT
import com.google.android.material.textfield.TextInputEditText

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var firstInt: Int? = null
    private var secondInt: Int? = null
    private var fizz: String? = null
    private var buzz: String? = null
    private var limit: Int? = null
    private var isFormValid: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkTextChanges(
            inputField = binding.firstInt,
            fieldToCapture = FIRST_INT
        )
        checkTextChanges(
            inputField = binding.secondInt,
            fieldToCapture = SECOND_INT
        )
        checkTextChanges(
            inputField = binding.fizz,
            fieldToCapture = FIZZ_STRING
        )
        checkTextChanges(
            inputField = binding.buzz,
            fieldToCapture = BUZZ_STRING
        )
        checkTextChanges(
            inputField = binding.limit,
            fieldToCapture = LIMIT_INT
        )

        binding.buttonFirst.setOnClickListener {
            checkFormValidity()
            if (isFormValid) {
                findNavController()
                    .navigate(R.id.action_FirstFragment_to_SecondFragment,
                        Bundle().apply {
                            firstInt?.let { putInt(FIRST_INT.name, it) }
                            secondInt?.let { putInt(SECOND_INT.name, it) }
                            fizz?.let { putString(FIZZ_STRING.name, it) }
                            buzz?.let { putString(BUZZ_STRING.name, it) }
                            limit?.let {
                                putInt(
                                    LIMIT_INT.name,
                                    if (it > 100) 100 else if (it <= 10) 1 else it
                                )
                            }
                        }
                    )
            }
        }
    }

    private fun checkTextChanges(
        inputField: TextInputEditText,
        fieldToCapture: FieldToCapture
    ) {
        inputField.doAfterTextChanged { editable: Editable? ->
            (editable?.isNotEmpty() == true).let { notEmpty ->
                if (notEmpty) {
                    inputField.error = null
                    isFormValid = true
                }
                when (fieldToCapture) {
                    FIRST_INT -> firstInt = if (notEmpty) editable.toString().toInt() else null
                    SECOND_INT -> secondInt = if (notEmpty) editable.toString().toInt() else null
                    FIZZ_STRING -> fizz = if (notEmpty) editable.toString() else null
                    BUZZ_STRING -> buzz = if (notEmpty) editable.toString() else null
                    LIMIT_INT -> limit = if (notEmpty) editable.toString().toInt() else null
                }
            }
        }
    }

    private fun checkFormValidity() {
        if (firstInt == null) {
            isFormValid = false
            binding.firstInt.error = getString(R.string.mandatory_field)
        }
        if (secondInt == null) {
            isFormValid = false
            binding.secondInt.error = getString(R.string.mandatory_field)

        }
        if (fizz == null) {
            isFormValid = false
            binding.fizz.error = getString(R.string.mandatory_field)
        }
        if (buzz == null) {
            isFormValid = false
            binding.buzz.error = getString(R.string.mandatory_field)

        }
        if (limit == null) {
            isFormValid = false
            binding.limit.error = getString(R.string.mandatory_field)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
