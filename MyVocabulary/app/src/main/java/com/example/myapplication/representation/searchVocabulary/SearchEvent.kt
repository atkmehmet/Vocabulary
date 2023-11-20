package com.example.myapplication.representation.searchVocabulary

sealed class SearchEvent {
    data class getWords(val vocabulary:String):SearchEvent()
}



