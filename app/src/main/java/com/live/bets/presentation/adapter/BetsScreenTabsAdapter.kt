package com.live.bets.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.live.bets.BetsAppConstants
import com.live.bets.presentation.fragment.MatchTypeFragment

class BetsScreenTabsAdapter(
    fragmentActivity: FragmentActivity,
    private val tabsNamesList: ArrayList<String>,
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return tabsNamesList.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                MatchTypeFragment.addFragment(
                    BetsAppConstants.PageTypeConstants.INPLAY
                )
            }
            1 -> {
                MatchTypeFragment.addFragment(
                    BetsAppConstants.PageTypeConstants.TODAY
                )
            }

            2 -> {
                MatchTypeFragment.addFragment(
                    BetsAppConstants.PageTypeConstants.TOMORROW
                )
            }
            else -> {
                MatchTypeFragment.addFragment(
                    BetsAppConstants.PageTypeConstants.RESULTS
                )

            }
        }

    }
}