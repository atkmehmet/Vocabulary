package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.representation.mainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // val viewModel by viewModels<VocabularyView>()
             //mainScreen(viewModel.vocabularyState,onEvent=viewModel::onEvent)
            mainScreen();
        }
    }
}

