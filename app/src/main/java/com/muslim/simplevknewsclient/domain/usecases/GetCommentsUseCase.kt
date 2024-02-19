package com.muslim.simplevknewsclient.domain.usecases

import com.muslim.simplevknewsclient.domain.entity.FeedPost
import com.muslim.simplevknewsclient.domain.entity.PostComment
import com.muslim.simplevknewsclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetCommentsUseCase(
    private val repository: NewsFeedRepository
) {

    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}