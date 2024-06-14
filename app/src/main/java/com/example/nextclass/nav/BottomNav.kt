package com.example.nextclass.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.nextclass.items.BottomNavItem
import com.example.nextclass.items.TopNavItem
import com.example.nextclass.view.HomeView

import com.example.nextclass.view.JoinView
import com.example.nextclass.view.LoginView
import com.example.nextclass.view.ScheduleScreen
import com.example.nextclass.view.TimetableScreen
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun BottomNav() {
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

@Composable
fun TopNavGraph(navController: NavHostController, loginViewModel: LoginViewModel) {
    NavHost(navController = navController, startDestination = TopNavItem.Login.screenRoute) {
        composable(TopNavItem.Login.screenRoute) {
            LoginView(loginViewModel)
        }
        composable(TopNavItem.Join.screenRoute) {
            JoinView(loginViewModel)
        }
    }
}