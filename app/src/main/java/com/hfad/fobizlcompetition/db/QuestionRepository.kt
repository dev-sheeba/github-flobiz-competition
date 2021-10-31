package com.hfad.fobizlcompetition.db

import android.util.Log
import androidx.room.withTransaction
import com.hfad.fobizlcompetition.api.StackOverFlowApi
import com.hfad.fobizlcompetition.data.HomeItem
import com.hfad.fobizlcompetition.utils.Resource
import com.hfad.fobizlcompetition.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: StackOverFlowApi,
    private val db: QuestionDatabase,
) {
    private val questionDao = db.questionsDao()

    fun getQuestions(): Flow<Resource<List<HomeItem.Question>>> {
        return networkBoundResource<List<HomeItem.Question>, List<HomeItem.Question>>(
            fetch = {
                api.getQuestions().items
            },
            saveFetchResult = {
                db.withTransaction {
                    questionDao.insertQuestions(it)
                }
            },
            query = {
                questionDao.getAllQuestions()
            },
            shouldFetch = {
                true
            }
        )
    }

    fun getQuestionsFilteredByTag(tag: String): Flow<Resource<List<HomeItem.Question>>> {
        return questionDao.getAllQuestionsByQuestionTag(tag).map {
            Log.d("Test", "Filter Testing")
            Resource.Success(it)
        }.catch {
            Log.e("Test2", "Testing2", it)
        }
    }

    fun searchData(query:String): Flow<Resource<List<HomeItem.Question>>> {
        return questionDao.searchData(query).map { Resource.Success(it) }
    }
}