package com.live.bets.repository

import com.live.bets.domain.repository.LiveMatchesRepository
import com.live.bets.domain.utils.DataState
import com.live.bets.data.api.LiveMatchesApi
import com.live.bets.domain.model.LiveMatchesDTO
import com.live.bets.domain.utils.APIUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LiveMatchesRepositoryImpl @Inject constructor(
    private val liveMatchesApi: LiveMatchesApi,
) : LiveMatchesRepository {
    override suspend fun getBetsData(): Flow<DataState<LiveMatchesDTO>> = flow {
        emit(DataState.Loading)
        try {
            emit(DataState.Success(liveMatchesApi.getAllMatches()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(APIUtils.resolveError(e))
        }
    }

}