package com.live.bets.presentation.appIntents

import com.live.bets.appBaseClasses.BaseIntent
import com.live.bets.domain.model.LiveMatchesDTO

sealed class BetsIntent : BaseIntent() {
    object GetBetsData : BetsIntent()
}