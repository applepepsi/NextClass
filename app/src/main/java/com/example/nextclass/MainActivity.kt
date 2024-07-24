package com.example.nextclass

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
import com.example.nextclass.appComponent.ProgressBarComponent
import com.example.nextclass.nav.AppNav
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.utils.TokenManager
import com.example.nextclass.utils.UserInfoManager
import com.example.nextclass.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setAppLocale()

        setContent {
            NextClassTheme {
                Greeting()

            }
        }
    }
    private fun setAppLocale() {
        val locale=Locale.KOREA
        val config = resources.configuration
        Locale.setDefault(locale)
        config.setLocale(locale)
        createConfigurationContext(config)
    }
}



@Composable
fun Greeting() {

    //저장된 사용자 정보가있다면 로그인 시도
    val context = LocalContext.current

    val loginViewModel: LoginViewModel = hiltViewModel()

    val (autoLoginId, autoLoginPassword) = UserInfoManager.getUserInfo(context)

    if (autoLoginId != null) {
        Log.d("자동로그인 id", autoLoginId)
    }
    if (autoLoginPassword != null) {
        Log.d("자동로그인 password", autoLoginPassword)
    }
    LaunchedEffect(autoLoginId, autoLoginPassword) {
        loginViewModel.tryAutoLogin(autoLoginId, autoLoginPassword)
    }

    if(loginViewModel.loginResult.value){
        TokenManager.saveToken(context,loginViewModel.tokenData.value)
    }



    //스플래시화면으로 변경예정
//    if (!loginViewModel.loading.value) {
//
//    } else {
//        // 로딩 중일때 로딩 화면 표시
//        ProgressBarComponent()
//    }
    AppNav(loginViewModel)

//    AppNav(loginViewModel)
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NextClassTheme {

    }
}












