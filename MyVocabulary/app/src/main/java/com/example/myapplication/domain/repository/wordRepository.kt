package com.example.myapplication.domain.repository

import com.example.myapplication.data.local.VocabularyEntity
import com.example.myapplication.data.remote.dto.wordValue
import kotlinx.coroutines.flow.Flow

interface wordRepository {


    suspend fun getwordsMeans(word:String):List<wordValue>



    suspend fun insertVocabulary(vocabulary: VocabularyEntity)


    suspend fun getAllVocabulary(): Flow<List<VocabularyEntity>>


    suspend fun getVocabulary(id:Int): VocabularyEntity



    fun  getLearnVocabulary(): List<VocabularyEntity>


    suspend fun LearnVocabulary(id:Int)


    suspend fun EditVocabulary(vocabulary:String,means:String,sentences:String,id:Int)


    suspend fun getCountVocabulary():Int

}