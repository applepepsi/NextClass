package com.example.nextclass.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.items.BottomNavItem

@Composable
fun BottomNav() {
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen(navController)
        }
        composable(BottomNavItem.Timetable.screenRoute) {
            TimetableScreen(navController)
        }
        composable(BottomNavItem.Schedule.screenRoute) {
            ScheduleScreen(navController)
        }
    }
}