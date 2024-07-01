package com.example.nextclass.appComponent

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextclass.R
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Feldgrau
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.utils.MaxTextCount
import com.example.nextclass.utils.TimeFormatter
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDateBottomSheet(
    onDismiss: () -> Unit,
    updateSelectData: (LocalDate)->Unit,

    ) {
    val modalBottomSheetState = rememberModalBottomSheetState()



    ModalBottomSheet(

        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        CustomDatePicker(

            updateSelectData = updateSelectData
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = "확인")
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(

    updateSelectData: (LocalDate) -> Unit
) {
    val dateState = rememberDatePickerState()

    LaunchedEffect(dateState.selectedDateMillis) {
        dateState.selectedDateMillis?.let {
            val selectedDateTime = Instant.ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            updateSelectData(selectedDateTime)
        }
    }

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Pastel_Red, // 선택된 날짜 색상
        )
    ) {
        Column {
            DatePicker(
                state = dateState,
                title = {
                    Text(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                        text = "날짜 선택",
                        fontSize = 20.sp
                    )
                },
                headline = {
                    Text(
                        modifier = Modifier.padding(start = 10.dp,bottom=10.dp),
                        text = "날짜를 선택해 주세요.",
                        fontSize = 15.sp
                    )
                },
                showModeToggle = false
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTimeBottomSheet(
    onDismiss: () -> Unit,
    updateSelectTime: (LocalTime) -> Unit

    ) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(

        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        CustomTimePicker(

            updateSelectTime=updateSelectTime
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = "확인")
            }

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePicker(
    updateSelectTime: (LocalTime) -> Unit
) {
    val timeState = rememberTimePickerState()

    LaunchedEffect(timeState.hour, timeState.minute) {
        val selectedTime = LocalTime.of(timeState.hour, timeState.minute)
        updateSelectTime(selectedTime)
    }
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Pastel_Red,
            onPrimary = Color.White,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            TimePicker(
                state = timeState,
            )
        }
    }
}

@Composable
fun ScheduleDateTimePickerView(
    selectDate: LocalDate,
    selectTime: LocalTime,
    onDateClick: () -> Unit,
    onTimeClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(start = 7.dp, end = 7.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Feldgrau)
            .height(90.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(40.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.calender_icon),
                    contentDescription = "",
                    tint = Color.White,
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = TimeFormatter.formatDate(selectDate),
                        color = Color.White,
                        modifier = Modifier
                    )
                    TextButton(
                        onClick = onDateClick,
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.height(25.dp)
                    ) {
                        Text(
                            text = "날짜 선택하기",
                            color = Color.White
                        )
                    }
                }
            }

            Divider(
                color = Color.White,
                modifier = Modifier
                    .height(50.dp)
                    .width(1.dp)
            )

            Row {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(40.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.schedule_icon),
                    contentDescription = "",
                    tint = Color.White,
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = TimeFormatter.formatTime(selectTime),
                        color = Color.White,
                        modifier = Modifier
                    )
                    TextButton(
                        onClick = onTimeClick,
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.height(25.dp)
                    ) {
                        Text(
                            text = "시간 선택하기",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScheduleTextInsertView(
    text:String,
    onValueChange:(String)->Unit,
    textCount:Int
) {


    Column() {

        Text(
            text="스케쥴 내용",
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 12.dp,bottom=20.dp),
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .padding(start = 7.dp, end = 7.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .border(1.5.dp, Color.LightGray, shape = RoundedCornerShape(15.dp))
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {

            BasicTextField(
                value = text,
                onValueChange = {
                    if (it.length <= MaxTextCount) {
                        onValueChange(it)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 200.dp)
                    .padding(14.dp),
                textStyle = TextStyle.Default.copy(fontSize = 20.sp),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.TopStart
                    ) {
                        if (text.isEmpty()) {
                            Text(
                                text = "내용을 입력해 주세요",
                                style = TextStyle.Default.copy(color = Color.Gray, fontSize = 20.sp)
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }


        Text(
            text="${textCount}/${MaxTextCount}",
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 25.dp, top = 3.dp),
            fontSize = 12.sp,
            color = Color.DarkGray
            )
    }
}

@Composable
fun SingleScheduleView(
    scheduleDetail:String="물방울이 떨어지는 소리를 들으며 창밖을 바라보는 시간은 참 평화로워.",
    scheduleDate:Pair<String,String> = TimeFormatter.formatTimeAndSplit( LocalDateTime.now()),

) {


        Surface(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .height(130.dp)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(Background_Color2)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),

                ) {
                    Column(
                        modifier = Modifier
                            .padding(top=20.dp,bottom=20.dp,start=15.dp,end=10.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Feldgrau)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = scheduleDate.first,
                            color=Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier

                                .padding(start=10.dp,end=10.dp,bottom=3.dp)
                        )
                        Text(
                            text = scheduleDate.second,
                            color=Color.White,
                            modifier = Modifier

                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxSize()

                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically),

                            text = scheduleDetail,
                            color = Color.Black
                        )
                    }
                }

            }
        }
}


@Preview(showBackground = true)
@Composable
fun SelectDatePreview() {
    CustomDatePicker() {

    }
}


@Preview(showBackground = true)
@Composable
fun SelectTimePreview() {
    CustomTimePicker(updateSelectTime = {})

}

@Preview(showBackground = true)
@Composable
fun ScheduleTextInsertPreview() {
//    ScheduleTextInsertView(text = "", onValueChange = {}, textCount = 0)
    SingleScheduleView()

}
