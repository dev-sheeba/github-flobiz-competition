package com.hfad.fobizlcompetition.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.fobizlcompetition.databinding.FilterItemBinding

class FilterAdapter(private val listener: OnTagActionListener): ListAdapter<String, FilterAdapter.FilterViewHolder>(FilterListsComparator()) {

    interface OnTagActionListener{
        fun onTagClicked(tag: String)
    }

    inner class FilterViewHolder(private val binding: FilterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: String) {
            binding.apply {
                questionTag.text = tag

                cardQuestionTag.setOnClickListener {
                    listener.onTagClicked(tag)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val binding = FilterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class FilterListsComparator: DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

}