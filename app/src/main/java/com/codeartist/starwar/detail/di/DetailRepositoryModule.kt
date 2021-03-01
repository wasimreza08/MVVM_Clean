package com.codeartist.starwar.detail.di

import com.codeartist.starwar.detail.data.remotesource.DetailAPI
import com.codeartist.starwar.detail.data.repository.DetailRepositoryImpl
import com.codeartist.starwar.detail.domain.repository.DetailRepository
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