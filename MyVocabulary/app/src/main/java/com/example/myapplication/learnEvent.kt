package com.example.myapplication

sealed class learnEvent{
    data class findVocabulary(val searchText:String):learnEvent ()
}