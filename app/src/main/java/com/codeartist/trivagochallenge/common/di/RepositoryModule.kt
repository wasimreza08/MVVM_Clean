package com.codeartist.trivagochallenge.common.di

import com.codeartist.trivagochallenge.detail.data.DetailAPI
import com.codeartist.trivagochallenge.detail.data.repository.DetailRepositoryImpl
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.search.data.remotesource.RemoteAPI
import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import com.codeartist.trivagochallenge.search.data.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideSearchRepository(
        retrofitService: RemoteAPI
    ): SearchRepository {
        return SearchRepositoryImpl(retrofitService)
    }

    @Singleton
    @Provides
    fun provideDetailRepository(
        retrofitService: DetailAPI
    ): DetailRepository {
        return DetailRepositoryImpl()
    }
}
