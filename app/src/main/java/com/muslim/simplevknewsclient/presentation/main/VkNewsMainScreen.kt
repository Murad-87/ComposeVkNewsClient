package com.muslim.simplevknewsclient.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.muslim.simplevknewsclient.navigation.AppNavGraph
import com.muslim.simplevknewsclient.navigation.rememberNavigationState
import com.muslim.simplevknewsclient.presentation.ViewModelFactory
import com.muslim.simplevknewsclient.presentation.comments.CommentsScreen
import com.muslim.simplevknewsclient.presentation.news.NewsFeedScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navigateState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            NavigationBar {

                val navBackStackEntry by navigateState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigateState.navigateTo(item.screen.route)
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(text = stringResource(id = item.titleResId)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            indicatorColor = Color.White
                        )
                    )
                }
            }
        }
    ) {

        AppNavGraph(
            navHostController = navigateState.navHostController,
            newsFeedScreenContent = {
                NewsFeedScreen(
                    onCommentClickListener = {
                        navigateState.navigateToComments(it)
                    },
                )
            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(
                    onBackPressed = {
                        navigateState.navHostController.popBackStack()
                    },
                    feedPost = feedPost,
                )
            },
            favouriteScreenContent = { TextCount(name = "Favourite") },
            profileScreenContent = { TextCount(name = "Profile") }
        )
    }
}

@Composable
private fun TextCount(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}