package com.codeartist.trivagochallenge.detail.di

import com.codeartist.trivagochallenge.detail.domain.repository.DetailRepository
import com.codeartist.trivagochallenge.detail.domain.usecases.GetFilmsUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetHomeWorldUseCase
import com.codeartist.trivagochallenge.detail.domain.usecases.GetSpeciesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
@Module
@InstallIn(ApplicationComponent::class)
object DetailUseCaseModule {
    @Singleton
    @Provides
    fun provideFilmUseCase(
        repository: DetailRepository
    ): GetFilmsUseCase {
        return GetFilmsUseCase(repository)
    }
    @Singleton
    @Provides
    fun provideSpeciesUseCase(
        repository: DetailRepository
    ): GetSpeciesUseCase {
        return GetSpeciesUseCase(repository)
    }

    @Singleton
    @Provides
    fun providePopulationUseCase(
        repository: DetailRepository
    ): GetHomeWorldUseCase {
        return GetHomeWorldUseCase(repository)
    }
}