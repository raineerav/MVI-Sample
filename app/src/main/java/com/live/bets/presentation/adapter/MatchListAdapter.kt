package com.live.bets.presentation.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.live.bets.BetsAppConstants
import com.live.bets.databinding.IncludeMatchBodyRowBinding
import com.live.bets.databinding.IncludeMatchHeaderRowBinding
import com.live.bets.domain.model.LiveMatchesDTO

class MatchListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var matchList = ArrayList<LiveMatchesDTO.LiveMatches>()

    inner class HeaderViewHolder(val binding: IncludeMatchHeaderRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindHeader(item: LiveMatchesDTO.LiveMatches) {
            with(item) {
                binding.tvMatchType.text = this.headType
            }
        }


    }

    inner class BodyViewHolder(val binding: IncludeMatchBodyRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindBodyData(item: LiveMatchesDTO.LiveMatches) {
            with(item) {
                binding.tvMatchName.text = this.competitionName
                binding.tvMatchTime.text = this.openDate
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> HeaderViewHolder(
                IncludeMatchHeaderRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            1 -> BodyViewHolder(
                IncludeMatchBodyRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (TextUtils.isEmpty(matchList[position].headType)) return BetsAppConstants.SportsViewTypes.FOOTER
        return BetsAppConstants.SportsViewTypes.HEADER
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(
        matchList: ArrayList<LiveMatchesDTO.LiveMatches>
    ) {
        this.matchList.clear()
        this.matchList.addAll(matchList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.bindHeader(matchList[position])
        } else if (holder is BodyViewHolder) {
            holder.bindBodyData(matchList[position])
        }
    }

    override fun getItemCount(): Int {
        return matchList.size
    }
}


