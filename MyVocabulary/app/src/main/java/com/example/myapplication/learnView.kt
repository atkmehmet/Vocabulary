package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import com.example.myapplication.data.local.VocabularyDao
import com.example.myapplication.domain.model.Vocabulary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class learnView @Inject constructor(private val vocabularyDao: VocabularyDao) :ViewModel() {

     var _learnVocabulary = MutableStateFlow(VocabularyState())
    var _state by mutableStateOf(LearnState())
    val state :LearnState
      get() = _state

    //private val vocabularyDao=VocabularyDb.getDaoInstance(ApplicationContent.getAppContext())
    private val learnVocabulary=_learnVocabulary.asStateFlow()
    init {
        _learnVocabulary.update { it.copy(
            allLearnVocabulary=vocabularyDao.getLearnVocabulary().map {
                it.map {
                    Vocabulary(id = it.id, vocabulary = it.vocabulary, vocabularyMeans = it.vocabularyMeans, vocabularySentences = it.vocabularySentences)
                }
            }
        ) }
    }

    fun onEvent(event: learnEvent){
        when(event) {
            is learnEvent.findVocabulary -> {
                _state = _state.copy(
                    search = event.searchText
                )
                _learnVocabulary.update {
                    it.copy(
                        allLearnVocabulary=_learnVocabulary.value.allLearnVocabulary.map {
                            it.filter {
                                it.vocabulary.contains(_state.search,ignoreCase = false)
                            }
                        }
                    )
                }

            }

        }
    }


}