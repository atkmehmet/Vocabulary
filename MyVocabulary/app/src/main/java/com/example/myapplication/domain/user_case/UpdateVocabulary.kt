package com.example.myapplication.domain.user_case

import com.example.myapplication.data.repository.repositoryImplemtation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateVocabulary @Inject constructor(
    private val repository: repositoryImplemtation
){
    operator fun invoke (vocabulary:String,means:String,sentences:String,id:Int){
        GlobalScope.launch {
            repository.EditVocabulary(vocabulary = vocabulary, means = means,sentences=sentences, id = id)
        }

    }
 }