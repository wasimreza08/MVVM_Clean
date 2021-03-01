package com.codeartist.starwar.search.di

import com.codeartist.starwar.search.data.remotesource.RemoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SearchRetrofitAPI {

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit.Builder): RemoteAPI {
        return retrofit
            .build()
            .create(RemoteAPI::class.java)
    }
}