package com.example.nextclass.nav

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.appComponent.LoginOrJoinNav
import com.example.nextclass.appComponent.MainBottomNav
import com.example.nextclass.items.BottomNavItem
import com.example.nextclass.items.TopNavItem
import com.example.nextclass.utils.GlobalNavigationHandler
import com.example.nextclass.utils.GlobalNavigator
import com.example.nextclass.utils.TokenManager
import com.example.nextclass.utils.UserInfoManager
import com.example.nextclass.view.CommunityView
import com.example.nextclass.view.ForGotId
import com.example.nextclass.view.ForGotPassword
import com.example.nextclass.view.HomeView

import com.example.nextclass.view.JoinView
import com.example.nextclass.view.LoginView
import com.example.nextclass.view.ScheduleScreen
import com.example.nextclass.view.ScheduleView
import com.example.nextclass.view.TermsAndConditionsView
import com.example.nextclass.view.TimeTableView
import com.example.nextclass.view.UserProfileView
import com.example.nextclass.viewmodel.LoginViewModel

//todo로그아웃 구현하기
@Composable
fun AppNav(loginViewModel: LoginViewModel) {
    val mainNavController = rememberNavController()

    val startDestination=if(loginViewModel.loginResult.value){
        "mainNav"
    } else {
        "loginOrJoinGraph"
    }

    val context = LocalContext.current

    //테스트 해봐야함
    DisposableEffect(Unit) {
        GlobalNavigator.registerHandler(object : GlobalNavigationHandler {
            override fun logout() {

                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed(Runnable { Toast.makeText(context, "토큰이 만료 되었습니다. 다시 로그인해 주세요", Toast.LENGTH_SHORT).show() }, 0)


                Log.d("자동로그아웃 ","로그아웃")
                loginViewModel.logOut()
                UserInfoManager.clearUserInfo(context)
                TokenManager.clearToken(context)
//                mainNavController.navigate("loginOrJoinGraph") {
//                    popUpTo(mainNavController.graph.startDestinationId) {
//                        inclusive = true
//                    }
//                }
            }
        })
        onDispose {
            GlobalNavigator.unregisterHandler()
        }
    }


    NavHost(
        navController = mainNavController,
        startDestination = startDestination
    ) {
        composable("loginOrJoinGraph") {
            LoginOrJoinNav(loginViewModel, mainNavController)
        }
        composable("mainNav") {
            MainBottomNav(loginViewModel,mainNavController)
        }
    }
}













