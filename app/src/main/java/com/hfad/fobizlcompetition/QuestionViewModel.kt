package com.hfad.fobizlcompetition
import androidx.lifecycle.*
import com.hfad.fobizlcompetition.data.HomeItem
import com.hfad.fobizlcompetition.db.QuestionRepository
import com.hfad.fobizlcompetition.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel() {
    private val searchQueryLiveData = MutableLiveData<String?>(null)
    private val filterLiveData = MutableLiveData<String?>(null)
    private val tagsLiveData = MutableLiveData<Set<String>>(setOf())

    val questions: LiveData<Resource<out List<HomeItem>>> = searchQueryLiveData.switchMap{ query ->
        if (query == null) {
            filterLiveData.switchMap { filter ->
                if (filter == null) {
                    // get all questions
                    repository.getQuestions().asLiveData()
                } else {
                    // do the filter
                    repository.getQuestionsFilteredByTag(filter).asLiveData()
                }
            }
        } else {
            // do search
            repository.searchData(query).asLiveData()
        }
    }.map {
        tagsLiveData.value = it.data?.fold(mutableSetOf()) { acc, question ->
            acc.addAll(question.tags)
            acc
        }
        it
    }

    val tags: LiveData<Set<String>> = tagsLiveData

    fun setFilter(filter: String?) {
        filterLiveData.value = filter
    }

    fun searchData(query: String?) {
        searchQueryLiveData.value = query
    }
}