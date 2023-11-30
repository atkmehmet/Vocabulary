package com.example.myapplication.representation.newVocabulary

import android.database.SQLException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.myapplication.comon.Resource
import com.example.myapplication.data.local.VocabularyDao
import com.example.myapplication.data.local.VocabularyEntity
import com.example.myapplication.domain.model.Vocabulary
import com.example.myapplication.domain.user_case.AddVocabulary
import com.example.myapplication.domain.user_case.GetChangeVocabulary
import com.example.myapplication.domain.user_case.GetVocabulary
import com.example.myapplication.domain.user_case.GetVocabularywithId
import com.example.myapplication.domain.user_case.UpdateVocabulary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabularyView @Inject constructor(
    private val getAllVocabulary: GetVocabulary,
    private val updateVocabulary: UpdateVocabulary,
    private val getVocabularywithId: GetVocabularywithId,
    private val getChangeVocabulary: GetChangeVocabulary,
    private val addVocabulary: AddVocabulary
)  :ViewModel(){


    private  var _vocabularyState by mutableStateOf(VocabularyAddState())
    val   vocabularyState: VocabularyAddState
       get() =_vocabularyState
  //  private var _allVocabulary by mutableStateOf(VocabularyState())

  private var _allVocabulary = MutableStateFlow(VocabularyState())
   val allVocabulary=_allVocabulary.asStateFlow()


    init {
        getVocabulary()
    }





    fun  onEvent(event: VocabularyEvent){

        when(event){
         is VocabularyEvent.vocabulary ->
         {
               _vocabularyState=_vocabularyState.copy(
                   vocabulary = event.vocabulary
               )

         }
            is VocabularyEvent.vocabularySearch ->{
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

            is VocabularyEvent.vocabularyMeans ->
            {
                _vocabularyState=_vocabularyState.copy(
                    vocabularyMeans = event.vocabularyMeans,
                )
            }
            is VocabularyEvent.vocabularySentences ->
            {
                _vocabularyState=_vocabularyState.copy(
                    vocabularySentences = event.vocabularySentences
                )
            }
            is VocabularyEvent.vocabularyEdit ->{
              val value  = getVocabularywithId(event.id).data?: Vocabulary()
                viewModelScope.launch {
                    // val entity   =vocabularyDao.getVocabulary(event.id)
                    try {
                        _vocabularyState=_vocabularyState.copy(
                            vocabulary = value.vocabulary,
                            vocabularyMeans = value.vocabularyMeans,
                            vocabularySentences = value.vocabularySentences,
                            id = value.id,
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
            is VocabularyEvent.vocabularyLearn ->{
                 _vocabularyState=_vocabularyState.copy(
                     idValue = event.id,
                     dialogLearn = true
                 )

            }
            VocabularyEvent.vocabularySubmit ->
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
            VocabularyEvent.ShowDialog ->
            {
               _vocabularyState=_vocabularyState.copy(
                   dialogShowHide = true
               )
            }
            VocabularyEvent.HideDialog ->
            {
                _vocabularyState=_vocabularyState.copy(
                    dialogShowHide = false
                )
            }

            VocabularyEvent.LearnDialogHide ->{
                _vocabularyState=_vocabularyState.copy(
                    dialogLearn = false
                )
            }
            VocabularyEvent.changeVocabularyPage ->{
                viewModelScope.launch {
                   // vocabularyDao.LearnVocabulary(_vocabularyState.idValue)
                    getChangeVocabulary(_vocabularyState.idValue)
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

                //vocabularyDao.insertVocabulary(insertVocabulary)
                 addVocabulary(insertVocabulary)

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
                updateVocabulary(_vocabularyState.vocabulary,_vocabularyState.vocabularyMeans,_vocabularyState.vocabularySentences,_vocabularyState.id)
               // vocabularyDao.EditVocabulary(_vocabularyState.vocabulary,_vocabularyState.vocabularyMeans,_vocabularyState.vocabularySentences,_vocabularyState.id)
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

        var getAll:Flow<List<Vocabulary>> = emptyFlow()

        getAllVocabulary().onEach {
                result->
            when(result){
                is Resource.Success ->{
                    getAll = result.data?: emptyFlow()
                }
            }
        }




        viewModelScope.launch {


            _allVocabulary.update {
                it.copy(
                 //   allVocabulary=vocabularyDao.getAllVocabulary().map {
                   //     it.map {
                    //        Vocabulary(id = it.id, vocabulary = it.vocabulary, vocabularyMeans = it.vocabularyMeans, vocabularySentences = it.vocabularySentences)
                    //    }
                   // }

                    allVocabulary = getAll


                )
            }
        }
        return  _allVocabulary.value.allVocabulary


    }

}