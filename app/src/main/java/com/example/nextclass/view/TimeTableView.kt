package com.example.nextclass.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.nextclass.appComponent.AccreditationCalculationComponent
import com.example.nextclass.appComponent.BasicClass
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.TimeTableComponent
import com.example.nextclass.appComponent.sampleEvents
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.TimeTableViewModel

@Composable
fun TimeTableView(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController
) {
    val timeTableViewModel:TimeTableViewModel = hiltViewModel()
    val context = LocalContext.current
    Column(
        modifier = Modifier

            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        MainTextComponent(
            value = "2024년 1학기",
            modifier= Modifier
                .padding(top=20.dp)
        )
        TimeTableComponent(sampleEvents)

//        AccreditationCalculationComponent(
//            updateLayoutHeight = { initialHeight, initialTouchY, currentTouchY, screenHeight ->
//                timeTableViewModel.updateLayoutHeight(initialHeight, initialTouchY, currentTouchY, screenHeight)
//            }
//        )
    }

}

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