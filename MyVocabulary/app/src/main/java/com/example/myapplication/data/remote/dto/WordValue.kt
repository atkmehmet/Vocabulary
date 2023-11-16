package com.example.myapplication.data.remote.dto

import com.example.myapplication.data.remote.dto.meaning
import com.example.myapplication.domain.model.wordModel
import com.google.gson.annotations.SerializedName

data class wordValue (
    @SerializedName("word")
    val askWord:String,
    @SerializedName("meanings")
    val wordMeans:List<meaning>
        )


fun wordValue.towordModel():wordModel{
    return wordModel(
       askWord = askWord,
        wordMeans=wordMeans.map {
            it.tomeaningModel()
        }
    )
}