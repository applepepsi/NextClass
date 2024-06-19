package com.example.nextclass.appComponent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nextclass.Data.ClassData

import kotlin.math.roundToInt

@Composable
fun OneClassCellDetailComponent(
    classData: ClassData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
//            .padding(end = 2.dp, bottom = 2.dp)
            .background(classData.color)
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

val sampleEvents =
    listOf(
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


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun EventPreview() {

    MaterialTheme {
        TimeTableComponent(sampleEvents)
    }
}

@Composable
fun TimeTableComponent(
    classDataList: List<ClassData>,
    modifier: Modifier = Modifier,
    classContent: @Composable (classData: ClassData) -> Unit = { OneClassCellDetailComponent(classData = it) },
    minClassTime: Int = classDataList.minByOrNull(ClassData::startClassTime)?.startClassTime ?:0,
    maxClassTime: Int = classDataList.maxByOrNull(ClassData::endClassTime)?.endClassTime?:7,
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
            BasicClass(
                classDataList = classDataList,
                classContent = classContent,
                minClassTime = minClassTime,
                maxClassTime = maxClassTime,
                hourHeight = classTimeHeight,
                modifier = Modifier.fillMaxWidth()

            )
        }
    }
}

@Composable
fun BasicClass(
    classDataList: List<ClassData>,
    modifier: Modifier = Modifier,
    classContent: @Composable (classData: ClassData) -> Unit = { OneClassCellDetailComponent(classData = it) },
    minClassTime: Int = classDataList.minByOrNull(ClassData::startClassTime)?.startClassTime?:0,
    maxClassTime: Int = classDataList.maxByOrNull(ClassData::endClassTime)?.endClassTime?:7,
    hourHeight: Dp,
) {
    val totalPeriods = maxClassTime - minClassTime + 1
    val totalDays = 5
    val totalHourHeight = hourHeight * 7
    val dividerColor = Color.LightGray

    Layout(
        content = {
            classDataList.sortedBy(ClassData::startClassTime).forEach { classData ->
                Box(modifier = Modifier
                    .layoutId(classData)
                    .fillMaxSize()
                ) {
                    classContent(classData)
                }
            }
        },
        modifier = modifier
            .drawBehind {
                val dayWidth = size.width / totalDays

                for (i in 0..totalDays) {
                    val startX = i * dayWidth
                    drawLine(
                        dividerColor,
                        start = Offset(startX, 0f),
                        end = Offset(startX, size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                for (i in 0..7) {
                    val startY = i * hourHeight.toPx()
                    drawLine(
                        dividerColor,
                        start = Offset(0f, startY),
                        end = Offset(size.width, startY),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
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

        layout(constraints.maxWidth, totalHourHeight.roundToPx()) {
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
fun DayHeader(
    day: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = day,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
//            .padding(4.dp)
    )
}

@Composable
fun DayOfWeekHeader(
    modifier: Modifier = Modifier,
    dayHeader: @Composable (dayOfWeek: String) -> Unit = { DayHeader(day = it) },
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
    label: @Composable (time: Int) -> Unit = { TimeLabel(time = it) },
) {
    Column(modifier = modifier) {
        repeat(7) { i ->
            Box(
                modifier = Modifier
                    .height(hourHeight),
                contentAlignment = Alignment.Center
            ) {
                label(i + 1)
            }
        }
    }
}

@Composable
fun TimeLabel(
    time: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "${time}교시",
        modifier = Modifier


    )
}


@Preview(showBackground = true)
@Composable
fun ScheduleHeaderPreview() {
    MaterialTheme {
        BasicClass(
            classDataList = sampleEvents,

            hourHeight = 90.dp,
        )
    }
}

