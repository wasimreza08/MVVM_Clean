package com.codeartist.trivagochallenge.detail.di

import com.codeartist.trivagochallenge.detail.data.remotesource.DetailAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DetailRetrofitAPI {

    @Singleton
    @Provides
    fun providerDetailAPI(retrofit: Retrofit.Builder): DetailAPI {
        return retrofit
            .build()
            .create(DetailAPI::class.java)
    }
}