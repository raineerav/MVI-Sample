package com.live.bets.data.api

import com.live.bets.domain.model.LiveMatchesDTO
import retrofit2.http.*

/**
 * LiveMatchesApi retrofit interface.
 */
interface LiveMatchesApi {
    @GET(ApiEndPoints.LIVE_MATCHES)
    suspend fun getAllMatches(): LiveMatchesDTO
}