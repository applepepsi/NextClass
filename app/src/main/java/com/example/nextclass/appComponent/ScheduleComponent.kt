package com.example.nextclass.appComponent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextclass.Data.ScheduleData
import com.example.nextclass.Data.SortOption
import com.example.nextclass.R
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Feldgrau
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.utils.MaxTextCount
import com.example.nextclass.utils.TimeFormatter
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId


@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
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


@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
val scheduleDataList=listOf(
    ScheduleData(
        content = "물방울이 떨어지는 소리를 들으며 창밖을 바라보는 시간은 참 평화로워.",
        alarm_time = LocalDateTime.now()
    ),
    ScheduleData(
        content = "가나다라",
        alarm_time = LocalDateTime.now()
    ),
    ScheduleData(
        content = "가나다라",
        alarm_time = LocalDateTime.now()
    ),
    ScheduleData(
        content = "가나다라",
        alarm_time = LocalDateTime.now()
    ),
    ScheduleData(
        content = "가나다라",
        alarm_time = LocalDateTime.now()
    ),
    ScheduleData(
        content = "가나다라",
        alarm_time = LocalDateTime.now()
    ),
    ScheduleData(
        content = "가나다라",
        alarm_time = LocalDateTime.now()
    ),
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SingleScheduleView(
    scheduleDetail: String = "물방울이 떨어지는 소리를 들으며 창밖을 바라보는 시간은 참 평화로워.",
    scheduleDate: Pair<String, String> = TimeFormatter.formatTimeAndSplit(LocalDateTime.now()),
    modifySchedule: () -> Unit,
    deleteSchedule: () -> Unit
    ) {


            Surface(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .height(150.dp)
                    .padding(start = 20.dp, end = 20.dp)
            ) {


                Column(
                    modifier = Modifier
                        .background(Background_Color2)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 25.dp, top = 8.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        SingleScheduleOptionIcon(
                            onClick = {
                                modifySchedule()
                            },
                            iconImage = ImageVector.vectorResource(R.drawable.write_icon)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        SingleScheduleOptionIcon(
                            onClick = {
                                deleteSchedule()
                            },
                            iconImage = ImageVector.vectorResource(R.drawable.trash_icon)
                        )
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxSize(),

                        ) {



                        Column(
                            modifier = Modifier
                                .padding(bottom = 20.dp, start = 15.dp, end = 10.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Feldgrau)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = scheduleDate.first,
                                color = Color.White,
                                fontSize = 15.sp,
                                modifier = Modifier

                                    .padding(start = 10.dp, end = 10.dp, bottom = 3.dp)
                            )
                            Text(
                                text = scheduleDate.second,
                                color = Color.White,
                                modifier = Modifier

                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 10.dp, bottom = 20.dp,)

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


@Composable
fun SingleScheduleOptionIcon(
    onClick:()->Unit,
    iconImage:ImageVector=ImageVector.vectorResource(R.drawable.schedule_icon)
    ) {
    Row(
        modifier = Modifier
            .width(35.dp)
            .height(22.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Pastel_Red),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ){
        IconButton(onClick = { onClick() }) {
            Icon(
                modifier = Modifier

                    .size(15.dp),
                imageVector = iconImage,
                contentDescription = "",
                tint = Color.Black,
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SingleScheduleGridView(
    scheduleDetail:String="물방울이 떨어지는 소리를 들으며 창밖을 바라보는 시간은 참 평화로워.",
    scheduleDate:Pair<String,String> = TimeFormatter.formatTimeAndSplit( LocalDateTime.now()),
    modifySchedule: (ScheduleData) -> Unit,
    deleteSchedule: (ScheduleData) -> Unit
    ) {
    Surface(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .heightIn(min = 200.dp)

            .padding(start = 10.dp, end = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Background_Color2)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),

                ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 25.dp, top = 12.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    SingleScheduleOptionIcon(
                        onClick = {

                        },
                        iconImage = ImageVector.vectorResource(R.drawable.write_icon)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    SingleScheduleOptionIcon(
                        onClick = {

                        },
                        iconImage = ImageVector.vectorResource(R.drawable.trash_icon)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 5.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Feldgrau)
                        .height(60.dp)
                        .fillMaxWidth(),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = scheduleDate.first,
                        color=Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(end=5.dp)

                    )



                    Text(
                        text = scheduleDate.second,
                        color=Color.White,
                        modifier = Modifier,
                        fontSize = 16.sp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 20.dp)
                ) {
                    Text(
                        modifier = Modifier,


                        text = scheduleDetail,
                        color = Color.Black
                    )
                }
            }

        }
    }
}

val sortOptions = listOf(
    SortOption("작성 시간 오름 차순", R.drawable.up_button),
    SortOption("작성 시간 내림 차순", R.drawable.down_button),
    SortOption("남은 시간 오름 차순", R.drawable.up_button),
    SortOption("남은 시간 내림 차순", R.drawable.down_button)
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheetComponent(
    bottomSheetShowState:Boolean=true,
    setSortType: (String) -> Unit,
    toggleBottomSheetState:()->Unit,
) {

    //숨김
    val state1=rememberModalBottomSheetState()
    //보여주기 테스트용
    val state2=rememberStandardBottomSheetState()

    if(bottomSheetShowState){
        ModalBottomSheet(
            onDismissRequest = {
                toggleBottomSheetState()
            },
            sheetState = state1,

            ){

            LazyColumn {
                items(items= sortOptions){ sortOption->
                    SortBottomSheetSingleItemComponent(
                        sortItem=sortOption.label,
                        icon=ImageVector.vectorResource(sortOption.iconRes),
                        setSortType = {
                            setSortType(sortOption.label)
                            toggleBottomSheetState()
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun SortBottomSheetSingleItemComponent(
    sortItem:String="",
    icon: ImageVector,
    setSortType:()->Unit
) {
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable {
                setSortType()
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text=sortItem,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
        )
        Spacer(modifier = Modifier.width(15.dp))
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier
                .size(20.dp),
            tint= Pastel_Red
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SelectDatePreview() {
    CustomDatePicker() {

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SelectTimePreview() {
    CustomTimePicker(updateSelectTime = {})

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ScheduleTextInsertPreview() {
//    ScheduleTextInsertView(text = "", onValueChange = {}, textCount = 0)
    SingleScheduleView(
        deleteSchedule = {},
        modifySchedule = {}
    )

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GridViewPreview() {
//    ScheduleTextInsertView(text = "", onValueChange = {}, textCount = 0)
    SingleScheduleGridView(
        deleteSchedule = {},
        modifySchedule = {},
    )

}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
//    ScheduleTextInsertView(text = "", onValueChange = {}, textCount = 0)
    SortBottomSheetComponent(
        setSortType = {},
        toggleBottomSheetState = {}
    )

}


@Preview(showBackground = true)
@Composable
fun BottomSheetSingleItemPreview() {
//    ScheduleTextInsertView(text = "", onValueChange = {}, textCount = 0)
    SortBottomSheetSingleItemComponent(
        sortOptions[0].label,ImageVector.vectorResource(sortOptions[0].iconRes), setSortType = {}
    )

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SingleScheduleIconPreview() {
//    ScheduleTextInsertView(text = "", onValueChange = {}, textCount = 0)
    SingleScheduleOptionIcon(
        onClick = {}
    )

}