package com.hfad.fobizlcompetition.di

import android.app.Application
import androidx.room.Room
import com.hfad.fobizlcompetition.api.StackOverFlowApi
import com.hfad.fobizlcompetition.db.QuestionDatabase
import com.hfad.fobizlcompetition.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideStackOverFlowApi(retrofit: Retrofit): StackOverFlowApi =
        retrofit.create(StackOverFlowApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : QuestionDatabase =
        Room.databaseBuilder(app, QuestionDatabase::class.java, "question_database")
            .build()
}