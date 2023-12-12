package com.live.bets.di

import com.live.bets.data.api.LiveMatchesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun provideLiveMatchesApiApi(retrofit: Retrofit.Builder): LiveMatchesApi =
        retrofit.build().create(LiveMatchesApi::class.java)

}