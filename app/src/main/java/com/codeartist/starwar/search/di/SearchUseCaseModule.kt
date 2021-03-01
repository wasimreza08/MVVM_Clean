package com.codeartist.starwar.search.di

import com.codeartist.starwar.search.domain.repository.SearchRepository
import com.codeartist.starwar.search.domain.usecase.SearchUseCase
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