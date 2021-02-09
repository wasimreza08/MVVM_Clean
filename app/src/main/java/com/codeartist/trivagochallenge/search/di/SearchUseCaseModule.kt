package com.codeartist.trivagochallenge.search.di

import com.codeartist.trivagochallenge.search.domain.repository.SearchRepository
import com.codeartist.trivagochallenge.search.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SearchUseCaseModule {
    @Singleton
    @Provides
    fun provideSearchUseCase(
        repository: SearchRepository
    ): SearchUseCase {
        return SearchUseCase(repository)
    }

}