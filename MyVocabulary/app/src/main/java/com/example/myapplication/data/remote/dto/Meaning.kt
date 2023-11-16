package com.example.myapplication.data.remote.dto

import com.example.myapplication.domain.model.definitionModel
import com.example.myapplication.domain.model.meaningModel
import com.google.gson.annotations.SerializedName

data class meaning (
    @SerializedName("partOfSpeech")
    val partofSpeech:String,
    @SerializedName("definitions")
    val definitions:  List<definitions>
)
fun meaning.tomeaningModel():meaningModel{
    return  meaningModel(
        partofSpeech = partofSpeech,
        definitions= definitions.map {
            it.todefinitionModel()
        }
    )
}