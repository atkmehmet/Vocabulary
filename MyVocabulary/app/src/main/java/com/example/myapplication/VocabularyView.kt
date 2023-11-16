package com.example.myapplication

import android.database.SQLException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.VocabularyDao
import com.example.myapplication.data.local.VocabularyEntity
import com.example.myapplication.domain.model.Vocabulary
import com.example.myapplication.representation.newVocabulary.VocabularyAddState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabularyView @Inject constructor(private  val vocabularyDao: VocabularyDao)  :ViewModel(){


    private  var _vocabularyState by mutableStateOf(VocabularyAddState())
    val   vocabularyState: VocabularyAddState
       get() =_vocabularyState
  //  private var _allVocabulary by mutableStateOf(VocabularyState())

  private var _allVocabulary = MutableStateFlow(VocabularyState())
   val allVocabulary=_allVocabulary.asStateFlow()


    //private  val vocabularyDao=VocabularyDb.getDaoInstance(ApplicationContent.getAppContext())

    init {
        getVocabulary()
    }





    fun  onEvent(event:VocabularyEvent){

        when(event){
         is  VocabularyEvent.vocabulary->
         {

               _vocabularyState=_vocabularyState.copy(
                   vocabulary = event.vocabulary
               )



         }
            is VocabularyEvent.vocabularySearch->{
                _vocabularyState=_vocabularyState.copy(
                    findVocabulary= event.searchText
                )
                _allVocabulary.update {
                    it.copy(
                        allVocabulary = _allVocabulary.value.allVocabulary.map {
                            it.filter {
                                it.vocabulary.contains(_vocabularyState.findVocabulary,ignoreCase = false)
                            }
                        }
                    )
                }
            }

            is  VocabularyEvent.vocabularyMeans->
            {
                _vocabularyState=_vocabularyState.copy(
                    vocabularyMeans = event.vocabularyMeans,
                )
            }
            is VocabularyEvent.vocabularySentences->
            {
                _vocabularyState=_vocabularyState.copy(
                    vocabularySentences = event.vocabularySentences
                )
            }
            is VocabularyEvent.vocabularyEdit->{
                viewModelScope.launch {
                     val entity   =vocabularyDao.getVocabulary(event.id)
                    try {
                        _vocabularyState=_vocabularyState.copy(
                            vocabulary = entity.vocabulary,
                            vocabularyMeans = entity.vocabularyMeans,
                            vocabularySentences = entity.vocabularySentences,
                            id = entity.id,
                            dialogShowHide = true
                        )
                    }
                    catch (e:Exception){
                        _vocabularyState=_vocabularyState.copy(
                            vocabularyError = e.toString()
                        )
                    }

                }
            }
            is VocabularyEvent.vocabularyLearn->{
                 _vocabularyState=_vocabularyState.copy(
                     idValue = event.id,
                     dialogLearn = true
                 )

            }
            VocabularyEvent.vocabularySubmit->
            {
                if(_vocabularyState.id!=0){

                    EditVocabulary()
                }
                else
                {
                    AddVocabulary()
                }
                clearValues()

            }
            VocabularyEvent.ShowDialog->
            {
               _vocabularyState=_vocabularyState.copy(
                   dialogShowHide = true
               )
            }
            VocabularyEvent.HideDialog->
            {
                _vocabularyState=_vocabularyState.copy(
                    dialogShowHide = false
                )
            }

            VocabularyEvent.LearnDialogHide->{
                _vocabularyState=_vocabularyState.copy(
                    dialogLearn = false
                )
            }
            VocabularyEvent.changeVocabularyPage->{
                viewModelScope.launch {
                    vocabularyDao.LearnVocabulary(_vocabularyState.idValue)
                }
                getVocabulary()
            }

        }
    }

    fun AddVocabulary(){

        var insertVocabulary= VocabularyEntity(
            0,
            _vocabularyState.vocabulary,
            _vocabularyState.vocabularyMeans,
            _vocabularyState.vocabularySentences
        )
        viewModelScope.launch {
            try {
                vocabularyDao.insertVocabulary(insertVocabulary)
                _vocabularyState=_vocabularyState.copy(
                    vocabularyCount = vocabularyDao.getCountVocabulary()
                )
               }
            catch (e: SQLException)
            {
                  _vocabularyState=_vocabularyState.copy(
                      vocabularyInsertError = e.toString()
                  )
            }

        }
    }

    fun EditVocabulary(){

        viewModelScope.launch {
            try {
                vocabularyDao.EditVocabulary(_vocabularyState.vocabulary,_vocabularyState.vocabularyMeans,_vocabularyState.vocabularySentences,_vocabularyState.id)
            }
            catch (e: SQLException)
            {
                _vocabularyState=_vocabularyState.copy(
                    vocabularyInsertError = e.toString()
                )
            }

        }
    }
    fun clearValues(){
        _vocabularyState=_vocabularyState.copy(
            id = 0,
            vocabulary = "",
            vocabularyMeans = "",
            vocabularySentences = ""
        )
    }
    fun getVocabulary():Flow<List<Vocabulary>>{

        viewModelScope.launch {


            _allVocabulary.update {
                it.copy(
                    allVocabulary=vocabularyDao.getAllVocabulary().map {
                        it.map {
                            Vocabulary(id = it.id, vocabulary = it.vocabulary, vocabularyMeans = it.vocabularyMeans, vocabularySentences = it.vocabularySentences)
                        }
                    }


                )
            }
        }
        return  _allVocabulary.value.allVocabulary


    }

}