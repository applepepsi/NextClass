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
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nextclass.appComponent.TopNav
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
//            val testRepository = TestRepository()
//            val loginViewModel = LoginViewModel(testRepository)
//
//            val navController = rememberNavController()

            NextClassTheme {
                Greeting()
//                BasicClass(sampleEvents)
//                Greeting()
//            InsertPasswordCodeView(loginViewModel = loginViewModel, navController = navController)
            }
        }
    }
}


@Composable
fun Greeting() {
//    val navController = rememberNavController()

    //저장된 사용자 정보가있다면 로그인 시도
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = hiltViewModel()
    val autoLoginInfo = context.getSharedPreferences("autoLoginInfo", Context.MODE_PRIVATE)

    val autoLoginId=autoLoginInfo.getString("userId",null)
    val autoLoginPassword=autoLoginInfo.getString("userPassword",null)
    if (autoLoginId != null) {
        Log.d("autoLoginId",autoLoginId)
        if (autoLoginPassword != null) {
            Log.d("autoLoginPassword",autoLoginPassword)
        }
    }

    loginViewModel.tryAutoLogin(autoLoginId,autoLoginPassword)

    TopNav(loginViewModel)

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NextClassTheme {

    }
}












