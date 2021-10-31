package com.hfad.fobizlcompetition

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.hfad.fobizlcompetition.data.HomeItem
import com.hfad.fobizlcompetition.databinding.BannerItemBinding
import com.hfad.fobizlcompetition.databinding.QuestionItemBinding

sealed class HomeRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    class QuestionViewHolder(private val binding: QuestionItemBinding) :
    HomeRecyclerViewHolder(binding) {
        fun bind(question: HomeItem.Question) {
            binding.apply {
                Glide.with(itemView)
                    .load(question.owner.profile_image)
                    .into(ownerImage)
                questionTitle.text = question.title
                ownerName.text = question.owner.display_name
            }
        }
    }

    class BannerViewHolder(private val binding: BannerItemBinding) :
        HomeRecyclerViewHolder(binding,) {
        fun bind(banner: HomeItem.Advertisement) {
            binding.apply {

            }
        }
    }
}