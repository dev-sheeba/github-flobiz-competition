package com.hfad.fobizlcompetition.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


sealed class HomeItem{
    @Entity(tableName = "questions_table")
    data class Question(
        @SerializedName("question_id")
        @PrimaryKey
        @ColumnInfo(name = "question_id")
        val questionId: Long,
        @SerializedName("answer_count")
        @ColumnInfo(name = "answer_count")
        val answerCount: Int,
        @SerializedName("creation_date")
        @ColumnInfo(name = "creation_date")
        val creationDate: Int,
        @Embedded
        val owner: Owner,
        val title: String,
        @SerializedName("view_count")
        @ColumnInfo(name = "view_count")
        val viewCount: Int,
        val tags: List<String>,
        val link: String
    ): HomeItem()

    object Advertisement : HomeItem()
}
