package com.example.nextclass.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.DescriptionTextComponent
import com.example.nextclass.appComponent.FindFieldComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun MainView(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

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
                        value = "메인뷰",
                        modifier= Modifier
                            .padding(top=20.dp)
                    )


                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
//    val testRepository = TestRepository()
//    val loginViewModel = LoginViewModel(testRepository)

    NextClassTheme {
//        LoginView(loginViewModel)
//        ForGotId(loginViewModel)
//        MainView()
    }
}