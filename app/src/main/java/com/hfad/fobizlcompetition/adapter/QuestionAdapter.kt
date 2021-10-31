package com.hfad.fobizlcompetition.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hfad.fobizlcompetition.HomeRecyclerViewHolder
import com.hfad.fobizlcompetition.R
import com.hfad.fobizlcompetition.data.HomeItem
import com.hfad.fobizlcompetition.databinding.BannerItemBinding
import com.hfad.fobizlcompetition.databinding.QuestionItemBinding

class QuestionAdapter: ListAdapter<HomeItem, HomeRecyclerViewHolder>(ListsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when(viewType) {
            R.layout.question_item -> HomeRecyclerViewHolder.QuestionViewHolder(
                QuestionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.banner_item -> HomeRecyclerViewHolder.BannerViewHolder(
                BannerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        when (holder) {
            is HomeRecyclerViewHolder.QuestionViewHolder -> holder.bind(getItem(position) as HomeItem.Question)
            is HomeRecyclerViewHolder.BannerViewHolder -> holder.bind(getItem(position) as HomeItem.Advertisement)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HomeItem.Question -> R.layout.question_item
            is HomeItem.Advertisement -> R.layout.banner_item

        }
    }

    class ListsComparator: DiffUtil.ItemCallback<HomeItem>() {
        override fun areItemsTheSame(
            oldItem: HomeItem,
            newItem: HomeItem
        ): Boolean {
            return oldItem ==  newItem
        }

        override fun areContentsTheSame(
            oldItem: HomeItem,
            newItem: HomeItem
        ): Boolean {
            return oldItem ==  newItem
        }
    }
}