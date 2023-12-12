package com.live.bets.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.live.bets.BetsAppConstants
import com.live.bets.appBaseClasses.BaseActivity
import com.live.bets.appUtils.DateTimeUtil

import com.live.bets.databinding.ActivityMainBinding
import com.live.bets.domain.model.LiveMatchesDTO
import com.live.bets.domain.utils.DataState
import com.live.bets.presentation.adapter.BetsScreenTabsAdapter
import com.live.bets.presentation.appIntents.BetsIntent
import com.live.bets.presentation.fragment.MatchTypeFragment
import com.live.bets.presentation.viewmodel.LiveMatchesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BetsAppMainActivity : BaseActivity() {
    private val liveMatchesViewModel: LiveMatchesViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewPager()
        observeLiveData()
        liveMatchesViewModel.onTriggerEvent(BetsIntent.GetBetsData)
    }

    private fun setupViewPager() {
        val tabNameList = ArrayList<String>()
        tabNameList.add(BetsAppConstants.PageTypeConstants.INPLAY)
        tabNameList.add(BetsAppConstants.PageTypeConstants.TODAY)
        tabNameList.add(BetsAppConstants.PageTypeConstants.TOMORROW)
        tabNameList.add(BetsAppConstants.PageTypeConstants.RESULTS)
        binding.myPager2.adapter = BetsScreenTabsAdapter(
            this, tabNameList
        )
        TabLayoutMediator(
            binding.tabLayout, binding.myPager2
        ) { tab, position ->
            tab.text = tabNameList[position]
        }.attach()
    }

    private fun observeLiveData() {
        liveMatchesViewModel.observeLiveMatchesResponse().observe(this) {
            when (it) {
                is DataState.Empty -> {
                    System.out.println("Empty==========================")
                }

                is DataState.Error -> {
                    System.out.println("Error==========================")
                }

                is DataState.Loading -> {
                    System.out.println("Loading==========================")
                }

                is DataState.Success -> {
//                    val todayDate=DateTimeUtil.getCurrentDateAndTime()
                    val todayDateEnd = DateTimeUtil.getTodayDayEndTime()

                    liveMatchesViewModel.liveMatchesDataList.clear()
                    liveMatchesViewModel.liveMatchesDataList.addAll(it.data.data ?: ArrayList())

                    val openEndDateLongValue = DateTimeUtil.dateToLongValue(todayDateEnd ?: "")

                    val todayMatches = liveMatchesViewModel.liveMatchesDataList.filter {
                        DateTimeUtil.dateToLongValue(it.openDate ?: "") < openEndDateLongValue
                    }
                    val tomorrowMatchesData = liveMatchesViewModel.liveMatchesDataList.filter {
                        DateTimeUtil.dateToLongValue(it.openDate ?: "") > openEndDateLongValue
                    }


                    val todayMatchListToShow = ArrayList<LiveMatchesDTO.LiveMatches>()


                    val todayCricketList = todayMatches.filter {
                        it.sportId == BetsAppConstants.SportsTypeConstants.CRICKET
                    }
                    if (todayCricketList.isNotEmpty()) {
                        todayMatchListToShow.add(LiveMatchesDTO.LiveMatches().apply {
                            headType = BetsAppConstants.SportsTypeConstantsName.CRICKET
                        })
                        todayMatchListToShow.addAll(todayCricketList)
                    }

                    val todayTennisList = todayMatches.filter {
                        it.sportId == BetsAppConstants.SportsTypeConstants.TENNIS
                    }
                    if (todayTennisList.isNotEmpty()) {
                        todayMatchListToShow.add(LiveMatchesDTO.LiveMatches().apply {
                            headType = BetsAppConstants.SportsTypeConstantsName.TENNIS
                        })
                        todayMatchListToShow.addAll(todayTennisList)
                    }

                    val todaySoccerList = todayMatches.filter {
                        it.sportId == BetsAppConstants.SportsTypeConstants.SOCCER
                    }
                    if (todaySoccerList.isNotEmpty()) {
                        todayMatchListToShow.add(LiveMatchesDTO.LiveMatches().apply {
                            headType = BetsAppConstants.SportsTypeConstantsName.SOCCER
                        })
                        todayMatchListToShow.addAll(todaySoccerList)
                    }
                    liveMatchesViewModel.liveMatchesDataListForToday.value =
                        todayMatchListToShow
                    liveMatchesViewModel.liveMatchesDataListForTomorrow.value =
                        tomorrowMatchesData as ArrayList

                }
            }
        }
    }
}