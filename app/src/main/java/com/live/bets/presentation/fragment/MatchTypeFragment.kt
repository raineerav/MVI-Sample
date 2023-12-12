package com.live.bets.presentation.fragment


import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.live.bets.BetsAppConstants
import com.live.bets.appBaseClasses.BaseFragment
import com.live.bets.databinding.FragmentMatchTypeBinding
import com.live.bets.presentation.adapter.MatchListAdapter
import com.live.bets.presentation.viewmodel.LiveMatchesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchTypeFragment : BaseFragment<FragmentMatchTypeBinding>() {

    private lateinit var matchAdapter: MatchListAdapter

    /**
     * inflates the data binding.
     */
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMatchTypeBinding
        get() = FragmentMatchTypeBinding::inflate

    private val liveMatchesViewModel: LiveMatchesViewModel by activityViewModels()
    private var pageType = ""

    override fun setup() = with(binding) {
        pageType = requireArguments().getString(BetsAppConstants.IntentConstants.TYPE) ?: ""
        setMatchAdapter(binding)
        observeData()
    }


    private fun observeData() {
        if (pageType == BetsAppConstants.PageTypeConstants.TODAY) {
            liveMatchesViewModel.observeTodayResponse().observe(this) {
                System.out.println("liveMatchesViewModel==========================" + it)
                matchAdapter.updateList(it)

            }
        } else if (pageType == BetsAppConstants.PageTypeConstants.TOMORROW) {
            liveMatchesViewModel.observeTomorrowResponse().observe(this) {
                System.out.println("observeTomorrowResponse==========================" + it)
                matchAdapter.updateList(it)
            }
        } else if (pageType == BetsAppConstants.PageTypeConstants.INPLAY) {
//            liveMatchesViewModel.observeTomorrowResponse().observe(this) {
//                System.out.println("observeTomorrowResponse==========================" + it)
//                matchAdapter.updateList(it)
//            }
        } else if (pageType == BetsAppConstants.PageTypeConstants.RESULTS) {
//            liveMatchesViewModel.observeTomorrowResponse().observe(this) {
//                System.out.println("observeTomorrowResponse==========================" + it)
//                matchAdapter.updateList(it)
//            }
        }


    }


    companion object {
        fun addFragment(
            pageType: String,
        ): MatchTypeFragment {
            val matchTypeFragment = MatchTypeFragment()
            val dataBundle = Bundle()
            dataBundle.putString(BetsAppConstants.IntentConstants.TYPE, pageType)
            matchTypeFragment.arguments = dataBundle
            return matchTypeFragment
        }
    }

    private fun setMatchAdapter(mBinding: FragmentMatchTypeBinding) {
        matchAdapter = MatchListAdapter()
        mBinding.rvMatchList.layoutManager = LinearLayoutManager(
            mBinding.rvMatchList.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        mBinding.rvMatchList.itemAnimator = DefaultItemAnimator()
        mBinding.rvMatchList.adapter = matchAdapter
    }

}