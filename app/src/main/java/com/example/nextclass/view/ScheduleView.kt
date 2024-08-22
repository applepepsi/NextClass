package com.example.nextclass.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.GridScheduleItem
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.LazyScheduleItem
import com.example.nextclass.appComponent.ScheduleDateTimePickerView
import com.example.nextclass.appComponent.ScheduleTextInsertView
import com.example.nextclass.appComponent.SelectDateBottomSheet
import com.example.nextclass.appComponent.SelectTimeBottomSheet
import com.example.nextclass.appComponent.SortBottomSheetComponent
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.appComponent.scheduleDataList
import com.example.nextclass.repository.testRepo.ScheduleTestRepository
import com.example.nextclass.repository.testRepo.TestRepository

import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.utils.TokenManager
import com.example.nextclass.utils.UserInfoManager
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleView(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    loginViewModel: LoginViewModel
    ) {


    LaunchedEffect(Unit) {
        scheduleViewModel.resetScheduleData()
        scheduleViewModel.getScheduleData()
    }



    val context = LocalContext.current

//    val itemDeploymentIcon=if(!scheduleViewModel.toggleLazyType.value){
//        ImageVector.vectorResource(R.drawable.lazy_colum_icon)
//    }else{
//        ImageVector.vectorResource(R.drawable.lazy_grid_icon)
//    }

    val sortIcon=ImageVector.vectorResource(R.drawable.sorting_icon)

    //todo 정렬 타입에 따라 데이터 재배치 코드 추가
    SortBottomSheetComponent(
        bottomSheetShowState = scheduleViewModel.toggleShowSortBottomSheet.value,
        setSortType = { selectSortType->
            scheduleViewModel.setSortTypeScheduleDataList(selectSortType)
        },
        toggleBottomSheetState = {
            scheduleViewModel.toggleSortBottomSheet()
        }
    )

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
                    navRoute = "insertScheduleView",
                    buttonText = "추가하기"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        //배치 제어 버튼
                        scheduleViewModel.toggleSortBottomSheet()
                    },
                    modifier = Modifier
                        .size(30.dp)
                ) {
                    Icon(
                        modifier = Modifier

                            .size(25.dp),
                        imageVector = sortIcon,
                        contentDescription = "",
                        tint = Color.Black,
                    )
                }
//                Spacer(modifier = Modifier.width(10.dp))
//                IconButton(
//                    onClick = {
//                        //배치 제어 버튼
//                        scheduleViewModel.changeLazyViewType()
//                    },
//                    modifier = Modifier
//                        .size(30.dp)
//                ) {
//                    Icon(
//                        modifier = Modifier
//
//                            .size(30.dp),
//                        imageVector = itemDeploymentIcon,
//                        contentDescription = "",
//                        tint = Color.Black,
//                    )
//                }
            }

            Spacer(modifier = Modifier.height(20.dp))


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            scheduleViewModel.groupByDateScheduleData.value.forEach { (date, schedules) ->


                //그리드뷰의 존재 이유가 딱히 없는것 같아 보류
                if(scheduleViewModel.toggleLazyType.value){
                    item {
                        Log.d("date", date.toString())
                        GridScheduleItem(
                            date = date.toString(),
                            schedules = schedules,
                            scheduleViewModel = scheduleViewModel,
                            navController = navController
                        )
                    }
                }else{
                    item {
                        Log.d("date", date.toString())
                        LazyScheduleItem(
                            date = date.toString(),
                            schedules = schedules,
                            scheduleViewModel = scheduleViewModel,
                            navController = navController
                        )
                    }
                }

            }

            item{
                Spacer(modifier = Modifier.height(50.dp))
            }
        }



            if (scheduleViewModel.tokenCheckResult.value) {
                loginViewModel.logOut()
            }
        }



}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InsertOrModifyScheduleView(
    navController: NavController,
    scheduleViewModel: ScheduleViewModel,
    loginViewModel: LoginViewModel,
    postType:()->Unit
) {
    val context = LocalContext.current

    if(scheduleViewModel.saveScheduleState.value){
        navController.navigateUp()
        scheduleViewModel.toggleSaveScheduleResult()
    }

    Column(
        modifier = Modifier

            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

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
                        text = scheduleViewModel.scheduleData.value.content,
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
                            onClick = { postType()},
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

@RequiresApi(Build.VERSION_CODES.O)
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
        InsertOrModifyScheduleView(navController, scheduleViewModel,loginViewModel, postType = {})
    }
}