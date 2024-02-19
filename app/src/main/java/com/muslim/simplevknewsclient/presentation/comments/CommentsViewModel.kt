package com.muslim.simplevknewsclient.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.muslim.simplevknewsclient.data.repository.NewsFeedRepository
import com.muslim.simplevknewsclient.domain.FeedPost
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : ViewModel() {

    private val repository = NewsFeedRepository(application)

    val screenState = repository.getComments(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}