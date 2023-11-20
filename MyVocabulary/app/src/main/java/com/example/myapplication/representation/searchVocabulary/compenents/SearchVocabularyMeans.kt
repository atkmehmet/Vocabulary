package com.example.myapplication.representation.searchVocabulary.compenents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.representation.searchVocabulary.SearchEvent
import com.example.myapplication.representation.searchVocabulary.SearchVocalaryState

@Composable
fun SearchVocabulary(searchState:SearchVocalaryState,
                     onEvent:(SearchEvent)->Unit){
    Column {

        TextField(
            value = searchState.searchVocabulary,
            placeholder={ Text(text = "Search Vocabulary Which You don't means") },
            modifier = Modifier.padding(20.dp),
            onValueChange ={newText->searchState.searchVocabulary=newText} )

        Button(onClick = { onEvent(SearchEvent.getWords(searchState.searchVocabulary)) }, modifier = Modifier.padding(20.dp)) {
            Text(text = "Search Means")
        }

        Text(text = searchState.searchVocabularyMeans, modifier = Modifier.verticalScroll(rememberScrollState()))


    }
}