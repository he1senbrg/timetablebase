package com.vte.timetable.complication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.wear.watchface.complications.data.ComplicationData
import androidx.wear.watchface.complications.data.ComplicationType
import androidx.wear.watchface.complications.data.PlainComplicationText
import androidx.wear.watchface.complications.data.ShortTextComplicationData
import androidx.wear.watchface.complications.data.TimeRange
import androidx.wear.watchface.complications.datasource.ComplicationRequest
import androidx.wear.watchface.complications.datasource.SuspendingComplicationDataSourceService
import com.vte.timetable.tile.getCurrentPeriod
import java.time.Instant
import java.util.Calendar

/**
 * Skeleton for complication data source that returns short text.
 */
class MainComplicationService : SuspendingComplicationDataSourceService() {

    private val updateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Trigger an update to your complication here
            val updateIntent = Intent("com.vte.timetable.complication.UPDATE_COMPLICATION")
            context.sendBroadcast(updateIntent)
        }
    }

    private val complicationUpdateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == "com.vte.timetable.complication.UPDATE_COMPLICATION") {
                // Update your complication data here
                createComplicationData(getCurrentPeriod(), getCurrentPeriod())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter().apply {
            addAction("com.vte.timetable.complication.UPDATE_COMPLICATION")
        }
        registerReceiver(updateReceiver, filter, RECEIVER_NOT_EXPORTED)
        registerReceiver(complicationUpdateReceiver, filter, RECEIVER_NOT_EXPORTED)
        setUpdateAlarms()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(updateReceiver)
        unregisterReceiver(complicationUpdateReceiver)
    }

    override fun getPreviewData(type: ComplicationType): ComplicationData? {
        if (type != ComplicationType.SHORT_TEXT) {
            return null
        }
        return createComplicationData(getCurrentPeriod(), getCurrentPeriod())
    }

    override suspend fun onComplicationRequest(request: ComplicationRequest): ComplicationData {
        return createComplicationData(getCurrentPeriod(), getCurrentPeriod())
    }

    private fun createComplicationData(text: String, contentDescription: String) =
        ShortTextComplicationData.Builder(
            text = PlainComplicationText.Builder(text).build(),
            contentDescription = PlainComplicationText.Builder(contentDescription).build()
        ).build()

    private fun setUpdateAlarms() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MainComplicationService::class.java)
        val pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val updateTimes = arrayOf(arrayOf(9,0), arrayOf(9,50), arrayOf(10,40), arrayOf(10,50), arrayOf(11,40), arrayOf(12,30), arrayOf(13,20), arrayOf(14,10), arrayOf(15,0), arrayOf(15,10), arrayOf(16,0), arrayOf(16,50))
        for (time in updateTimes) {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, time[0])
                set(Calendar.MINUTE, time[1])
            }

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }
}