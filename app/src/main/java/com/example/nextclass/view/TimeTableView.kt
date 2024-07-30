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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.ClassData
import com.example.nextclass.R
import com.example.nextclass.appComponent.AccreditationCalculationComponent
import com.example.nextclass.appComponent.ClassDetail
import com.example.nextclass.appComponent.ClassModify
import com.example.nextclass.appComponent.InsertClassData
import com.example.nextclass.appComponent.ProgressBarComponent
import com.example.nextclass.appComponent.TimeTableComponent

import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.TimeTableViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeTableView(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController
) {
    val timeTableViewModel:TimeTableViewModel = hiltViewModel()
    val context = LocalContext.current

    val button= ImageVector.vectorResource(R.drawable.plus_icon)


    //todo 작동하는지 테스트 해봐야함
    LaunchedEffect(timeTableViewModel.timeTableToastMessage.value) {
        Toast.makeText(
            context,
            timeTableViewModel.timeTableToastMessage.value,
            Toast.LENGTH_SHORT,
        ).show()
    }

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetContent = {

            AccreditationCalculationComponent()

        },
        sheetPeekHeight = 200.dp,
    ) {
        ProgressBarComponent(state = timeTableViewModel.loading.value)
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
                    text="2024년 1학기",
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
@Preview(showBackground = true)
@Composable
fun TimeTablePreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)


    MaterialTheme {
        TimeTableView(mainNavController,loginViewModel,navController)
    }
}