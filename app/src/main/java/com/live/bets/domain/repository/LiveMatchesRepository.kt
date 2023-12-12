package com.live.bets.domain.repository

import com.live.bets.domain.model.LiveMatchesDTO
import com.live.bets.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface LiveMatchesRepository {
    suspend fun getBetsData(): Flow<DataState<LiveMatchesDTO>>

}