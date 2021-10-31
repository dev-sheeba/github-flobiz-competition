package com.hfad.fobizlcompetition.db

import androidx.room.TypeConverter

class QuestionTagConverter {
    @TypeConverter
    fun convertToString(tags: List<String>): String {
        return tags.joinToString("~")
    }

    @TypeConverter
    fun convertToListOfString(tags: String): List<String> {
        return tags.split("~")
    }
}
