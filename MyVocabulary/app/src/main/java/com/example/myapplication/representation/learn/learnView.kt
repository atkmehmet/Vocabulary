package com.example.myapplication.representation.learn

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import com.example.myapplication.comon.Resource
import com.example.myapplication.representation.newVocabulary.VocabularyState
import com.example.myapplication.data.local.VocabularyDao
import com.example.myapplication.domain.model.Vocabulary
import com.example.myapplication.domain.user_case.GetLearnVocabulary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class learnView @Inject constructor(private val getLearnVocabulary: GetLearnVocabulary) :ViewModel() {


   private var _state by mutableStateOf(LearnState())
    val state: LearnState
        get() = _state

    //private val vocabularyDao=VocabularyDb.getDaoInstance(ApplicationContent.getAppContext())
  //  private val learnVocabulary = _learnVocabulary.asStateFlow()

    init {
        lateinit var getList: List<Vocabulary>
        getLearnVocabulary().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getList = result.data ?: emptyList()
                }
            }
                _state.LearnVocabularyList= flow {
                    emit(getList)
                }


            }
        }


    fun onEvent(event: learnEvent) {
        when (event) {
            is learnEvent.findVocabulary -> {
                _state = _state.copy(
                    search = event.searchText
                )
                _state.LearnVocabularyList.map {
                    it.filter {
                        it.vocabulary.contains(_state.search)
                    }
                }

            }

        }
    }
}

