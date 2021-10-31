package com.hfad.fobizlcompetition.data

import com.google.gson.annotations.SerializedName


sealed class HomeItem{
    data class Question(
        @SerializedName("question_id")
        val questionId: Int,
        @SerializedName("answer_count")
        val answerCount: Int,
        @SerializedName("creation_date")
        val creationDate: Int,
        @SerializedName("view_count")
        val title: String,
        val viewCount: Int,
        val link: String,
        val owner: Owner,
        val tags: List<String>
    ): HomeItem()

    object Advertisement : HomeItem()
}
