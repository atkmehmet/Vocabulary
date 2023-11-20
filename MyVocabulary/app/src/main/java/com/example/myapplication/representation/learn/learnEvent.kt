package com.example.myapplication.representation.learn

sealed class learnEvent{
    data class findVocabulary(val searchText:String): learnEvent()
}