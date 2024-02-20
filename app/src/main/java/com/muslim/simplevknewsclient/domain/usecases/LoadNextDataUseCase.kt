package com.muslim.simplevknewsclient.domain.usecases

import com.muslim.simplevknewsclient.domain.repository.NewsFeedRepository
import javax.inject.Inject

class LoadNextDataUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}