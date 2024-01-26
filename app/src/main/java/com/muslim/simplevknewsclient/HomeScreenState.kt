package com.muslim.simplevknewsclient

import com.muslim.simplevknewsclient.domain.FeedPost
import com.muslim.simplevknewsclient.domain.PostComment

sealed class HomeScreenState {

    object Initial: HomeScreenState()

    data class Posts(val posts: List<FeedPost>) : HomeScreenState()

    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>) : HomeScreenState()
}
