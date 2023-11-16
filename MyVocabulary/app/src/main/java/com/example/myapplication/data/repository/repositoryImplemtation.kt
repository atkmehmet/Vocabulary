package com.example.myapplication.data.repository

import android.os.Parcel
import android.os.Parcelable
import com.example.myapplication.data.local.VocabularyDao
import com.example.myapplication.data.local.VocabularyEntity
import com.example.myapplication.data.remote.dto.wordValue
import com.example.myapplication.data.remote.wordApi
import com.example.myapplication.domain.repository.wordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class repositoryImplemtation @Inject constructor(
    private val dao: VocabularyDao,
    private  val api: wordApi
): wordRepository {
    override suspend fun getwordsMeans(word: String): List<wordValue> {
      return  api.getwordMeans(word)
    }

    override suspend fun insertVocabulary(vocabulary: VocabularyEntity) {
        dao.insertVocabulary(vocabulary = vocabulary)
    }

    override suspend fun getAllVocabulary(): List<VocabularyEntity> {
        return  dao.getAllVocabulary()
    }

    override suspend fun getVocabulary(id: Int): VocabularyEntity {
           return dao.getVocabulary(id)
    }

    override fun getLearnVocabulary(): List<VocabularyEntity> {
        return dao.getLearnVocabulary()
    }

    override suspend fun LearnVocabulary(id: Int) {
        return dao.LearnVocabulary(id)
    }

    override suspend fun EditVocabulary(
        vocabulary: String,
        means: String,
        sentences: String,
        id: Int
    ) {
        dao.EditVocabulary(vocabulary, means, sentences, id)
    }

    override suspend fun getCountVocabulary(): Int {
         return dao.getCountVocabulary()
    }


}