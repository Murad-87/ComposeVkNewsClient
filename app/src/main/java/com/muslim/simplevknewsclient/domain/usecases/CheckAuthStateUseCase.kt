package com.muslim.simplevknewsclient.domain.usecases

import com.muslim.simplevknewsclient.domain.repository.NewsFeedRepository

class CheckAuthStateUseCase(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke() {
        return repository.checkAuthState()
    }
}