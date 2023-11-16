package com.example.myapplication.domain.user_case

import com.example.myapplication.comon.Resource
import com.example.myapplication.data.repository.repositoryImplemtation
import com.example.myapplication.domain.model.Vocabulary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLearnVocabulary @Inject constructor(
   private val repository: repositoryImplemtation) {
   operator fun invoke () : Flow<Resource<List<Vocabulary>>> = flow{

       val value= repository.getLearnVocabulary().map {
           Vocabulary(
               id = it.id,
               vocabulary = it.vocabulary,
               vocabularyLearn = it.vocabularyLearn,
               vocabularyMeans = it.vocabularyMeans,
               vocabularySentences = it.vocabularySentences
           )
       }
       emit(Resource.Success(value))
   }
}