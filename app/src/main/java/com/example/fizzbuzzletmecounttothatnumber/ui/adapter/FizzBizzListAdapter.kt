package com.example.fizzbuzzletmecounttothatnumber.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fizzbuzzletmecounttothatnumber.databinding.SimpleItemBinding
import com.example.fizzbuzzletmecounttothatnumber.ui.adapter.FizzBizzListAdapter.FizzBizzViewHolder

class FizzBizzListAdapter : PagingDataAdapter<String, FizzBizzViewHolder>(DIFF_CALLBACK) {

    class FizzBizzViewHolder(val binding: SimpleItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem.hashCode() == newItem.hashCode()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FizzBizzViewHolder {
        return FizzBizzViewHolder(
            SimpleItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FizzBizzViewHolder, position: Int) {
        holder.binding.textview.text = getItem(position)
    }

}