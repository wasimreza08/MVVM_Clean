package com.codeartist.trivagochallenge.common.di

import com.codeartist.trivagochallenge.common.Constants
import com.codeartist.trivagochallenge.detail.data.DetailAPI
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
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // Give String of Server base url
            .addConverterFactory(GsonConverterFactory.create())

    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit.Builder): RemoteAPI {
        return retrofit
            .build()
            .create(RemoteAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideDetailService(retrofit: Retrofit.Builder): DetailAPI {
        return retrofit
            .build()
            .create(DetailAPI::class.java)
    }

}
