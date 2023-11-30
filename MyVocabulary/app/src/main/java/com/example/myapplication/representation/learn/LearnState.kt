package com.example.myapplication.representation.learn

import com.example.myapplication.domain.model.Vocabulary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class LearnState (
    var search:String="",
    var LearnVocabularyList: Flow<List<Vocabulary>> = emptyFlow()
        )