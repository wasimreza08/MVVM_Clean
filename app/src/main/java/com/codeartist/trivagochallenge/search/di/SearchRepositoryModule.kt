package com.codeartist.trivagochallenge.search.di

import com.codeartist.trivagochallenge.search.data.RemoteAPI
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepositoryImpl
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
    fun provideRepository(
        retrofitService: RemoteAPI
    ): SearchRepository {
        return SearchRepositoryImpl(retrofitService)
    }
}
