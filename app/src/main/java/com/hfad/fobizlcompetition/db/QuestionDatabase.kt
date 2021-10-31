package com.hfad.fobizlcompetition.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hfad.fobizlcompetition.data.HomeItem

@Database(entities = [HomeItem.Question::class], version = 1)
@TypeConverters(QuestionTagConverter::class)
abstract class QuestionDatabase() : RoomDatabase(){

    abstract fun questionsDao() : QuestionDao
}