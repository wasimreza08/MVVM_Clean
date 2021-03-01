package com.codeartist.starwar.detail.di

import com.codeartist.starwar.detail.data.remotesource.DetailAPI
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