package com.example.fizzbuzzletmecounttothatnumber.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fizzbuzzletmecounttothatnumber.databinding.SimpleItemBinding
import com.example.fizzbuzzletmecounttothatnumber.ui.adapter.FizzBizzListAdapter.FizzBizzViewHolder

class FizzBizzListAdapter :
    RecyclerView.Adapter<FizzBizzViewHolder>() {

    private val fizzBuzzList: ArrayList<String> = arrayListOf()
    private val oldFizzBuzzList: ArrayList<String> = arrayListOf()

    class FizzBizzViewHolder(val binding: SimpleItemBinding) : RecyclerView.ViewHolder(binding.root)

    class FizzBUzzDiffCallBack(
        private val oldList: List<String>,
        private val newList: List<String>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]
    }

    fun updateList(fizzBuzzList: List<String>) {
        oldFizzBuzzList.apply {
            clear()
            addAll(this@FizzBizzListAdapter.fizzBuzzList)
        }
        this.fizzBuzzList.apply {
            clear()
            addAll(fizzBuzzList)
        }
        DiffUtil
            .calculateDiff(FizzBUzzDiffCallBack(oldFizzBuzzList, fizzBuzzList))
            .dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FizzBizzViewHolder =
        FizzBizzViewHolder(
            SimpleItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FizzBizzViewHolder, position: Int) {
        holder.binding.textview.text = this.fizzBuzzList[position]
    }

    override fun getItemCount(): Int = fizzBuzzList.size
}
