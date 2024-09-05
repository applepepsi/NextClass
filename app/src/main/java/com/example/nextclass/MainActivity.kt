package com.example.nextclass

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nextclass.nav.AppNav
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.utils.TokenManager
import com.example.nextclass.utils.UserInfoManager
import com.example.nextclass.view.SplashScreen
import com.example.nextclass.viewmodel.LoginViewModel
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.util.Locale


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val postSequence = intent.getStringExtra("POST_SEQUENCE")

        // 커뮤니티 댓글작성시 키보드에 맞게 패딩시키기 위해
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setAppLocale()

        setContent {
            NextClassTheme {


                Greeting(postSequence)

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
fun Greeting(postSequence: String?) {

    val loginViewModel: LoginViewModel = hiltViewModel()

    //저장된 사용자 정보가있다면 로그인 시도
    val context = LocalContext.current

    val (autoLoginId, autoLoginPassword) = UserInfoManager.getUserInfo(context)


    LaunchedEffect(Unit) {
        if(postSequence !=null){
            loginViewModel.setDirectPostSequence(postSequence)
        }
    }

    LaunchedEffect(Unit) {

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {

                TokenManager.saveFcmToken(context,task.result)
                Log.d("FCM Token", task.result)

            } else {
                Log.w("FCM Token", "FCM 토큰 발급 실패")
            }
        }
    }


    val fcmToken=TokenManager.getFcmToken(context)


    LaunchedEffect(autoLoginId, autoLoginPassword) {

        if (autoLoginId != null && autoLoginPassword != null) {
            loginViewModel.tryAutoLogin(autoLoginId, autoLoginPassword, fcmToken)

        }
        loginViewModel.toggleSplashVisibleState()
        delay(1000)
        loginViewModel.toggleSplashVisibleState()
    }


    LaunchedEffect(loginViewModel.loginResult.value) {
        if (loginViewModel.loginResult.value) {
            TokenManager.saveToken(context, loginViewModel.tokenData.value)
            Log.d("토큰데이터", loginViewModel.tokenData.value.toString())

        }
    }



    if(loginViewModel.splashScreenVisibility.value){
        SplashScreen()
    }else{
        AppNav(loginViewModel)
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NextClassTheme {

    }
}












