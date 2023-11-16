package com.example.myapplication.domain.user_case

import com.example.myapplication.comon.Resource
import com.example.myapplication.data.repository.repositoryImplemtation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class getVocabularyCount @Inject constructor(
    private  val repository: repositoryImplemtation
) {

    operator fun invoke ():Resource<Int>{
        var count:Int=0
        GlobalScope.launch {
             count=repository.getCountVocabulary()
        }
        return Resource.Success(count)
    }
}