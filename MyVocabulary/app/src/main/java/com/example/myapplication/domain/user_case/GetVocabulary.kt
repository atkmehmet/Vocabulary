package com.example.myapplication.domain.user_case

import com.example.myapplication.comon.Resource
import com.example.myapplication.data.repository.repositoryImplemtation
import com.example.myapplication.domain.model.Vocabulary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class GetVocabulary @Inject constructor(
   private val repository: repositoryImplemtation) {
   operator fun invoke(): Flow<Resource<Flow<List<Vocabulary>>>> = flow{
          val vocabulary=repository.getAllVocabulary().map {
              it.map {
                  Vocabulary(
                      id=it.id,
                      vocabulary = it.vocabulary,
                      vocabularyMeans = it.vocabularyMeans,
                      vocabularySentences = it.vocabularySentences
                  )
              }

          }
   emit(Resource.Success(vocabulary))
   }

}