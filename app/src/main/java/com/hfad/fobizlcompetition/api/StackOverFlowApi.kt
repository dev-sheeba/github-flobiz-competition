package com.hfad.fobizlcompetition.api

import com.hfad.fobizlcompetition.data.StackQuestionResponse
import com.hfad.fobizlcompetition.utils.Constants.Companion.API_KEY
import com.hfad.fobizlcompetition.utils.Constants.Companion.SITE
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverFlowApi {
    @GET("/2.2/questions")
    suspend fun getQuestions(
        @Query("key")
        key: String = API_KEY,
        @Query("site")
        site: String = SITE
    ): StackQuestionResponse
}