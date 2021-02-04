package com.codeartist.trivagochallenge.common.di

import com.codeartist.trivagochallenge.common.Constants
import com.codeartist.trivagochallenge.detail.data.remotesource.DetailAPI
import com.codeartist.trivagochallenge.search.data.remotesource.RemoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // Give String of Server base url
            .addConverterFactory(GsonConverterFactory.create())
    }

}
