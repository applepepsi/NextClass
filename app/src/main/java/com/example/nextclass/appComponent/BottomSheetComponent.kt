package com.example.nextclass.appComponent


import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.ClassScore
import com.example.nextclass.Data.SingleSemesterScore
import com.example.nextclass.R
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
    timeTableViewModel: TimeTableViewModel,
    navController: NavController
) {

    //최대 80퍼센트까지 확장가능
    Column(
        modifier = Modifier.fillMaxHeight(fraction = 0.8f)
    ) {



        Row(
            modifier=Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                append("평균 학점: ")
                withStyle(style = SpanStyle(
                    color = Pastel_Red,
                    fontWeight = FontWeight.Bold)) {
                    append(timeTableViewModel.timeTableScore.value.average_grade)
                }
            },
                fontSize = 15.sp
            )

            Text(
                text = buildAnnotatedString {
                append("취득 학점: ")
                withStyle(style = SpanStyle(
                    color = Pastel_Red,
                    fontWeight = FontWeight.Bold)) {
                    append(timeTableViewModel.timeTableScore.value.credit_sum.toString())
                }
                append("/${timeTableViewModel.timeTableScore.value.require_credit}")

            },
                fontSize = 15.sp
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(Pastel_Red)
                    .padding(start = 10.dp, end = 10.dp, top = 7.dp, bottom = 7.dp)
                    .clickable {
                        timeTableViewModel.toggleAddSemesterPopupState()
                    },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text="학기 추가하기",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        color = Color.White
                    ),
                    modifier = Modifier


                )
            }
        }



        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, bottom = 80.dp),



        ) {

            item{
                Spacer(modifier = Modifier.height(15.dp))
            }

            timeTableViewModel.timeTableScore.value.semester_list.forEachIndexed  { semesterIndex, semester ->

                item {
                    Text(
                        text = GetSemester.convertSemester(semester.semester),
                        modifier = Modifier.padding()
                    )
                    Log.d("semester", semester.toString())
                }
                item {
                    TableHeader(modifier = Modifier)
                }
                items(semester.data_list) { singleClassData ->

                    TableRow(
                        singleClassData,
                        timeTableViewModel=timeTableViewModel
                    )
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .background(Pastel_Red),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        SemesterScore(
                            score=semester.score
                        )
                        DeleteOrModifyScoreBoard(
                            index = semesterIndex,
                            semester = semester,
                            timeTableViewModel = timeTableViewModel,
                            navController = navController
                        )
                    }

                }
                item{
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }
    }
}

@Composable
fun DeleteOrModifyScoreBoard(
    index:Int,
    semester:SingleSemesterScore,
    timeTableViewModel:TimeTableViewModel,
    navController:NavController
) {
    Row(

    ){

        Text(
            modifier = Modifier
                .clickable {
                    timeTableViewModel.deleteSemesterScore(index)
                }
                .align(Alignment.CenterVertically)
                .padding(end = 10.dp),
            text="성적 제거하기",
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            ),
        )

        Text(
            modifier = Modifier
                .clickable {
                    timeTableViewModel.setSelectScoreList(semester)

                    navController.navigate("modifyScoreView") {
                        launchSingleTop = true
                    }
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
fun TableHeader(
    modifier: Modifier
) {

    val column1Weight = .1f
    val column2Weight = .2f
    val column3Weight = .15f

    Row(
        modifier

            .fillMaxWidth()

            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(Pastel_Red),
        horizontalArrangement = Arrangement.Center,

    ) {
        TableHeaderCell(
            text = "과목명",
            weight = column2Weight,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                ),
            )


        TableHeaderCell(text = "학점", weight = column1Weight,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(text = "등급", weight = column1Weight,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(text = "교과구분", weight = column3Weight,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(
            text = "성취도",
            weight = column3Weight,
            style = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            ),
        )

        TableHeaderCell(text = "원점수", weight = column3Weight,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(text = "과목평균", weight = column3Weight,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(text = "표준편차", weight = column3Weight,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
    }
}

@Composable
fun TableRow(
    data: ClassScore,
    dropDownIconVisible: Boolean=false,
    timeTableViewModel: TimeTableViewModel,
    index:Int=0
) {

    val column1Weight = .1f
    val column2Weight = .2f
    val column3Weight = .15f
    Log.d("보낸 인덱스", index.toString())



    Row(
        Modifier
            .fillMaxWidth()
            .background(Background_Color2),

        horizontalArrangement = Arrangement.Center,

    ) {



        TableCell(
            text = data.title,
            weight = column2Weight,
            onValueChange = { timeTableViewModel.updateScoreTitle(index,it) },
        )

        ScoreDropDownMenu(
            value = data.credit.toString(),
            onValueChange = {timeTableViewModel.updateScoreCredit(index,it.toInt())},
            dropDownMenuOption = timeTableViewModel.scoreCreditDropDownMenuState[index],
            toggleDropDownMenuOption = { timeTableViewModel.toggleScoreCreditDropDownMenuState(index) },
            menuItems = scoreCredit,
            weight = column1Weight,
            dropDownIconVisible = dropDownIconVisible,
        )

        ScoreDropDownMenu(
            value =  data.grade.toString(),
            onValueChange = {timeTableViewModel.updateScoreGrade(index,it.toInt())},
            dropDownMenuOption = timeTableViewModel.scoreGradeDropDownMenuState[index],
            toggleDropDownMenuOption = { timeTableViewModel.toggleScoreGradeDownMenuState(index) },
            menuItems = grade,
            weight = column1Weight,
            dropDownIconVisible = dropDownIconVisible,
        )

        ScoreDropDownMenu(
            value = data.category,
            onValueChange = {timeTableViewModel.updateScoreCategory(index,it)},
            dropDownMenuOption = timeTableViewModel.scoreCategoryDropDownMenuState[index],
            toggleDropDownMenuOption = { timeTableViewModel.toggleScoreCategoryDropDownState(index) },
            menuItems = category,
            weight = column3Weight,
            dropDownIconVisible = dropDownIconVisible,
        )

        ScoreDropDownMenu(
            value = data.achievement,
            onValueChange = {timeTableViewModel.updateScoreAchievement(index,it)},
            dropDownMenuOption = timeTableViewModel.scoreAchievementDropDownMenuState[index],
            toggleDropDownMenuOption = { timeTableViewModel.toggleScoreAchievementDropDownState(index) },
            menuItems = achievement,
            weight = column3Weight,
            dropDownIconVisible = dropDownIconVisible,
        )
        if(data.category=="공통"){
            TableCell(text = "", weight = column3Weight,readOnly = true,)
            TableCell(text = "", weight = column3Weight,readOnly = true,)
            TableCell(text = "", weight = column3Weight,readOnly = true,)
        }else{
            TableCell(text = data.student_score.toString(), weight = column3Weight,readOnly = false, onValueChange = {timeTableViewModel.updateStudentScore(index,it)},)
            TableCell(text = data.average_score.toString(), weight = column3Weight,readOnly = false, onValueChange = {timeTableViewModel.updateStudentAverageScore(index,it)},)
            TableCell(text = data.standard_deviation.toString(), weight = column3Weight,readOnly = false, onValueChange = {timeTableViewModel.updateStandardDeviation(index,it)},)
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
    readOnly:Boolean=false,
    onValueChange:(String)->Unit={},
) {

    Box(
        modifier = Modifier
            .border(0.6.dp, Color.LightGray)
            .weight(weight)
            .heightIn(min = 30.dp)
            .padding(start = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 10.sp
            ),
            readOnly=readOnly,
            modifier = Modifier.fillMaxWidth(),

        )
    }
}




val scoreCredit=listOf("1", "2", "3")
val achievement= listOf("A","B","C","D","E")
val grade= listOf("1","2","3","4","5","6","7","8","9")
val category= listOf("공통","선택")
@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.ScoreDropDownMenu(
    value: String="",
    onValueChange: (String) -> Unit,
    dropDownMenuOption: Boolean,
    toggleDropDownMenuOption: () -> Unit,
    dropDownIconVisible:Boolean=false,
    menuItems: List<String>,
    weight:Float,

    ) {

    val interactionSource = remember { MutableInteractionSource() }
//    Log.d("토클상태", dropDownMenuOption.toString())
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
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    readOnly = true,

                    modifier = Modifier
                        .then(if (dropDownIconVisible) Modifier.menuAnchor() else Modifier)
                        .weight(1f),
                    textStyle = TextStyle(
                        fontSize = 10.sp
                    ),

                )

                if(dropDownIconVisible){

                    CustomTrailingIcon(expanded=dropDownMenuOption,size=14.dp)
                }
            }

            ExposedDropdownMenu(
                expanded = dropDownMenuOption,
                onDismissRequest = { toggleDropDownMenuOption() },
                modifier = Modifier.width(80.dp)
            ) {
                menuItems.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                            )
                        },
                        onClick = {
                            onValueChange(item)
                            toggleDropDownMenuOption()
                        },
                        modifier = Modifier

                    )
                }
            }
        }
    }
}
@Composable
fun CustomTrailingIcon(expanded: Boolean,size: Dp) {
    Icon(
        Icons.Filled.ArrowDropDown,
        null,
        modifier= Modifier
            .rotate(if (expanded) 180f else 0f)
            .size(size)
            .padding(0.dp)
    )
}
@Composable
fun ModifyScoreComponent(
    timeTableViewModel:TimeTableViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),

    ) {

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp, end = 10.dp, start = 5.dp),
            verticalArrangement = Arrangement.Center
            ) {


                item {
                    Text(
                        text = GetSemester.convertSemester(timeTableViewModel.singleSemesterScore.value.semester),
                        modifier = Modifier.padding(start=11.dp)
                    )

                }
                item {
                    TableHeader(modifier = Modifier.padding(start=11.dp))
                }

                itemsIndexed(timeTableViewModel.singleSemesterScore.value.data_list) { index,singleClassData ->
                    Log.d("인덱스", index.toString())

                    Row(){
                        Icon(
                            modifier = Modifier
                                .size(11.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    timeTableViewModel.deleteScoreRow(index)
                                },
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = Color.Black,
                        )

                        Spacer(Modifier.width(2.dp))

                        TableRow(
                            singleClassData,
                            dropDownIconVisible = true,
                            timeTableViewModel=timeTableViewModel,
                            index=index
                        )

                    }

                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 11.dp)
                            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .background(Pastel_Red),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        SemesterScore(
                            score=timeTableViewModel.singleSemesterScore.value.score
                        )

                        //todo수업추가하기 테스트 해야함
                        Text(
                            modifier = Modifier
                                .clickable {
                                    timeTableViewModel.addScoreRow()
                                    timeTableViewModel.initializeScoreDropDownStates(
                                        timeTableViewModel.singleSemesterScore.value.data_list.size
                                    )
                                }
                                .align(Alignment.CenterVertically)
                                .padding(end = 10.dp),
                            text="수업 추가하기",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                                color = Color.Black
                            ),
                        )
                    }
                }



            item{
                Spacer(Modifier.height(40.dp))
                
                TextInputHelpFieldComponent(
                    errorMessage = timeTableViewModel.modifyScoreErrorMessage.value.asString(LocalContext.current),
                    isError = timeTableViewModel.modifyScoreErrorState.value)
                
                InputButtonComponent(
                    value = "수정 완료",
                    onClick = {
                        timeTableViewModel.modifyScore()

                        //todo 뒤로가기 테스트 해봐야함

                        navController.popBackStack()

                    },
                    modifier = Modifier.padding(start=15.dp,end=15.dp))
            }
        }
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSemesterPopupComponent(
    timeTableViewModel: TimeTableViewModel,

) {
    val year = remember { (2010..2030).map { it.toString() } }
    val semester=remember { (1..2).map { it.toString() } }
    val yearPickerState = rememberPickerState()
    val semesterPickerState = rememberPickerState()

    Dialog(
        onDismissRequest = { timeTableViewModel.toggleAddSemesterPopupState() }) {
        Surface(
            modifier = Modifier.height(370.dp),
            shape = RoundedCornerShape(16.dp),
            color = Background_Color2,

            ) {
            Column(
                verticalArrangement = Arrangement.Center
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Picker(
                        state = yearPickerState,
                        items = year,
                        visibleItemsCount = 3,
                        modifier = Modifier.weight(0.5f),
                        textModifier = Modifier.padding(8.dp),
                        textStyle = TextStyle(fontSize = 32.sp),
                        startIndex = 2024,
                        separateWord = " 년"
                    )
                    Picker(
                        state = semesterPickerState,
                        items = semester,
                        visibleItemsCount = 3,
                        modifier = Modifier.weight(0.5f),
                        textModifier = Modifier.padding(8.dp),
                        textStyle = TextStyle(fontSize = 32.sp),

                        separateWord = " 학기"
                    )
                }

                Spacer(Modifier.height(15.dp))

                TextInputHelpFieldComponent(

                    errorMessage = timeTableViewModel.addSemesterErrorMessage.value.asString(LocalContext.current),
                    isError = timeTableViewModel.addSemesterErrorState.value,

                    )

                InputButtonComponent(
                    value = "추가 완료",
                    onClick = {
                        timeTableViewModel.postNewScoreTable(year=yearPickerState.selectedItem,semester=semesterPickerState.selectedItem)
                        timeTableViewModel.toggleAddSemesterPopupState()
                    },
                    modifier = Modifier.padding(start=15.dp,end=15.dp,top=15.dp))

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
    val navController= rememberNavController()
    NextClassTheme {

        AccreditationCalculationComponent(timeTableViewModel, navController = navController)
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
    val navController= rememberNavController()
    NextClassTheme {

        ModifyScoreComponent(timeTableViewModel,navController)
    }
}

@Preview(showBackground = true)
@Composable
fun AddSemesterPreview() {

    val mainNavController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val timeTableTestRepository=TimeTableTestRepo()
    val timeTableViewModel = TimeTableViewModel(timeTableTestRepository)
    NextClassTheme {

        AddSemesterPopupComponent(timeTableViewModel)
    }
}