package com.vte.timetable.tile

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ResourceBuilders
import androidx.wear.protolayout.TimelineBuilders
import androidx.wear.protolayout.material.Button
import androidx.wear.protolayout.material.CompactChip
import androidx.wear.protolayout.material.Text
import androidx.wear.protolayout.material.layouts.MultiButtonLayout
import androidx.wear.protolayout.material.layouts.MultiSlotLayout
import androidx.wear.protolayout.material.layouts.PrimaryLayout
import androidx.wear.tiles.ColorBuilders.argb
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.tools.LayoutRootPreview
import com.google.android.horologist.tiles.SuspendingTileService
import com.vte.timetable.emptyClickable
import com.vte.timetable.emptyPar
import com.vte.timetable.presentation.ListFriday
import com.vte.timetable.presentation.ListMonday
import com.vte.timetable.presentation.ListThursday
import com.vte.timetable.presentation.ListTuesday
import com.vte.timetable.presentation.ListWednesday
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Calendar


private const val RESOURCES_VERSION = "0"

/**
 * Skeleton for a tile with no images.
 */
@OptIn(ExperimentalHorologistApi::class)
 class MainTileService : SuspendingTileService() {

    override suspend fun resourcesRequest(
        requestParams: RequestBuilders.ResourcesRequest
    ): ResourceBuilders.Resources {
        return ResourceBuilders.Resources.Builder().setVersion(RESOURCES_VERSION).build()
    }

    override suspend fun tileRequest(
        requestParams: RequestBuilders.TileRequest
    ): TileBuilders.Tile {
        val singleTileTimeline = TimelineBuilders.Timeline.Builder().addTimelineEntry(
            TimelineBuilders.TimelineEntry.Builder().setLayout(
                LayoutElementBuilders.Layout.Builder().setRoot(tileLayout(this)).build()
            ).build()
        ).build()

        return TileBuilders.Tile.Builder().setResourcesVersion(RESOURCES_VERSION)
            .setFreshnessIntervalMillis(300000)
            .setTileTimeline(singleTileTimeline).build()
    }
}

private fun tileLayout(context: Context): LayoutElementBuilders.LayoutElement {
    val cText = MultiSlotLayout.Builder()
        .addSlotContent(
            LayoutElementBuilders.Text.Builder()
                .setText("Current : " + getCurrentPeriod())
                .build()
        )
        .build()

    val sText = MultiSlotLayout.Builder()
        .addSlotContent(
            LayoutElementBuilders.Text.Builder()
                .setText(" ")
                .build()
        )
        .build()

    val nText = MultiSlotLayout.Builder()
        .addSlotContent(
            LayoutElementBuilders.Text.Builder()
                .setText("Next : " + getNextPeriod())
                .build()
        )
        .build()

    val refreshButton =  (
            PrimaryLayout.Builder(emptyPar)
            .setPrimaryChipContent(
                CompactChip.Builder(context, "Refresh",  emptyClickable, emptyPar)
                    .build()
            )
            .build()
    )
    val box: LayoutElementBuilders.Column = LayoutElementBuilders.Column.Builder().addContent(sText).addContent(cText).addContent(sText).addContent(nText).addContent(sText).addContent(refreshButton).build()
    return box
}


@Preview(
    device = Devices.WEAR_OS_SMALL_ROUND,
    showSystemUi = true,
    backgroundColor = 0xff000000,
    showBackground = true
)
@Composable
fun TilePreview() {
    LayoutRootPreview(root = tileLayout(LocalContext.current))
}

val calender = Calendar.getInstance();

val current = LocalDateTime.of(
    calender.get(Calendar.YEAR),
    calender.get(Calendar.MONTH),
    calender.get(Calendar.DAY_OF_MONTH),
    calender.get(Calendar.HOUR_OF_DAY),
    calender.get(Calendar.MINUTE),
    calender.get(Calendar.SECOND)
)

fun getCurrentPeriod(): String {
    val currentDay : Int = calender.get(Calendar.DAY_OF_WEEK);
    val cPeriod: Int = getPeriodIndex();
    var cList: String = "Nothing!";
    if (currentDay == 2){
        if ( cPeriod == 1) {
            cList = ListMonday[1]
        }
        else if ( cPeriod == 2) {
            cList = ListMonday[2]
        }
        else if ( cPeriod == 3) {
            cList = ListMonday[3]
        }
        else if ( cPeriod == 4) {
            cList = ListMonday[4]
        }
        else if ( cPeriod == 5) {
            cList = ListMonday[5]
        }
        else if ( cPeriod == 6) {
            cList = ListMonday[6]
        }
        else if ( cPeriod == 7) {
            cList = ListMonday[7]
        }
        else if ( cPeriod == 8) {
            cList = ListMonday[8]
        }
        else if ( cPeriod == 9) {
            cList = ListMonday[9]
        }
    }
    else if (currentDay == 3){
        if ( cPeriod == 1) {
            cList = ListTuesday[1]
        }
        else if ( cPeriod == 2) {
            cList = ListTuesday[2]
        }
        else if ( cPeriod == 3) {
            cList = ListTuesday[3]
        }
        else if ( cPeriod == 4) {
            cList = ListTuesday[4]
        }
        else if ( cPeriod == 5) {
            cList = ListTuesday[5]
        }
        else if ( cPeriod == 6) {
            cList = ListTuesday[6]
        }
        else if ( cPeriod == 7) {
            cList = ListTuesday[7]
        }
        else if ( cPeriod == 8) {
            cList = ListTuesday[8]
        }
        else if ( cPeriod == 9) {
            cList = ListTuesday[9]
        }
    }
    else if (currentDay == 4){
        if ( cPeriod == 1) {
            cList = ListWednesday[1]
        }
        else if ( cPeriod == 2) {
            cList = ListWednesday[2]
        }
        else if ( cPeriod == 3) {
            cList = ListWednesday[3]
        }
        else if ( cPeriod == 4) {
            cList = ListWednesday[4]
        }
        else if ( cPeriod == 5) {
            cList = ListWednesday[5]
        }
        else if ( cPeriod == 6) {
            cList = ListWednesday[6]
        }
        else if ( cPeriod == 7) {
            cList = ListWednesday[7]
        }
        else if ( cPeriod == 8) {
            cList = ListWednesday[8]
        }
        else if ( cPeriod == 9) {
            cList = ListWednesday[9]
        }
    }
    else if (currentDay == 5){
        if ( cPeriod == 1) {
            cList = ListThursday[1]
        }
        else if ( cPeriod == 2) {
            cList = ListThursday[2]
        }
        else if ( cPeriod == 3) {
            cList = ListThursday[3]
        }
        else if ( cPeriod == 4) {
            cList = ListThursday[4]
        }
        else if ( cPeriod == 5) {
            cList = ListThursday[5]
        }
        else if ( cPeriod == 6) {
            cList = ListThursday[6]
        }
        else if ( cPeriod == 7) {
            cList = ListThursday[7]
        }
        else if ( cPeriod == 8) {
            cList = ListThursday[8]
        }
        else if ( cPeriod == 9) {
            cList = ListThursday[9]
        }
    }
    else if (currentDay == 6){
        if ( cPeriod == 1) {
            cList = ListFriday[1]
        }
        else if ( cPeriod == 2) {
            cList = ListFriday[2]
        }
        else if ( cPeriod == 3) {
            cList = ListFriday[3]
        }
        else if ( cPeriod == 4) {
            cList = ListFriday[4]
        }
        else if ( cPeriod == 5) {
            cList = ListFriday[5]
        }
        else if ( cPeriod == 6) {
            cList = ListFriday[6]
        }
        else if ( cPeriod == 7) {
            cList = ListFriday[7]
        }
        else if ( cPeriod == 8) {
            cList = ListFriday[8]
        }
        else if ( cPeriod == 9) {
            cList = ListFriday[9]
        }
    }
    return cList
}

fun getNextPeriod(): String {
    val currentDay : Int = calender.get(Calendar.DAY_OF_WEEK);
    val cPeriod: Int = getPeriodIndex();
    var cList: String = "Nothing!";
    if (currentDay == 2){
        if ( cPeriod == 1) {
            cList = ListMonday[2]
        }
        else if ( cPeriod == 2) {
            cList = ListMonday[3]
        }
        else if ( cPeriod == 3) {
            cList = ListMonday[4]
        }
        else if ( cPeriod == 4) {
            cList = ListMonday[5]
        }
        else if ( cPeriod == 5) {
            cList = ListMonday[6]
        }
        else if ( cPeriod == 6) {
            cList = ListMonday[7]
        }
        else if ( cPeriod == 7) {
            cList = ListMonday[8]
        }
        else if ( cPeriod == 8) {
            cList = ListMonday[9]
        }
        else if ( cPeriod == 9) {
            cList = "Nothing!"
        }
    }
    else if (currentDay == 3){
        if ( cPeriod == 1) {
            cList = ListTuesday[2]
        }
        else if ( cPeriod == 2) {
            cList = ListTuesday[3]
        }
        else if ( cPeriod == 3) {
            cList = ListTuesday[4]
        }
        else if ( cPeriod == 4) {
            cList = ListTuesday[5]
        }
        else if ( cPeriod == 5) {
            cList = ListTuesday[6]
        }
        else if ( cPeriod == 6) {
            cList = ListTuesday[7]
        }
        else if ( cPeriod == 7) {
            cList = ListTuesday[8]
        }
        else if ( cPeriod == 8) {
            cList = ListTuesday[9]
        }
        else if ( cPeriod == 9) {
            cList = "Nothing!"
        }
    }
    else if (currentDay == 4){
        if ( cPeriod == 1) {
            cList = ListWednesday[2]
        }
        else if ( cPeriod == 2) {
            cList = ListWednesday[3]
        }
        else if ( cPeriod == 3) {
            cList = ListWednesday[4]
        }
        else if ( cPeriod == 4) {
            cList = ListWednesday[5]
        }
        else if ( cPeriod == 5) {
            cList = ListWednesday[6]
        }
        else if ( cPeriod == 6) {
            cList = ListWednesday[7]
        }
        else if ( cPeriod == 7) {
            cList = ListWednesday[8]
        }
        else if ( cPeriod == 8) {
            cList = ListWednesday[9]
        }
        else if ( cPeriod == 9) {
            cList = "Nothing!"
        }
    }
    else if (currentDay == 5){
        if ( cPeriod == 1) {
            cList = ListThursday[2]
        }
        else if ( cPeriod == 2) {
            cList = ListThursday[3]
        }
        else if ( cPeriod == 3) {
            cList = ListThursday[4]
        }
        else if ( cPeriod == 4) {
            cList = ListThursday[5]
        }
        else if ( cPeriod == 5) {
            cList = ListThursday[6]
        }
        else if ( cPeriod == 6) {
            cList = ListThursday[7]
        }
        else if ( cPeriod == 7) {
            cList = ListThursday[8]
        }
        else if ( cPeriod == 8) {
            cList = ListThursday[9]
        }
        else if ( cPeriod == 9) {
            cList = "Nothing!"
        }
    }
    else if (currentDay == 6){
        if ( cPeriod == 1) {
            cList = ListFriday[2]
        }
        else if ( cPeriod == 2) {
            cList = ListFriday[3]
        }
        else if ( cPeriod == 3) {
            cList = ListFriday[4]
        }
        else if ( cPeriod == 4) {
            cList = ListFriday[5]
        }
        else if ( cPeriod == 5) {
            cList = ListFriday[6]
        }
        else if ( cPeriod == 6) {
            cList = ListFriday[7]
        }
        else if ( cPeriod == 7) {
            cList = ListFriday[8]
        }
        else if ( cPeriod == 8) {
            cList = ListFriday[9]
        }
        else if ( cPeriod == 9) {
            cList = "Nothing!"
        }
    }
    return cList
}

fun isCurrentTimeInRange(start: LocalTime, end: LocalTime): Boolean {
    val currentTime = LocalTime.now()
    return !currentTime.isBefore(start) && !currentTime.isAfter(end)
}

fun getPeriodIndex(): Int {
    var periodIndex : Int;

    if (isCurrentTimeInRange(LocalTime.of(9,0), LocalTime.of(9,50))) {
        periodIndex = 1;
    }
    else if (isCurrentTimeInRange(LocalTime.of(9,50), LocalTime.of(10,40))) {
        periodIndex = 2;
    }
    else if (isCurrentTimeInRange(LocalTime.of(10,50), LocalTime.of(11,40))) {
        periodIndex = 3;
    }
    else if (isCurrentTimeInRange(LocalTime.of(11,40), LocalTime.of(12,30))) {
        periodIndex = 4;
    }
    else if (isCurrentTimeInRange(LocalTime.of(12,30), LocalTime.of(13,20))) {
        periodIndex = 5;
    }
    else if (isCurrentTimeInRange(LocalTime.of(13,20), LocalTime.of(14,10))) {
        periodIndex = 6;
    }
    else if (isCurrentTimeInRange(LocalTime.of(14,10), LocalTime.of(15,0))) {
        periodIndex = 7;
    }
    else if (isCurrentTimeInRange(LocalTime.of(15,10), LocalTime.of(16,0))) {
        periodIndex = 8;
    }
    else if (isCurrentTimeInRange(LocalTime.of(16,0), LocalTime.of(16,50))) {
        periodIndex = 9;
    }
    else {
        periodIndex = 0;
    }

    return  periodIndex
}