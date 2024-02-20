package com.muslim.simplevknewsclient.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.muslim.simplevknewsclient.di.ApplicationComponent
import com.muslim.simplevknewsclient.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            this,
        )
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    Log.d("RECOMPOSITION", "getApplicationComponent")
    return (LocalContext.current.applicationContext as NewsFeedApplication).component
}