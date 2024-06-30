package com.example.nextclass.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.ScheduleDateTimePickerView
import com.example.nextclass.appComponent.ScheduleTextInsertView
import com.example.nextclass.appComponent.SelectDateBottomSheet
import com.example.nextclass.appComponent.SelectTimeBottomSheet

import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Charleston_Green
import com.example.nextclass.ui.theme.Feldgrau
import com.example.nextclass.utils.TimeFormatter
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel

@Composable
fun ScheduleView(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,

    ) {


    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

    ){

            AppBarTextAndButtonComponent(
                value = "스케쥴",
                navController=navController,
                showLeftButton = false,
                showRightButton = true,
                navRoute = "insertScheduleView"
            )

        }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .height(350.dp)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Box(

                modifier = Modifier
                    .background(Background_Color2)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),

                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    MainTextComponent(
                        value = "스케쥴",
                        modifier= Modifier
                            .padding(top=20.dp)
                    )

                }
            }
        }
    }
}

@Composable
fun InsertScheduleView(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        if (scheduleViewModel.datePickerState.value) {
            SelectDateBottomSheet(
                onDismiss = { scheduleViewModel.toggleDatePickerState() },
                updateSelectData = { scheduleViewModel.updateScheduleDate(it) }
            )
        }

        if (scheduleViewModel.timePickerState.value) {
            SelectTimeBottomSheet(
                onDismiss = { scheduleViewModel.toggleTimePickerState() },
                updateSelectTime = { scheduleViewModel.updateScheduleTime(it) }
            )
        }

        Row(modifier = Modifier.padding(top = 20.dp)) {
            AppBarTextAndButtonComponent(
                value = "스케쥴 추가 하기",
                navController = navController,
                showLeftButton = true,
                showRightButton = false,
                navRoute = "insertScheduleView"
            )
        }

        Spacer(modifier = Modifier.padding(top=70.dp))


        ScheduleDateTimePickerView(
            selectDate = scheduleViewModel.timeData.value.selectDate,
            selectTime = scheduleViewModel.timeData.value.selectTime,
            onDateClick = { scheduleViewModel.toggleDatePickerState() },
            onTimeClick = { scheduleViewModel.toggleTimePickerState() }
        )
        Spacer(modifier = Modifier.padding(top=70.dp))

        ScheduleTextInsertView()
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val scheduleViewModel:ScheduleViewModel= hiltViewModel()
    MaterialTheme {
        ScheduleView(navController, scheduleViewModel)
    }
}


@Preview(showBackground = true)
@Composable
fun InsertScheduleViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val scheduleViewModel:ScheduleViewModel= hiltViewModel()

    MaterialTheme {
        InsertScheduleView(navController, scheduleViewModel)
    }
}