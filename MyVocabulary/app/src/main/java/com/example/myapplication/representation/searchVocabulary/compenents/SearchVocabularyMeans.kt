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

@Composable
fun SearchVocabulary(){
    Column {

        TextField(
            value = view.vocabulary.value,
            placeholder={ Text(text = "Search Vocabulary Which You don't means") },
            modifier = Modifier.padding(20.dp),
            onValueChange ={newText->view.vocabulary.value=newText} )

        Button(onClick = { view.getWords(view.vocabulary.value) }, modifier = Modifier.padding(20.dp)) {
            Text(text = "Search Means")
        }

        Text(text = view.means.value, modifier = Modifier.verticalScroll(rememberScrollState()))


    }
}