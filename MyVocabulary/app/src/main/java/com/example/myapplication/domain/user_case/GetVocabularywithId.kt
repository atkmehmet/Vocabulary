package com.example.myapplication.domain.user_case

import com.example.myapplication.VocabularyEvent
import com.example.myapplication.comon.Resource
import com.example.myapplication.data.repository.repositoryImplemtation
import com.example.myapplication.domain.model.Vocabulary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject


class GetVocabularywithId @Inject constructor(
    private val repository: repositoryImplemtation
) {

    operator fun invoke(id: Int): Resource<Vocabulary> {
        lateinit var vocabulary: Vocabulary
        GlobalScope.launch {
            val getVocabulary = repository.getVocabulary(id)
            vocabulary = Vocabulary(
                id = getVocabulary.id,
                vocabularyMeans = getVocabulary.vocabularyMeans,
                vocabulary = getVocabulary.vocabulary,
                vocabularySentences = getVocabulary.vocabularySentences,
                vocabularyLearn = getVocabulary.vocabularyLearn
            )


        }

        return Resource.Success(vocabulary)

    }

}