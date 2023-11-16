package com.example.myapplication.representation.newVocabulary

data class VocabularyAddState (
    val id:Int=0,
    val findVocabulary: String="",
    val vocabulary: String="",
    val vocabularyError: String="",
    val vocabularyMeans: String="",
    val vocabularyMeansError: String="",
    val vocabularySentences: String="",
    val vocabularySentencesError: String="",
    val vocabularyInsertError:String="",
    val vocabularyCount:Int=0,
    val dialogShowHide:Boolean=false,
    val dialogLearn:Boolean=false,
    val idValue:Int=0
        )
