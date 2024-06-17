package com.example.nextclass.appComponent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nextclass.Data.ClassData

import kotlin.math.roundToInt

@Composable
fun BasicEvent(
    classData: ClassData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(end = 2.dp, bottom = 2.dp)
            .background(classData.color, shape = RoundedCornerShape(4.dp))
            .padding(4.dp)
    ) {
        Text(
            text = "${classData.startClassTime} - ${classData.endClassTime}",
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = classData.className,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = classData.dayOfWeek.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

val sampleEvents = listOf(
    ClassData(
        "컴퓨터수학2",
        "정보관 201호",
        1,
        1,
        2,
        3,
        Color(0xFFF58284)
    ),

    ClassData(
        "컴퓨터수학3",
        "정보관 201호",
        2,
        2,
        3,
        3,
        Color(0xFFF58284)
    ),
    ClassData(
        "컴퓨터수학6",
    "정보관 201호",
    5,
    3,
    4,
    3,
        Color(0xFFF58284)
    ),
    ClassData(
        "컴퓨터수학6",
        "정보관 201호",
        5,
        1,
        2,
        3,
        Color(0xFFF58284)
    ),
    ClassData(
        "컴퓨터수학6",
        "정보관 201호",
        3,
        2,
        2,
        3,
        Color(0xFFF58284)
    )
)

class EventsProvider : PreviewParameterProvider<ClassData> {
    override val values = sampleEvents.asSequence()
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun EventPreview(
    @PreviewParameter(EventsProvider::class) classData : ClassData,) {


    MaterialTheme {
        Schedule(sampleEvents)
    }
}

@Composable
fun Schedule(
    classDatas: List<ClassData>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (classData: ClassData) -> Unit = { BasicEvent(classData = it) },
    minClassTime: Int = classDatas.minByOrNull(ClassData::startClassTime)!!.startClassTime,
    maxClassTime: Int = classDatas.maxByOrNull(ClassData::endClassTime)!!.endClassTime,
) {
    val classTimeHeight = 70.dp
    var sidebarWidth by remember { mutableIntStateOf(0) }

    Column(modifier = modifier.fillMaxSize()) {
        DayOfWeekHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = with(LocalDensity.current) { sidebarWidth.toDp() })
        )

        Row(modifier = Modifier
//            .weight(1f)

        ) {
            ScheduleSidebar(
                hourHeight = classTimeHeight,
                modifier = Modifier.onGloballyPositioned { sidebarWidth = it.size.width }
            )
            BasicSchedule(
                classDatas = classDatas,
                eventContent = eventContent,
                minClassTime = minClassTime,
                maxClassTime = maxClassTime,
                hourHeight = classTimeHeight,
                modifier = Modifier.fillMaxWidth()

            )
        }
    }
}

@Composable
fun BasicSchedule(
    classDatas: List<ClassData>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (classData: ClassData) -> Unit = { BasicEvent(classData = it) },
    minClassTime: Int = classDatas.minByOrNull(ClassData::startClassTime)!!.startClassTime,
    maxClassTime: Int = classDatas.maxByOrNull(ClassData::endClassTime)!!.endClassTime,
    hourHeight: Dp,
) {
    val totalPeriods = maxClassTime - minClassTime + 1
    val totalDays = 5
    val totalHourHeight=hourHeight*7

    Layout(
        content = {
            classDatas.sortedBy(ClassData::startClassTime).forEach { classData ->
                Box(modifier = Modifier
                    .layoutId(classData)
                    .fillMaxSize()
                ) {
                    eventContent(classData)
                }
            }
        },
        modifier = modifier
    ) { measurables, constraints ->
        val height = hourHeight.roundToPx() * totalPeriods
        val width = constraints.maxWidth / totalDays

        val placeablesWithEvents = measurables.map { measurable ->
            val classData = measurable.layoutId as ClassData
            val classDurationPeriods = (classData.endClassTime - classData.startClassTime + 1)
            val classHeight = (classDurationPeriods * hourHeight.toPx()).roundToInt()

            val placeable = measurable.measure(
                constraints.copy(
                    minWidth = width,
                    maxWidth = width,
                    minHeight = classHeight,
                    maxHeight = classHeight
                )
            )
            Pair(placeable, classData)
        }

        layout(constraints.maxWidth, height) {
            placeablesWithEvents.forEach { (placeable, classData) ->
                val eventOffsetPeriods = classData.startClassTime - minClassTime
                val eventY = (eventOffsetPeriods * hourHeight.toPx()).roundToInt()
                val eventX = (classData.dayOfWeek - 1) * width
                placeable.place(eventX, eventY)
            }
        }
    }
}


@Composable
fun BasicDayHeader(
    day: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = day,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    )
}

@Composable
fun DayOfWeekHeader(
    modifier: Modifier = Modifier,
    dayHeader: @Composable (dayOfWeek: String) -> Unit = { BasicDayHeader(day = it) },
) {
    val daysOfWeek = listOf("월요일", "화요일", "수요일", "목요일", "금요일")
    Row(modifier = modifier) {
        daysOfWeek.forEach { day ->
            Box(modifier = Modifier.weight(1f)) {
                dayHeader(day)
            }
        }
    }
}


@Composable
fun ScheduleSidebar(
    hourHeight: Dp,
    modifier: Modifier = Modifier,
    label: @Composable (time: Int) -> Unit = { BasicSidebarLabel(time = it) },
) {
    Column(modifier = modifier) {

        repeat(7) { i ->
            Box(modifier = Modifier.height(hourHeight)
            ) {
                label(i+1)
            }

        }
    }
}

@Composable
fun BasicSidebarLabel(
    time: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "$time 교시",
        modifier = modifier
            .fillMaxHeight()
            .padding(4.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun ScheduleHeaderPreview() {
    MaterialTheme {
        BasicSchedule(
            classDatas = sampleEvents,

            hourHeight = 90.dp,
        )
    }
}

