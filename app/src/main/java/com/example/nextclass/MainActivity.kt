package com.example.nextclass

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.nav.AppNav
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            NextClassTheme {
                Greeting()

            }
        }
    }
}


@Composable
fun Greeting() {

    //저장된 사용자 정보가있다면 로그인 시도
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = hiltViewModel()
    val autoLoginInfo = context.getSharedPreferences("autoLoginInfo", Context.MODE_PRIVATE)

    val autoLoginId=autoLoginInfo.getString("userId",null)
    val autoLoginPassword=autoLoginInfo.getString("userPassword",null)

    LaunchedEffect(autoLoginId, autoLoginPassword) {
        loginViewModel.tryAutoLogin(autoLoginId, autoLoginPassword)
    }
    if (!loginViewModel.loading.value) {
        AppNav(loginViewModel)
    } else {
        // 로딩 중일때 로딩 화면 표시
        CircularProgressIndicator()
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NextClassTheme {

    }
}












