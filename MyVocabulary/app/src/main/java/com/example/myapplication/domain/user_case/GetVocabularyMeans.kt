package com.example.myapplication.domain.user_case

import com.example.myapplication.comon.Resource
import com.example.myapplication.data.remote.dto.towordModel
import com.example.myapplication.data.repository.repositoryImplemtation
import com.example.myapplication.domain.model.wordModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetVocabularyMeans @Inject constructor(
    private  val repository: repositoryImplemtation
) {

    operator fun invoke (word:String):Resource<List<wordModel>>{
        lateinit  var wordMeans:List<wordModel>
        GlobalScope.launch {
            wordMeans=repository.getwordsMeans(word).map {
                it.towordModel()
            }

        }
        return Resource.Success(wordMeans)
    }
}