package com.codeartist.trivagochallenge.search.di

import com.codeartist.trivagochallenge.search.data.remotesource.RemoteAPI
import com.codeartist.trivagochallenge.search.data.repository.SearchRepositoryImpl
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SearchRepositoryModule {
    @Singleton
    @Provides
    fun provideSearchRepository(
        retrofitService: RemoteAPI
    ): SearchRepository {
        return SearchRepositoryImpl(retrofitService)
    }

}