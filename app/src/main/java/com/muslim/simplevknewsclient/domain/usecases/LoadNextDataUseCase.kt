package com.muslim.simplevknewsclient.domain.usecases

import com.muslim.simplevknewsclient.domain.repository.NewsFeedRepository

class LoadNextDataUseCase(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}