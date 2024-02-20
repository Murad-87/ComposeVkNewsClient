package com.muslim.simplevknewsclient.di

import android.content.Context
import com.muslim.simplevknewsclient.data.network.ApiFactory
import com.muslim.simplevknewsclient.data.network.ApiService
import com.muslim.simplevknewsclient.data.repository.NewsFeedRepositoryImpl
import com.muslim.simplevknewsclient.domain.repository.NewsFeedRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideVkStorage(
            context: Context
        ): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}