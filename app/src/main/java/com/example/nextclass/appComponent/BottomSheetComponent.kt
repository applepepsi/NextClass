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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.TimeTableViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun AccreditationCalculationComponent() {

    val tableData = (1..10).mapIndexed { index, item ->
        index.toString()
    }

    val tables = listOf(
        "학기1" to tableData,
        "학기2" to tableData,
        "학기3" to tableData
    )

    //최대 80퍼센트까지 확장가능
    Column(
        modifier = Modifier.fillMaxHeight(fraction = 0.8f)
    ) {
        LazyColumn(Modifier.fillMaxSize().padding(16.dp)) {
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
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun TableHeader() {
    //각각30퍼센트씩
    val column1Weight = .3f
    val column2Weight = .3f
    val column3Weight = .3f

    Row(Modifier.padding().background(Color.LightGray)) {
        TableCell(text = "과목명", weight = column1Weight)
        TableCell(text = "학점", weight = column2Weight)
        TableCell(text = "성적", weight = column3Weight)
    }
}

@Composable
fun TableRow(data: String) {

    val column1Weight = .3f
    val column2Weight = .3f
    val column3Weight = .3f

    Row(Modifier.fillMaxWidth()) {
        TableCell(text = data, weight = column1Weight)
        TableCell(text = data, weight = column2Weight)
        TableCell(text = data, weight = column3Weight)
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
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

