package com.example.myapplication.domain.user_case

import com.example.myapplication.data.repository.repositoryImplemtation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetChangeVocabulary @Inject constructor(
    private val repository: repositoryImplemtation
) {
    operator fun invoke(id:Int){
        GlobalScope.launch {
            repository.LearnVocabulary(id = id)
        }
    }
}