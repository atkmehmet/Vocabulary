package com.example.myapplication.representation.newVocabulary

sealed class VocabularyEvent {

    data class vocabulary(val vocabulary:String): VocabularyEvent()
    data class vocabularyMeans(val vocabularyMeans: String): VocabularyEvent()
    data class vocabularySentences(val vocabularySentences:String): VocabularyEvent();
    data class vocabularyLearn(val id:Int): VocabularyEvent();
    data class vocabularyEdit(val id:Int): VocabularyEvent();
    data class vocabularySearch(val searchText:String): VocabularyEvent();
    object     vocabularySubmit: VocabularyEvent()
    object     ShowDialog: VocabularyEvent()
    object     HideDialog: VocabularyEvent()
    object     LearnDialogShow: VocabularyEvent()
    object     LearnDialogHide: VocabularyEvent()
    object     changeVocabularyPage: VocabularyEvent()
}