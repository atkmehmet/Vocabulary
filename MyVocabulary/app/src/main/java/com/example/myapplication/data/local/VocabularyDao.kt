package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabularyDao {

    @Insert
    suspend fun insertVocabulary(vocabulary: VocabularyEntity)

    @Query("SELECT * FROM Vocabulary where vocabularyLearn=0 order by id desc")
     fun getAllVocabulary():List<VocabularyEntity>


    @Query("SELECT * FROM Vocabulary where id=:id")
    suspend fun getVocabulary(id:Int): VocabularyEntity


    @Query ("SELECT * FROM Vocabulary WHERE  vocabularyLearn=1 order by id desc")
     fun  getLearnVocabulary(): List<VocabularyEntity>

    @Query("Update Vocabulary set vocabularyLearn=1 where id=:id")
    suspend fun LearnVocabulary(id:Int)

    @Query("Update Vocabulary set vocabulary=:vocabulary,vocabularyMeans=:means,vocabularySentences=:sentences where id=:id")
    suspend fun EditVocabulary(vocabulary:String,means:String,sentences:String,id:Int)

    @Query("Select count(*) from Vocabulary")
    suspend fun getCountVocabulary():Int
}