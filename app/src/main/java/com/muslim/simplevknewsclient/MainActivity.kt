package com.muslim.simplevknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.muslim.simplevknewsclient.ui.theme.SimpleVkNewsClientTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleVkNewsClientTheme {
                MainScreen()
            }
        }
    }
}