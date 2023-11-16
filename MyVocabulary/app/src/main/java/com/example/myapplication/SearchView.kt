package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.wordApi
import com.example.myapplication.data.remote.dto.wordValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchView @Inject constructor(private val wordApi: wordApi) :ViewModel() {
    private var retWordValue= wordValue("", emptyList())
    var means= mutableStateOf("")
    var vocabulary= mutableStateOf("")


    @SuppressLint("SuspiciousIndentation")
    fun getWords(userVocabulary:String){
        try {
            means.value="Searching..."
            vocabulary.value=""
            var countMeans:Int=1
          //  val api= wordApi.getInstance()
            viewModelScope.launch {
                val returnValue = wordApi.getwordMeans(userVocabulary)
                means.value=""
                for (value in returnValue.indices){
                      val word=returnValue[value]
                       for (value2 in word.wordMeans.indices){
                           val mean=word.wordMeans[value2]
                            for (value3 in mean.definitions.indices){

                                means.value= means.value+"\r\n"+countMeans.toString()+"-) "+mean.definitions[value3].vocabularyMeans
                                   if(mean.definitions[value3].vocabularySentences!=null){
                                       means.value= means.value+"\r\n"+"Examples:"+mean.definitions[value3].vocabularySentences
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