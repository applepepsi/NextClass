package com.example.nextclass.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.ScheduleDateTimePickerView
import com.example.nextclass.appComponent.ScheduleTextInsertView
import com.example.nextclass.appComponent.SelectDateBottomSheet
import com.example.nextclass.appComponent.SelectTimeBottomSheet
import com.example.nextclass.appComponent.SingleScheduleView
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.appComponent.scheduleDataList
import com.example.nextclass.repository.ScheduleTestRepository
import com.example.nextclass.repository.TestRepository

import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.utils.TimeFormatter
import com.example.nextclass.utils.TokenManager
import com.example.nextclass.utils.UserInfoManager
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel




@Composable
fun ScheduleView(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    loginViewModel: LoginViewModel
    ) {


    val context = LocalContext.current
    Column {


        Row(
            modifier = Modifier

                .padding(top = 20.dp),

            ) {

            AppBarTextAndButtonComponent(
                value = "스케쥴",
                navController = navController,
                showLeftButton = false,
                showRightButton = true,
                navRoute = "insertScheduleView"
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = scheduleDataList
            ) { singleSchedule ->
                SingleScheduleView(
                    scheduleDetail = singleSchedule.scheduleDetail,
                    scheduleDate = TimeFormatter.formatTimeAndSplit(singleSchedule.scheduleDate)
                )
            }
        }


        if (scheduleViewModel.tokenCheckResult.value) {
            loginViewModel.logOut()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InsertScheduleView(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    loginViewModel: LoginViewModel,
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
    if(scheduleViewModel.tokenCheckResult.value){
        loginViewModel.logOut()
        UserInfoManager.clearUserInfo(context)
        TokenManager.clearToken(context)
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testLoginRepository = TestRepository()
    val testRepository = ScheduleTestRepository()
    val scheduleViewModel=ScheduleViewModel(testRepository)
    val loginViewModel=LoginViewModel(testLoginRepository)
    MaterialTheme {
        ScheduleView(navController, scheduleViewModel,loginViewModel)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun InsertScheduleViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = ScheduleTestRepository()
    val testLoginRepository = TestRepository()
    val scheduleViewModel=ScheduleViewModel(testRepository)
    val loginViewModel=LoginViewModel(testLoginRepository)

    MaterialTheme {
        InsertScheduleView(navController, scheduleViewModel,loginViewModel)
    }
}