package com.live.bets.di

import com.live.bets.domain.repository.LiveMatchesRepository
import com.live.bets.repository.LiveMatchesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * RepositoryModule is to bind di for all repositories
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindLiveMatchesRepository(achievementRepositoryImpl: LiveMatchesRepositoryImpl): LiveMatchesRepository

}