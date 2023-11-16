package com.example.myapplication.representation.newVocabulary.compenents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.representation.newVocabulary.VocabularyAddState
import com.example.myapplication.VocabularyEvent

@Composable
fun addVocabulary(state: VocabularyAddState,
                  onEvent:(VocabularyEvent)->Unit)
{

    AlertDialog(
        onDismissRequest = { onEvent(VocabularyEvent.HideDialog) },
        title = { Text(text = state.vocabularyError) },
        text = {  Column(modifier = Modifier.padding(10.dp))
        {
            TextField(value =state.vocabulary,
                onValueChange = {onEvent(VocabularyEvent.vocabulary(it))},
                placeholder ={ Text(text = "Vocabulary of not known") }
            )
            TextField(value = state.vocabularyMeans,
                onValueChange ={onEvent(VocabularyEvent.vocabularyMeans(it))},
                placeholder = { Text(text = "Vocabulary Means") }
            )
            TextField(value = state.vocabularySentences,
                onValueChange ={onEvent(VocabularyEvent.vocabularySentences(it))},
                placeholder = { Text(text = "Examples of Sentences ") }
            )
            if(state.vocabularyError!="") {
                Text(text = state.vocabularyError)
            }


        }
        },
        buttons = { Row(modifier = Modifier.padding(8.dp)) {
            Button(onClick = { onEvent(VocabularyEvent.vocabularySubmit) }) {
                Text(text = "Record")
            }
            Spacer(modifier= Modifier.width(10.dp))
            Button(onClick = {onEvent(VocabularyEvent.HideDialog) }) {
                Text(text = "Close Dialog")
            }
        }
        })

}