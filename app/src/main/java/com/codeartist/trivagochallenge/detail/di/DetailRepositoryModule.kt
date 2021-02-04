package com.codeartist.trivagochallenge.detail.di

import com.codeartist.trivagochallenge.detail.data.remotesource.DetailAPI
import com.codeartist.trivagochallenge.detail.data.repository.DetailRepositoryImpl
import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DetailRepositoryModule {
    @Singleton
    @Provides
    fun provideDetailRepository(
        retrofitService: DetailAPI
    ): DetailRepository {
        return DetailRepositoryImpl(retrofitService)
    }
}