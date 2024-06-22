package com.example.nextclass.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.appComponent.LoginOrJoinNav
import com.example.nextclass.items.BottomNavItem
import com.example.nextclass.items.TopNavItem
import com.example.nextclass.view.ForGotId
import com.example.nextclass.view.ForGotPassword
import com.example.nextclass.view.HomeView

import com.example.nextclass.view.JoinView
import com.example.nextclass.view.LoginView
import com.example.nextclass.view.MainView
import com.example.nextclass.view.ScheduleScreen
import com.example.nextclass.view.TermsAndConditionsView
import com.example.nextclass.view.TimetableScreen
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


    NavHost(
        navController = mainNavController,
        startDestination = startDestination
    ) {
        //약간 구조가 이상함 나중에 수정 예정
        composable("loginOrJoinGraph") {
            LoginOrJoinNav(loginViewModel, mainNavController)
        }
        composable("mainNav") {
            MainNavGraph(mainNavController,loginViewModel)
        }
    }
}

@Composable
fun LoginOrJoinNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    NavHost(navController = navController, startDestination = TopNavItem.Login.screenRoute) {
        composable(TopNavItem.Login.screenRoute) {
            LoginView(loginViewModel, navController, mainNavHostController)
        }
        composable(TopNavItem.Join.screenRoute) {
            JoinView(loginViewModel, navController)
        }
        composable("findIdView") {
            ForGotId(loginViewModel, navController)
        }
        composable("findPasswordView") {
            ForGotPassword(loginViewModel, navController)
        }
        composable("termsAndConditionsView") {
            TermsAndConditionsView(loginViewModel, navController)
        }
    }
}

@Composable
fun MainNavGraph(mainNavController: NavHostController,loginViewModel: LoginViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainView") {
        composable("mainView") {
            MainView(navController,loginViewModel,mainNavController)
        }
    }
}

@Composable
fun TimeTableNav() {
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeView(navController)
        }
        composable(BottomNavItem.Timetable.screenRoute) {
            TimetableScreen(navController)
        }
        composable(BottomNavItem.Schedule.screenRoute) {
            ScheduleScreen(navController)
        }
    }
}










