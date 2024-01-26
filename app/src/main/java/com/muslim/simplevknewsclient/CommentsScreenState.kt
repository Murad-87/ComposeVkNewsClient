package com.muslim.simplevknewsclient

import com.muslim.simplevknewsclient.domain.FeedPost
import com.muslim.simplevknewsclient.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()

}
