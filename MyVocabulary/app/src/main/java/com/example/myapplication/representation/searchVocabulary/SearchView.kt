package com.example.myapplication.representation.searchVocabulary

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.wordApi
import com.example.myapplication.data.remote.dto.wordValue
import com.example.myapplication.domain.user_case.GetVocabulary
import com.example.myapplication.domain.user_case.GetVocabularyMeans
import com.example.myapplication.domain.user_case.getVocabularyCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchView @Inject constructor(private val getMeans: GetVocabularyMeans) :ViewModel() {

    val state= mutableStateOf(SearchVocalaryState())




    @SuppressLint("SuspiciousIndentation")
    fun getWords(userVocabulary:String){


        try {

            val getMeans:List<wordValue>
            state.value.searchVocabulary="Searching..."
            state.value.searchVocabularyMeans=""
            var countMeans:Int=1
          //  val api= wordApi.getInstance()
            viewModelScope.launch {

              //  val returnValue = wordApi.getwordMeans(userVocabulary)
               val returnValue= getMeans(userVocabulary).data?: emptyList()
                state.value.searchVocabularyMeans=""
                for (value in returnValue.indices){
                      val word=returnValue[value]
                       for (value2 in word.wordMeans.indices){
                           val mean=word.wordMeans[value2]
                            for (value3 in mean.definitions.indices){

                                state.value.searchVocabularyMeans= state.value.searchVocabularyMeans+"\r\n"+countMeans.toString()+"-) "+mean.definitions[value3].vocabularyMeans
                                   if(mean.definitions[value3].vocabularySentences!=null){
                                       state.value.searchVocabularyMeans= state.value.searchVocabularyMeans+"\r\n"+"Examples:"+mean.definitions[value3].vocabularySentences
                                   }
                             countMeans++
                            }

                        }
                    }
                }



        }
        catch (e:Exception)
        {


        }
    }
}