package com.live.bets.appUtils

import android.text.TextUtils
import com.live.bets.BetsAppConstants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


/**
 * Time helper functions.
 */

object DateTimeUtil {
    fun getCurrentDateAndTime(): String? {
        return SimpleDateFormat(
            BetsAppConstants.DateTimeFormatterConstants.DATE_TIME_CONSTANTS_1,
            Locale.ENGLISH
        ).format(Calendar.getInstance().time)
    }

    fun dateToLongValue(dateToEvaluate: String): Long {
        if (TextUtils.isEmpty(dateToEvaluate))
            return 0
        return SimpleDateFormat(
            BetsAppConstants.DateTimeFormatterConstants.DATE_TIME_CONSTANTS_1,
            Locale.ENGLISH
        ).parse(dateToEvaluate)?.time ?: 0
    }
    fun getTodayDayEndTime(): String {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR] = 11
        cal[Calendar.MINUTE] = 59
        cal[Calendar.SECOND] = 0
        cal[Calendar.MILLISECOND] = 0
        cal[Calendar.AM_PM] = Calendar.PM
        return SimpleDateFormat(
             BetsAppConstants.DateTimeFormatterConstants.DATE_TIME_CONSTANTS_1,
             Locale.ENGLISH
         ).format(cal.time)

    }


}


