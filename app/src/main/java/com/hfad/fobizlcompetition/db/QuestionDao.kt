package com.hfad.fobizlcompetition.db

import androidx.room.*
import com.hfad.fobizlcompetition.data.HomeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions_table")
    @Transaction
    fun getAllQuestions(): Flow<List<HomeItem.Question>>

    @Query(
        """
            SELECT * FROM questions_table 
            WHERE tags LIKE '%~' || :questionTag || '~%' OR  
                tags LIKE '%~' || :questionTag OR tags LIKE :questionTag || '~%'
        """
    )
    fun getAllQuestionsByQuestionTag(questionTag: String): Flow<List<HomeItem.Question>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<HomeItem.Question>)

    @Query("SELECT * FROM questions_table Where title LIKE '%' || :query || '%' OR display_name LIKE '%' || :query || '%'")
    fun searchData(query: String): Flow<List<HomeItem.Question>>
}