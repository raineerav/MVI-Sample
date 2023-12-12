package com.live.bets.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.live.bets.appBaseClasses.BaseViewModel
import com.live.bets.domain.model.LiveMatchesDTO
import com.live.bets.domain.repository.LiveMatchesRepository
import com.live.bets.domain.utils.APIUtils
import com.live.bets.domain.utils.DataState
import com.live.bets.presentation.appIntents.BetsIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.json.JSONObject

@HiltViewModel
class LiveMatchesViewModel @Inject constructor(
    private val liveMatchesRepository: LiveMatchesRepository
) : BaseViewModel<BetsIntent>() {

    val liveMatchesDataList = ArrayList<LiveMatchesDTO.LiveMatches>()

    val liveMatchesDataListForToday = SingleLiveEvent<ArrayList<LiveMatchesDTO.LiveMatches>>()

    val liveMatchesDataListForTomorrow = SingleLiveEvent<ArrayList<LiveMatchesDTO.LiveMatches>>()


    private val liveMatchesResponseMLD = SingleLiveEvent<DataState<LiveMatchesDTO>>()

    fun observeLiveMatchesResponse(): SingleLiveEvent<DataState<LiveMatchesDTO>> {
        return liveMatchesResponseMLD
    }




    fun observeTodayResponse(): SingleLiveEvent<ArrayList<LiveMatchesDTO.LiveMatches>> {
        return liveMatchesDataListForToday
    }

    fun observeTomorrowResponse(): SingleLiveEvent<ArrayList<LiveMatchesDTO.LiveMatches>> {
        return liveMatchesDataListForTomorrow
    }



    private suspend fun getBetsData() {
        liveMatchesRepository.getBetsData().collect {
            when (it) {
                is DataState.Empty -> {
                    liveMatchesResponseMLD.value = DataState.Empty
                }

                is DataState.Error -> {
                    liveMatchesResponseMLD.value = DataState.Error(it.exception)
                }

                is DataState.Loading -> {
                    liveMatchesResponseMLD.value = DataState.Loading
                }

                is DataState.Success -> {
                    liveMatchesResponseMLD.value = DataState.Success(it.data)
                }
            }
        }
    }

    /**
     * Triggers the intent events.
     *
     * @param eventType the event type that we want to trigger
     */
    override fun onTriggerEvent(eventType: BetsIntent) {
        viewModelScope.launch {
            when (eventType) {
                BetsIntent.GetBetsData -> {
                    getBetsData()
                }
            }
        }
    }
}