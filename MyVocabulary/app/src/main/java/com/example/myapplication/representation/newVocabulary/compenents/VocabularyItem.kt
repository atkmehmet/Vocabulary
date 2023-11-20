package com.example.myapplication.representation.newVocabulary.compenents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.domain.model.Vocabulary
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.representation.newVocabulary.VocabularyEvent

@Composable
fun VocabularyItems(
    vocabulary: Vocabulary,
    onEvent:(VocabularyEvent)-> Unit
){
    Card(border = BorderStroke(2.dp, Color.Black),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(vertical = 10.dp)) {

        Column() {


            Row() {
                Text(text = vocabulary.vocabulary,
                    fontSize = 24.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp))
                Button(onClick = { onEvent(VocabularyEvent.vocabularyLearn(vocabulary.id)) }, modifier = Modifier.weight(0.25f)) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "I learned")
                }

                Button(onClick = { onEvent(VocabularyEvent.vocabularyEdit(vocabulary.id)) }, modifier = Modifier.weight(0.25f)) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Edit")
                }
            }

            Text(text = vocabulary.vocabularyMeans,fontSize = 24.sp,
                textAlign = TextAlign.Left, modifier = Modifier.padding(5.dp))
            Text(text = vocabulary.vocabularySentences,fontSize = 24.sp,
                textAlign = TextAlign.Left, modifier = Modifier.padding(5.dp))

        }
        Spacer(modifier = Modifier.height(100.dp))
    }

}