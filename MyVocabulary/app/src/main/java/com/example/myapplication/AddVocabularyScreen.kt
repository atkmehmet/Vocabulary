package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun addVocabularyScree(){
    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = "Add Vocabulary")},
        text = {  Column(modifier = Modifier.padding(10.dp))
                   {
                       TextField(value ="" , onValueChange = {})
                       TextField(value = "", onValueChange ={} )
                       TextField(value = "", onValueChange ={} )
                   }
               },
        buttons = { Row(modifier = Modifier.padding(8.dp)) {
                     Button(onClick = {  }) {
                         Text(text = "Record")
                     }
                  Button(onClick = { }) {
                      Text(text = "Close Dialog")
                  }
                    }
        })
}