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
import com.example.myapplication.representation.searchVocabulary.SearchView
import com.example.myapplication.representation.searchVocabulary.SearchVocalaryState

@Composable
fun SearchVocabulary(view: SearchView){
    Column {

        TextField(
            value = view.state.value.searchVocabulary,
            placeholder={ Text(text = "Search Vocabulary Which You don't means") },
            modifier = Modifier.padding(20.dp),
            onValueChange ={newText->view.state.value.searchVocabulary=newText} )

        Button(onClick = { view.getWords(view.state.value.searchVocabulary) }, modifier = Modifier.padding(20.dp)) {
            Text(text = "Search Means")
        }

        Text(text = view.state.value.searchVocabularyMeans, modifier = Modifier.verticalScroll(rememberScrollState()))


    }
}