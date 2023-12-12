package com.live.bets.domain.model


import com.google.gson.annotations.SerializedName

data class LiveMatchesDTO(
    val `data`: ArrayList<LiveMatches>? = null,
    val message: String? = null // Matches fetched!
) {
    data class LiveMatches(
        val addType: String? = null, // bangla
        val bbbFancy: String? = null, // off
        val bmProvider: String? = null, // diamond
        val channelNo: String? = null, // http://bettingsolutions.in/livetv/tvcode2.html
        val competitionId: String? = null, // 4298
        val competitionName: String? = null, // Assembly Election 2023
        val createdAt: String? = null, // 2023-10-28T13:44:37.467Z
        val eventId: String? = null, // 151414
        val eventName: String? = null, // Assembly Election 2023
        val fancyAType: String? = null, // manual
        val fancyProvider: String? = null, // diamond
        @SerializedName("_id")
        val id: String? = null, // 653d10459f1924971dc6cb5f
        val isActive: Boolean? = null, // true
        val isResult: Boolean? = null, // false
        val mEventId: String? = null, // 151414
        val mType: String? = null, // normal
        val marketId: String? = null, // 1.181009151414
        val marketIds: List<String?>? = null,
        val marketName: String? = null, // Match Odds
        val markets: List<Market?>? = null,
        val matchRunners: List<MatchRunner?>? = null,
        val oddsProvider: String? = null, // tiger
        val openDate: String? = null, // 10/22/2023 02:00:00 PM
        val sportId: String? = null, // 4
        val sportName: String? = null, // Cricket
        val type: String? = null, // auto
        val unixDate: Int? = null, // 1697963400
        val updatedAt: String? = null, // 2023-10-28T13:44:37.467Z
        var headType: String? = null, // this key is added in order to show header
        @SerializedName("__v")
        val v: Int? = null // 0
    ) {
        data class Market(
            val limit: List<Limit?>? = null,
            val marketId: String? = null, // 1.181009151414
            val marketName: String? = null, // Match Odds
            val runners: List<Runner?>? = null,
            val status: Boolean? = null // true
        ) {
            data class Limit(
                val baseCurrency: Boolean? = null, // true
                val delay: Int? = null, // 10
                val id: String? = null, // 63c3b02e91b4d6afe6df4f2d
                val maxPL: Int? = null, // 5000
                val maxStake: Int? = null, // 2000
                val minStake: Int? = null, // 1000
                val name: String? = null, // USD
                val oddsLimit: Int? = null, // 10000
                val preMaxPL: Int? = null, // 500
                val preMaxStake: Int? = null, // 200
                val preMinStake: Int? = null // 100
            )
            data class Runner(
                val name: String? = null, // Assembly Election 2023
                val selectionId: Int? = null // 953181
            )
        }

        data class MatchRunner(
            val name: String? = null, // Assembly Election 2023
            val selectionId: Int? = null // 953181
        )
    }
}