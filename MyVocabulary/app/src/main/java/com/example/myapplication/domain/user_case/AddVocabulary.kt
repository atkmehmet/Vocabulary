package com.example.myapplication.domain.user_case

import com.example.myapplication.data.local.VocabularyEntity
import com.example.myapplication.data.repository.repositoryImplemtation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddVocabulary @Inject  constructor(private  val repository: repositoryImplemtation) {

    operator fun invoke(entity: VocabularyEntity){
        GlobalScope.launch {
            repository.insertVocabulary(entity)
        }

    }
}