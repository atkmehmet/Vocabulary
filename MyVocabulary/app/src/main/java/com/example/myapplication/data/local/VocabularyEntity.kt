package com.example.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.domain.model.Vocabulary

@Entity(tableName = "Vocabulary")
data class VocabularyEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val vocabulary: String,
    val vocabularyMeans: String,
    val vocabularySentences: String,
    val vocabularyLearn:Int=0
        )

