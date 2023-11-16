package com.example.myapplication.data.remote.dto

import com.example.myapplication.domain.model.definitionModel
import com.google.gson.annotations.SerializedName

data class definitions (
  @SerializedName("definition")
   val vocabularyMeans:String,
  @SerializedName("example")
    val vocabularySentences:String
        )

fun definitions.todefinitionModel():definitionModel{
  return  definitionModel(
     vocabularyMeans = vocabularyMeans,
      vocabularySentences = vocabularySentences
  )
}
