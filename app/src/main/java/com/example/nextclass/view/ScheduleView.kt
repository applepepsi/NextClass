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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.ScheduleDateTimePickerView
import com.example.nextclass.appComponent.ScheduleTextInsertView
import com.example.nextclass.appComponent.SelectDateBottomSheet
import com.example.nextclass.appComponent.SelectTimeBottomSheet
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.repository.ScheduleTestRepository

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
        modifier = Modifier

            .fillMaxSize(),

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

        Row(
            modifier = Modifier
                .padding(top = 20.dp),

        ) {
            AppBarTextAndButtonComponent(
                value = "스케쥴 추가 하기",
                navController = navController,
                showLeftButton = true,
                showRightButton = false,
                navRoute = "insertScheduleView"
            )
        }



        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
        ) {
            ScheduleDateTimePickerView(
                selectDate = scheduleViewModel.timeData.value.selectDate,
                selectTime = scheduleViewModel.timeData.value.selectTime,
                onDateClick = { scheduleViewModel.toggleDatePickerState() },
                onTimeClick = { scheduleViewModel.toggleTimePickerState() }
            )

            Spacer(modifier = Modifier.padding(top = 15.dp))

            Surface(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    modifier = Modifier
                        .background(Background_Color2),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.padding(top = 50.dp))

                    ScheduleTextInsertView(
                        text = scheduleViewModel.scheduleData.value.scheduleDetail,
                        onValueChange = {
                            scheduleViewModel.updateScheduleDetail(it)
                        },
                        textCount = scheduleViewModel.textCounter.value
                    )


                    Spacer(modifier = Modifier.padding(top = 40.dp))


                    TextInputHelpFieldComponent(
                        errorMessage = scheduleViewModel.scheduleErrorMessage.value.asString(LocalContext.current),
                        isError = scheduleViewModel.scheduleErrorState.value,
                    )

                    Spacer(modifier = Modifier.padding(top = 5.dp))

                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {


                        InputButtonComponent(
                            value = "추가 하기",
                            onClick = { scheduleViewModel.postScheduleDate() },
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.padding(top = 40.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
//    val testRepository = TestRepository()
    val testRepository = ScheduleTestRepository()
    val scheduleViewModel=ScheduleViewModel(testRepository)
    MaterialTheme {
        ScheduleView(navController, scheduleViewModel)
    }
}


@Preview(showBackground = true)
@Composable
fun InsertScheduleViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = ScheduleTestRepository()
    val scheduleViewModel=ScheduleViewModel(testRepository)

    MaterialTheme {
        InsertScheduleView(navController, scheduleViewModel)
    }
}