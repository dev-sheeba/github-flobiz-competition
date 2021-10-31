package com.hfad.fobizlcompetition.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hfad.fobizlcompetition.data.HomeItem

@Database(entities = [HomeItem.Question::class], version = 1)
abstract class QuestionDatabase() : RoomDatabase(){
    abstract fun questionsDao() : QuestionDao
}