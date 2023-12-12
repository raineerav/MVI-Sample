package com.live.bets.domain.utils

/**
 * EventState class is a wrapper class to emit UI state.
 *
 */
sealed class EventState {

    /**
     * Back is to emit the back state, we use this after finishing an event
     * and we don't need the current fragment anymore.
     */
    object Back : EventState()
}
