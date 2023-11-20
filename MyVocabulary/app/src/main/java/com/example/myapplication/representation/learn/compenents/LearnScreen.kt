package com.example.myapplication.representation.learn.compenents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.representation.learn.learnEvent
import com.example.myapplication.representation.learn.learnView

@Composable
fun LearnScreen(view:learnView){
    Column(){
        Row() {
            TextField(
                value = view.state.search,
                placeholder = { Text(text ="Search Vocabulary") },
                onValueChange ={view.onEvent(learnEvent.findVocabulary(it))},
                modifier = Modifier.padding(20.dp) )
        }
        val vocabularyAll=view._learnVocabulary.value.allLearnVocabulary.collectAsState(initial = emptyList()).value
        LazyColumn( )
        {
            items(vocabularyAll){
                    vocabulary->
              learnItems(vocabulary = vocabulary)
            }
        }
    }
}