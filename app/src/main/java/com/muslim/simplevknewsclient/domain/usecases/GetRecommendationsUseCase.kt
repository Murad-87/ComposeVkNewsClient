package com.muslim.simplevknewsclient.domain.usecases

import com.muslim.simplevknewsclient.domain.entity.FeedPost
import com.muslim.simplevknewsclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}