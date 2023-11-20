package com.example.myapplication.representation.learn.compenents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.model.Vocabulary

@Composable
fun learnItems(vocabulary:Vocabulary){
    Row() {
        Card(border = BorderStroke(2.dp, Color.Black),
            modifier = Modifier.padding(20.dp)) {

            Column() {
                Row() {
                    Text(text = vocabulary.vocabulary,
                        fontSize = 24.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp))
                }
                Text(text = vocabulary.vocabularyMeans,fontSize = 24.sp,
                    textAlign = TextAlign.Left, modifier = Modifier.padding(5.dp))
                Text(text = vocabulary.vocabularySentences,fontSize = 24.sp,
                    textAlign = TextAlign.Left, modifier = Modifier.padding(5.dp))
            }
        }
    }
}