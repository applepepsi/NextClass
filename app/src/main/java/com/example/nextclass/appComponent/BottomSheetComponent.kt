package com.example.nextclass.appComponent


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi

import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.ClassScore
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.repository.testRepo.TimeTableTestRepo
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.utils.GetSemester
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.TimeTableViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun AccreditationCalculationComponent(
    timeTableViewModel: TimeTableViewModel
) {

//    val tableData1 = listOf(
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//    )
//
//    val tableData2 = listOf(
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//    )
//
//    val tableData3 = listOf(
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//        listOf("과목1", "1", "2","공통","A","92.0","86,26","26.0"),
//    )
//
//    val tables = listOf(
//        "학기1" to tableData1,
//        "학기2" to tableData2,
//        "학기3" to tableData3
//    )

    //최대 80퍼센트까지 확장가능
    Column(
        modifier = Modifier.fillMaxHeight(fraction = 0.8f)
    ) {

        Row(
            modifier=Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = buildAnnotatedString {
                append("평균 학점: ")
                withStyle(style = SpanStyle(
                    color = Pastel_Red,
                    fontWeight = FontWeight.Bold)) {
                    append(timeTableViewModel.timeTableScore.value.average_grade)
                }

            })

            Text(text = buildAnnotatedString {
                append("취득 학점: ")
                withStyle(style = SpanStyle(
                    color = Pastel_Red,
                    fontWeight = FontWeight.Bold)) {
                    append(timeTableViewModel.timeTableScore.value.credit_sum.toString())
                }
                append("/${timeTableViewModel.timeTableScore.value.require_credit}")

            })
        }

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, bottom = 80.dp),

        ) {
            timeTableViewModel.timeTableScore.value.semesterList.forEach { semester ->

                item {
                    Text(
                        text = GetSemester.convertSemester(semester.semester),
                        modifier = Modifier.padding()
                    )
                    Log.d("semester", semester.toString())
                }
                item {
                    TableHeader()
                }
                items(semester.dataList) { singleClassData ->

                    TableRow(
                        singleClassData,
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .background(Pastel_Red),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        SemesterScore(
                            score=semester.score
                        )

                        Text(
                            modifier = Modifier
                                .clickable {

                                }
                                .align(Alignment.CenterVertically)
                                .padding(end = 10.dp),
                            text="성적 수정하기",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                                color = Color.Black
                            ),
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun SemesterScore(
    score:String="0"
) {
    Row(
        Modifier

    ){
        Text(
            text= buildAnnotatedString {
                append("평균 학점: ")
                withStyle(style = SpanStyle(
//                                    color = Pastel_Red,
                    fontWeight = FontWeight.Bold)) {
                    append(score)
                }
            },
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier
                .padding(5.dp)
                .padding(start = 5.dp)
        )
        Text(
            text= buildAnnotatedString {
                append("취득 학점: ")
                withStyle(style = SpanStyle(
//                                    color = Color.White,
                    fontWeight = FontWeight.Bold)) {
                    append("15")
                }

            },
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun TableHeader() {

    val column1Weight = .2f
    val column2Weight = .1f
    val column3Weight = .2f

    Row(
        Modifier

            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(Pastel_Red),
        horizontalArrangement = Arrangement.Center,

    ) {
        TableHeaderCell(
            text = "과목명",
            weight = column1Weight,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                ),
            )


        TableHeaderCell(text = "학점", weight = column2Weight,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(text = "학년", weight = column2Weight,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(text = "종류", weight = column3Weight,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(
            text = "등급",
            weight = column2Weight,
            style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            ),
        )

        TableHeaderCell(text = "원점수", weight = column3Weight,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(text = "과목평균", weight = column3Weight,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(text = "표준편차", weight = column3Weight,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
    }
}

@Composable
fun TableRow(data: ClassScore) {

    val column1Weight = .2f
    val column2Weight = .1f
    val column3Weight = .2f

    Row(
        Modifier
            .fillMaxWidth()
            .background(Background_Color2),
        
        horizontalArrangement = Arrangement.Center,

    ) {

        TableCell(text = data.title, weight = column1Weight)
//        TableCell(text = data.credit.toString(), weight = column2Weight)
        ScoreDropDownMenu(
            value = data.credit.toString(),
            onValueChange = {},
            dropDownMenuOption = false,
            toggleDropDownMenuOption = { /*TODO*/ },
            menuItems = category,
            weight = column2Weight
        )
//        TableCell(text = data.grade.toString(), weight = column2Weight)
        ScoreDropDownMenu(
            value =  data.grade.toString(),
            onValueChange = {},
            dropDownMenuOption = false,
            toggleDropDownMenuOption = { /*TODO*/ },
            menuItems = category,
            weight = column2Weight
        )
//        TableCell(text = data.category, weight = column3Weight)
        ScoreDropDownMenu(
            value = data.category,
            onValueChange = {},
            dropDownMenuOption = false,
            toggleDropDownMenuOption = { /*TODO*/ },
            menuItems = category,
            weight = column3Weight
        )
//        TableCell(text = data.achievement, weight = column2Weight)
        ScoreDropDownMenu(
            value = data.achievement,
            onValueChange = {},
            dropDownMenuOption = false,
            toggleDropDownMenuOption = { /*TODO*/ },
            menuItems = category,
            weight = column2Weight
        )
        if(data.category=="공통"){
            TableCell(text = "", weight = column3Weight,readOnly = true)
            TableCell(text = "", weight = column3Weight,readOnly = true)
            TableCell(text = "", weight = column3Weight,readOnly = true)
        }else{
            TableCell(text = data.student_score.toString(), weight = column3Weight,readOnly = true)
            TableCell(text = data.average_socre.toString(), weight = column3Weight,readOnly = true)
            TableCell(text = data.standard_deviation.toString(), weight = column3Weight,readOnly = true)
        }

    }
}

@Composable
fun RowScope.TableHeaderCell(
    text: String,
    weight: Float,
    style: TextStyle,
) {

    Text(
        text = text,
        modifier= Modifier
            .fillMaxWidth()
            .border(0.6.dp, Color.LightGray)
            .weight(weight)

            .padding(start = 4.dp, top = 5.dp, bottom = 5.dp),

        style=style,

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    readOnly:Boolean=false
) {
    var currentText by remember { mutableStateOf(text) }
    Box(
        modifier = Modifier
            .border(0.6.dp, Color.LightGray)
            .weight(weight)
            .heightIn(min = 30.dp)
            .padding(start = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = currentText,
            onValueChange = { currentText = it },
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 13.sp
            ),
            readOnly=readOnly,
            modifier = Modifier.fillMaxWidth(),

        )
    }
}


val scoreCredit=listOf(1, 2, 3)
val achievement= listOf("A","B","C")
val grade= listOf(1,2,3)
val category= listOf("공통","선택")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.ScoreDropDownMenu(
    value: String="",
    onValueChange: (String) -> Unit,
    dropDownMenuOption: Boolean,
    toggleDropDownMenuOption: () -> Unit,
    dropDownIconVisible:Boolean=false,
    menuItems: List<String>,
    weight:Float
    ) {

    Row(
        modifier = Modifier
            .border(0.6.dp, Color.LightGray)
            .heightIn(min = 30.dp)
            .padding(start = 4.dp)
            .weight(weight),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ExposedDropdownMenuBox(
            expanded = dropDownMenuOption,
            onExpandedChange = { toggleDropDownMenuOption() },

        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // 텍스트 필드와 아이콘 간의 간격 조정
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .weight(1f), // 텍스트 필드가 가능한 공간을 차지하도록 설정
                    textStyle = TextStyle(
                        fontSize = 13.sp
                    )
                )
                if(dropDownIconVisible){
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { toggleDropDownMenuOption() }
                            .size(12.dp)
                    )
                }
            }
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

@Composable
fun ModifyScore(
    timeTableViewModel:TimeTableViewModel
) {
    Column(
        modifier = Modifier.fillMaxHeight(fraction = 0.8f)
    ) {


        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, bottom = 80.dp),

            ) {


                item {
                    Text(
                        text = GetSemester.convertSemester(timeTableViewModel.singleSemesterScore.value.semester),
                        modifier = Modifier.padding()
                    )

                }
                item {
                    TableHeader()
                }
                items(timeTableViewModel.singleSemesterScore.value.dataList) { singleClassData ->

                    TableRow(
                        singleClassData,
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .background(Pastel_Red),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        SemesterScore(
                            score=timeTableViewModel.singleSemesterScore.value.score
                        )

                        Text(
                            modifier = Modifier
                                .clickable {

                                }
                                .align(Alignment.CenterVertically)
                                .padding(end = 10.dp),
                            text="성적 수정하기",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                                color = Color.Black
                            ),
                        )
                    }

                }
            }
        }


}


@Preview(showBackground = true)
@Composable
fun ZipperPreview() {

    val mainNavController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val timeTableTestRepository=TimeTableTestRepo()
    val timeTableViewModel = TimeTableViewModel(timeTableTestRepository)
    NextClassTheme {

        AccreditationCalculationComponent(timeTableViewModel)
    }
}


@Preview(showBackground = true)
@Composable
fun ModifyScorePreview() {

    val mainNavController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val timeTableTestRepository=TimeTableTestRepo()
    val timeTableViewModel = TimeTableViewModel(timeTableTestRepository)
    NextClassTheme {

        ModifyScore(timeTableViewModel)
    }
}

