package com.hfad.fobizlcompetition

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.hfad.fobizlcompetition.adapter.QuestionAdapter
import com.hfad.fobizlcompetition.data.HomeItem
import com.hfad.fobizlcompetition.databinding.BannerItemBinding
import com.hfad.fobizlcompetition.databinding.QuestionItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed class HomeRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    class QuestionViewHolder(private val binding: QuestionItemBinding) :
    HomeRecyclerViewHolder(binding) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(question: HomeItem.Question, listener: QuestionAdapter.OnLinkClickListener) {
            binding.apply {
                Glide.with(itemView)
                    .load(question.owner.profile_image)
                    .into(ownerImage)
                questionTitle.text = question.title
                ownerName.text = question.owner.display_name

                val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                val timestamp = question.creationDate.toLong() // timestamp in Long
                val timestampAsDateString = DateTimeFormatter.ISO_INSTANT
                    .format(java.time.Instant.ofEpochSecond(timestamp))
                val date = LocalDate.parse(timestampAsDateString, secondApiFormat)
                postedDate.text = date.toString()

                questionItemCard.setOnClickListener {
                    listener.onLinkClicked(question.link)
                }
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