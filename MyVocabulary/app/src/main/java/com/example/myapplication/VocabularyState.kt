package com.example.myapplication

import com.example.myapplication.domain.model.Vocabulary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class VocabularyState
        (
    var allVocabulary: Flow<List<Vocabulary>> = emptyFlow(),
    var allLearnVocabulary:Flow<List<Vocabulary>> = emptyFlow()
        )
