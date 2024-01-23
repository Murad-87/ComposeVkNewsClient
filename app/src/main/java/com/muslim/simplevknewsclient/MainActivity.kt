package com.muslim.simplevknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.muslim.simplevknewsclient.ui.theme.SimpleVkNewsClientTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleVkNewsClientTheme {
                MainScreen(viewModel)
            }
        }
    }
}