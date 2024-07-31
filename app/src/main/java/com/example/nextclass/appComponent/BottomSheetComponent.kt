package com.example.nextclass.appComponent


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.viewmodel.TimeTableViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun AccreditationCalculationComponent() {

    val tableData = listOf(
        listOf("과목1", "1", "A"),
        listOf("과목2", "2", "B"),
        listOf("과목3", "3", "C"),
        listOf("과목4", "4", "각")
    )

    val tables = listOf(
        "학기1" to tableData,
        "학기2" to tableData,
        "학기3" to tableData
    )

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
                    append("15")
                }
                append("/150")
            })

            Text(text = buildAnnotatedString {
                append("취득 학점: ")
                withStyle(style = SpanStyle(
                    color = Pastel_Red,
                    fontWeight = FontWeight.Bold)) {
                    append("15")
                }
                append("/150")

            })
        }

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(16.dp)) {
            tables.forEach { (title, data) ->


                item {
                    Text(
                        text = title,
                        modifier = Modifier.padding()
                    )
                }
                item {
                    TableHeader()
                }
                items(data) { itemData ->
                    TableRow(itemData)
                }
                item {
                    SemesterScore()
                }
            }
        }
    }
}

@Composable
fun SemesterScore() {
    Row(
        Modifier

            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .background(Pastel_Red),
    ){
        Text(
            text= buildAnnotatedString {
                append("평균 학점: ")
                withStyle(style = SpanStyle(
//                                    color = Pastel_Red,
                    fontWeight = FontWeight.Bold)) {
                    append("15")
                }
                append("/4.5")

            },
            style = TextStyle(
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier
                .padding(5.dp)
                .padding(start=5.dp)
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
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun TableHeader() {
    //각각30퍼센트씩
    val column1Weight = .6f
    val column2Weight = .2f
    val column3Weight = .2f

    Row(
        Modifier

            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .background(Pastel_Red)

    ) {
        TableHeaderCell(
            text = "과목명",
            weight = column1Weight,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                ),
            )
        TableHeaderCell(text = "학점", weight = column2Weight,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        TableHeaderCell(text = "성적", weight = column3Weight,
            style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            ),
        )
    }
}

@Composable
fun TableRow(data: List<String>) {

    val column1Weight = .6f
    val column2Weight = .2f
    val column3Weight = .2f

    Row(Modifier
        .fillMaxWidth()
        .background(Background_Color2),

    ) {
        TableCell(text = data[0], weight = column1Weight)
        TableCell(text = data[1], weight = column2Weight)
        TableCell(text = data[2], weight = column3Weight)
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
        modifier=Modifier
            .border(0.6.dp, Color.LightGray)
            .weight(weight)

            .padding(start=10.dp,top=5.dp,bottom=5.dp),
        style=style
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,

) {
    var currentText by remember { mutableStateOf(text) }
    Box(
        modifier = Modifier
            .border(0.6.dp, Color.LightGray)
            .weight(weight)
            .heightIn(min = 40.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = currentText,
            onValueChange = { currentText = it },
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ZipperPreview() {

//    val mainNavController= rememberNavController()
//    val testRepository = TestRepository()
//    val loginViewModel = LoginViewModel(testRepository)
//    val timeTableViewModel = TimeTableViewModel()
    NextClassTheme {

        AccreditationCalculationComponent()
    }
}

