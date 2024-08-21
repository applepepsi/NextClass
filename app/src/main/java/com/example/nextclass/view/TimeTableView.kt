package com.example.nextclass.view

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.appComponent.AccreditationCalculationComponent
import com.example.nextclass.appComponent.AddSemesterPopupComponent
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.ClassDetail
import com.example.nextclass.appComponent.ClassModify
import com.example.nextclass.appComponent.InsertClassData
import com.example.nextclass.appComponent.ModifyScoreComponent
import com.example.nextclass.appComponent.ProgressBarFullComponent
import com.example.nextclass.appComponent.TimeTableComponent

import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.repository.testRepo.TimeTableTestRepo
import com.example.nextclass.utils.GetSemester
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.TimeTableViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeTableView(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController,
    timeTableViewModel: TimeTableViewModel
) {

    val context = LocalContext.current

    val button= ImageVector.vectorResource(R.drawable.plus_icon)

    LaunchedEffect(Unit) {
        timeTableViewModel.getTimeTableScore()
    }

    LaunchedEffect(timeTableViewModel.timeTableToastMessage.value) {
        timeTableViewModel.timeTableToastMessage.value?.let{
            Toast.makeText(context, timeTableViewModel.timeTableToastMessage.value, Toast.LENGTH_SHORT,)
                .show()
            timeTableViewModel.clearToastMessage()
        }
    }

    LaunchedEffect(timeTableViewModel.scoreModifyResultToastMessage.value) {
        timeTableViewModel.scoreModifyResultToastMessage.value?.let{
            Toast.makeText(context, timeTableViewModel.scoreModifyResultToastMessage.value, Toast.LENGTH_SHORT,)
                .show()
            timeTableViewModel.resetScoreModifyToastMessage()
        }
    }

    if(timeTableViewModel.addScorePopupState.value){
        AddSemesterPopupComponent(timeTableViewModel = timeTableViewModel)
    }

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetContent = {

            AccreditationCalculationComponent(
                timeTableViewModel=timeTableViewModel,
                navController=navController
            )
        },
        sheetPeekHeight = 200.dp,
    ) {

        ProgressBarFullComponent(state = timeTableViewModel.loading.value)

        LaunchedEffect(Unit) {
            timeTableViewModel.getTimeTable()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    modifier = Modifier
                        .heightIn()
                        .padding(start = 20.dp),
                    text=GetSemester.convertCurrentSemester(),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    ),
                )

                IconButton(
                    onClick = {
                        timeTableViewModel.toggleInsertClassDataDialogState()

                    },
                    modifier = Modifier.padding(end=10.dp)
                ){
                    Icon(
                        imageVector = button,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                    )
                }
            }
            if(timeTableViewModel.insertClassDataDialogState.value){
                InsertClassData (timeTableViewModel=timeTableViewModel)
            }
            TimeTableComponent(
                classDataList = timeTableViewModel.timeTableDataList.value,
//                sampleEvents,
                setShowClassDetailDialog={
                    timeTableViewModel.toggleSetShowClassDetailDialogState()
                },
                setShowClassDataModifyDialog = {
                    timeTableViewModel.toggleSetShowClassDataModifyDialogState()
                },
                setSelectClassData = {
                    classData -> timeTableViewModel.setSelectClassData(classData)
                },
            )

            if (timeTableViewModel.setShowClassDetailDialog.value) {
                ClassDetail(
                    timeTableViewModel.selectClassData.value,
                    setShowClassDetailDialog = {
                        timeTableViewModel.toggleSetShowClassDetailDialogState()
                    },
                    setShowClassDataModifyDialog = {
                        timeTableViewModel.toggleSetShowClassDataModifyDialogState()
                    },
                    deleteClassData = {
                        timeTableViewModel.postDeleteScheduleData()
                    }
                )
            }
            if (timeTableViewModel.setShowClassDataModifyDialog.value) {
                ClassModify(
                    timeTableViewModel=timeTableViewModel
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyScoreView(
    navController: NavController,
    timeTableViewModel: TimeTableViewModel
) {

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ProgressBarFullComponent(state = timeTableViewModel.loading.value)

        Column(
            modifier = Modifier
                .padding(top = 20.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AppBarTextAndButtonComponent(
                value = "성적 수정하기",
                navController = navController,
                showLeftButton = true,
            )
        }

        ModifyScoreComponent(timeTableViewModel = timeTableViewModel,navController=navController)


    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TimeTablePreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val timeTableTestRepo=TimeTableTestRepo()
    val loginViewModel = LoginViewModel(testRepository)
    val timeTableViewModel=TimeTableViewModel(timeTableTestRepo)

    MaterialTheme {
        TimeTableView(mainNavController, loginViewModel, navController, timeTableViewModel)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ModifyScorePreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val timeTableTestRepo=TimeTableTestRepo()
    val loginViewModel = LoginViewModel(testRepository)
    val timeTableViewModel=TimeTableViewModel(timeTableTestRepo)

    MaterialTheme {
        ModifyScoreView(mainNavController,timeTableViewModel)
    }
}