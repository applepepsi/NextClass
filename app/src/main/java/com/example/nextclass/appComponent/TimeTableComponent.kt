package com.example.nextclass.appComponent

import android.R
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.nextclass.Data.ClassData
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.viewmodel.TimeTableViewModel

import kotlin.math.roundToInt

@Composable
fun OneClassCellDetailComponent(
    classData: ClassData,
    modifier: Modifier = Modifier,
    setSelectClassData: (ClassData) -> Unit,
    setShowClassDetailDialog: () -> Unit,
    setShowClassDataModifyDialog: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(classData.color)
            .padding(4.dp)
            .clickable {
                setSelectClassData(classData)
                setShowClassDetailDialog()
            }
    ) {

        Text(
            text = classData.className,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )

        Text(
            text =if(classData.startClassTime==classData.endClassTime){
                "${classData.startClassTime}교시"
            }else{
                "${classData.startClassTime}교시 - ${classData.endClassTime}교시"
            },
            fontSize = 10.sp
        )


        Text(
            text = convertDayOfWeek(classData.dayOfWeek),
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
    var setShowClassDetailDialog by remember { mutableStateOf(false) }
    var setShowClassDataModifyDialog by remember { mutableStateOf(false) }

    val classData=ClassData(
        "컴퓨터수학2",
        "정보관 201호",
        1,
        1,
        2,
        3,
        Color(0xFFF58284)
    )
    MaterialTheme {
//        ClassDetail(
//            classData,
//            setShowClassDetailDialog = {setShowClassDetailDialog=it},
//            setShowClassDataModifyDialog = {setShowClassDataModifyDialog=it})

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun InsertClassDataPreview() {
    var setShowClassDetailDialog by remember { mutableStateOf(false) }
    var setShowClassDataModifyDialog by remember { mutableStateOf(false) }
    var setShowInsertClassDataDialog by remember { mutableStateOf(false) }

    MaterialTheme {
        InsertClassData(
//            setShowInsertClassDataDialog = {setShowInsertClassDataDialog=it},
            timeTableViewModel = TimeTableViewModel()
        )

    }
}


@Composable
fun TimeTableComponent(
    classDataList: List<ClassData>,
    modifier: Modifier = Modifier,
    setShowClassDetailDialog: () -> Unit,
    setShowClassDataModifyDialog: () -> Unit,
    setSelectClassData: (ClassData) -> Unit,
    classContent: @Composable (classData: ClassData) -> Unit = {
        OneClassCellDetailComponent(
            classData = it,
            modifier = Modifier,
            setShowClassDetailDialog = setShowClassDetailDialog,
            setShowClassDataModifyDialog = setShowClassDataModifyDialog,
            setSelectClassData=setSelectClassData
        )
    },
    minClassTime: Int = classDataList.minByOrNull(ClassData::startClassTime)?.startClassTime ?: 0,
    maxClassTime: Int = classDataList.maxByOrNull(ClassData::endClassTime)?.endClassTime ?: 7
) {
    val classTimeHeight = 60.dp
    var sidebarWidth by remember { mutableIntStateOf(0) }

    Column(modifier = modifier
        .padding(5.dp)
        .background(Color.White, shape = RoundedCornerShape(10.dp))
        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp)),


        ) {
        DayOfWeekHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = with(LocalDensity.current) { sidebarWidth.toDp() })
        )

        Row(modifier = Modifier
            .padding(bottom=10.dp,end=10.dp)
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
                modifier = Modifier.fillMaxWidth(),
                setShowClassDetailDialog={

                },
                setShowClassDataModifyDialog = {

                }
            )
        }
    }
}
@Composable
fun ClassDetail(
    classData:ClassData,
    setShowClassDetailDialog: () -> Unit,
    setShowClassDataModifyDialog: () -> Unit
){

    Dialog(onDismissRequest = { setShowClassDetailDialog() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),

                    ) {


                        Text(
                            text = classData.className,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(

                            text = classData.teacherName,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = convertDayOfWeek(classData.dayOfWeek),
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = classData.teacherName,
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${classData.credit}학점",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text =if(classData.startClassTime==classData.endClassTime){
                                "${classData.startClassTime}교시"
                            }else{
                                "${classData.startClassTime}교시 - ${classData.endClassTime}교시"
                            },
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                        ) {
                        TextButton(
                            onClick = {
                                setShowClassDataModifyDialog()
                                setShowClassDetailDialog()
                            },
                            modifier = Modifier
                        ) {
                            Text(text = "삭제 하기")
                        }
                        TextButton(
                            onClick = {
                                setShowClassDataModifyDialog()
                                setShowClassDetailDialog()
                            },
                            modifier = Modifier
                        ) {
                            Text(text = "수정 하기")
                        }
                        TextButton(
                            onClick = {
                                setShowClassDetailDialog()
                            },
                            modifier = Modifier
                        ) {
                            Text(text = "확인")
                        }
                    }
                }
            }
        }

}

@Composable
fun ClassModify(
    classData:ClassData,
    setShowClassDataModifyDialog: () -> Unit
){

    Dialog(onDismissRequest = { setShowClassDataModifyDialog() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                Column(
                    modifier = Modifier.fillMaxWidth(),

                    ) {


                    Text(
                        text = classData.className,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(

                        text = classData.teacherName,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = convertDayOfWeek(classData.dayOfWeek),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = classData.teacherName,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${classData.credit}학점",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text =if(classData.startClassTime==classData.endClassTime){
                            "${classData.startClassTime}교시"
                        }else{
                            "${classData.startClassTime}교시 - ${classData.endClassTime}교시"
                        },
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            setShowClassDataModifyDialog()
                        },
                        modifier = Modifier
                    ) {
                        Text(text = "수정 확인")
                    }
                    TextButton(
                        onClick = {
                            setShowClassDataModifyDialog()
                        },
                        modifier = Modifier
                    ) {
                        Text(text = "취소")
                    }
                }
            }
        }
    }

}

val daysOfWeek = listOf("월", "화", "수", "목", "금")
val classTime=listOf("1", "2", "3", "4", "5", "6", "7")
val credit=listOf("1", "2", "3")
val checkBoxName= listOf("학점","요일","시작 교시","종료 교시","학년")

@Composable
fun InsertClassData(
    timeTableViewModel: TimeTableViewModel,

){

    Dialog(onDismissRequest = { timeTableViewModel.toggleInsertClassDataDialogState() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Background_Color2,

        ) {


            Column(modifier = Modifier.padding(5.dp)) {

                MainTextComponent(value = "수업 추가하기", modifier = Modifier)

                TextInputFieldComponent(
                    value="",
                    onValueChange = {},
                    labelValue = "수업 이름")


                TextInputFieldComponent(
                    value="",
                    onValueChange = {},
                    labelValue = "교사명")


                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TimeTableDownMenuComponent(
                        onValueChange = {timeTableViewModel.updateCredit(it.toInt())},
                        value = timeTableViewModel.credit.value.toString(),
                        dropDownMenuOption = timeTableViewModel.toggleCreditDropDownMenu.value,
                        menuItems = credit,
                        dropDownMenuName = checkBoxName[0],
                        toggleDropDownMenuOption = {timeTableViewModel.toggleCreditDropDownMenu()}
                    )
                    TimeTableDownMenuComponent(
                        onValueChange = {timeTableViewModel.updateDayOfWeek(it)},
                        value = timeTableViewModel.dayOfWeek.value,
                        dropDownMenuOption = timeTableViewModel.toggleDayOfWeekDropDownMenu.value,
                        menuItems = daysOfWeek,
                        dropDownMenuName = checkBoxName[1],
                        toggleDropDownMenuOption = {timeTableViewModel.toggleDayOfWeekDropDownMenu()}
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TimeTableDownMenuComponent(
                        onValueChange = {timeTableViewModel.updateStartClassTime(it.toInt())},
                        value = timeTableViewModel.startClassTime.value.toString(),
                        dropDownMenuOption = timeTableViewModel.toggleStartClassTimeDropDownMenu.value,
                        menuItems = classTime,
                        dropDownMenuName = checkBoxName[2],
                        toggleDropDownMenuOption = {timeTableViewModel.toggleStartClassTimeDropDownMenu()}
                    )
                    TimeTableDownMenuComponent(
                        onValueChange = {timeTableViewModel.updateEndClassTime(it.toInt())},
                        value = timeTableViewModel.endClassTime.value.toString(),
                        dropDownMenuOption = timeTableViewModel.toggleEndClassTimeDropDownMenu.value,
                        menuItems = classTime,
                        dropDownMenuName = checkBoxName[3],
                        toggleDropDownMenuOption = {timeTableViewModel.toggleEndClassTimeDropDownMenu()}
                    )
                }

                GradeDropDownMenuComponent(
                    onValueChange = {timeTableViewModel.updateGrade(it)},

                    labelValue = timeTableViewModel.grade.value,
                    dropDownMenuOption = timeTableViewModel.toggleGradeDropDownMenu.value,
                    placeholderValue = "",
                    toggleDropDownMenuOption = {timeTableViewModel.toggleGradeDropDownMenu()}
                )

                TextInputFieldComponent(
                    value="",
                    onValueChange = {},
                    labelValue = "학교")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            timeTableViewModel.toggleInsertClassDataDialogState()
                        },
                        modifier = Modifier
                    ) {
                        Text(text = "추가")
                    }
                    TextButton(
                        onClick = {
                            timeTableViewModel.toggleInsertClassDataDialogState()
                        },
                        modifier = Modifier
                    ) {
                        Text(text = "취소")
                    }
                }
            }
        }
    }

}


fun convertDayOfWeek(day:Int):String{
    return when(day){
        1->"월요일"
        2->"화요일"
        3->"수요일"
        4->"목요일"
        5->"금요일"
        else -> "미정"
    }
}

@Composable
fun BasicClass(
    classDataList: List<ClassData>,
    modifier: Modifier = Modifier,
    setShowClassDetailDialog: () -> Unit,
    setShowClassDataModifyDialog: () -> Unit,
    classContent: @Composable (classData: ClassData) -> Unit = {
        OneClassCellDetailComponent(
            classData = it,
            modifier = Modifier,
            setShowClassDetailDialog = setShowClassDetailDialog,
            setShowClassDataModifyDialog = setShowClassDataModifyDialog,
            setSelectClassData = {}
        )
    },
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
            .padding(2.dp)
    )
}

@Composable
fun DayOfWeekHeader(
    modifier: Modifier = Modifier,
    dayHeader: @Composable (dayOfWeek: String) -> Unit = { DayHeader(day = it) },
) {

    Row(modifier = modifier) {
        daysOfWeek.forEach { day ->
            Box(modifier = Modifier
                .weight(1f)
                ) {
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
                    .height(hourHeight)
                    .padding(5.dp)
                    ,
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
        text = time.toString(),
        modifier = Modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeTableDownMenuComponent(
    value: String="",
    onValueChange: (String) -> Unit,
    dropDownMenuOption: Boolean,
    toggleDropDownMenuOption: () -> Unit,
    menuItems: List<String>,
    dropDownMenuName:String,

) {
    Column {
        Text(
            text = dropDownMenuName,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            color = Color.DarkGray,
            textAlign = TextAlign.Start,
            modifier = Modifier

                .padding(start = 3.dp, bottom = 5.dp, top = 10.dp)
        )
        ExposedDropdownMenuBox(
            expanded = dropDownMenuOption,
            onExpandedChange = { toggleDropDownMenuOption() }
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                readOnly = true,
                textStyle = TextStyle.Default.copy(fontSize = 25.sp),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownMenuOption)
                },

                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                ),
                modifier = Modifier
                    .width(120.dp)
                    .height(60.dp)
                    .menuAnchor(),
                shape = RoundedCornerShape(15.dp),
            )

            ExposedDropdownMenu(
                expanded = dropDownMenuOption,
                onDismissRequest = { toggleDropDownMenuOption() }
            ) {
                menuItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            onValueChange(item)
                            toggleDropDownMenuOption()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleHeaderPreview() {
//    MaterialTheme {
//        BasicClass(
//            classDataList = sampleEvents,
//
//            hourHeight = 90.dp,
//        )
//    }
}

