package com.example.nextclass.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.nextclass.items.BottomNavItem
import com.example.nextclass.view.CommunityView
import com.example.nextclass.view.HomeView
import com.example.nextclass.view.InsertScheduleView
import com.example.nextclass.view.ScheduleView
import com.example.nextclass.view.TimeTableView
import com.example.nextclass.view.UserProfileView
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    val scheduleViewModel:ScheduleViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "homeRoute") {
        homeRoute(navController, loginViewModel, mainNavHostController)
        timetableRoute(navController, loginViewModel, mainNavHostController)
        communityRoute(navController, loginViewModel, mainNavHostController)
        scheduleRoute(navController, loginViewModel, mainNavHostController,scheduleViewModel)
        userProfileRoute(navController, loginViewModel, mainNavHostController)
    }
}


private fun NavGraphBuilder.homeRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    navigation(
        route = "homeRoute",
        startDestination = BottomNavItem.Home.screenRoute
    ) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeView(navController, loginViewModel, mainNavHostController)
        }
    }
}

private fun NavGraphBuilder.timetableRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {

    navigation(
        route = "timetableRoute",
        startDestination = BottomNavItem.Timetable.screenRoute
    ) {
        composable(BottomNavItem.Timetable.screenRoute) {
            TimeTableView(navController, loginViewModel, mainNavHostController)
        }
    }
}

private fun NavGraphBuilder.communityRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    navigation(
        route = "communityRoute",
        startDestination = BottomNavItem.Community.screenRoute
    ) {
        composable(BottomNavItem.Community.screenRoute) {
            CommunityView(navController, loginViewModel, mainNavHostController)
        }
    }
}

private fun NavGraphBuilder.scheduleRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    scheduleViewModel: ScheduleViewModel
) {


    navigation(
        route = "scheduleRoute",
        startDestination = BottomNavItem.Schedule.screenRoute
    ) {
        scheduleView(navController,scheduleViewModel)
        insertScheduleView(navController,scheduleViewModel)
    }
}

private fun NavGraphBuilder.scheduleView(
    navController: NavHostController,
    scheduleViewModel: ScheduleViewModel,
) {
    composable(BottomNavItem.Schedule.screenRoute) {
        ScheduleView(navController,scheduleViewModel)
    }
}

private fun NavGraphBuilder.insertScheduleView(
    navController: NavHostController,
    scheduleViewModel: ScheduleViewModel,
) {
    composable("insertScheduleView") {
        InsertScheduleView(navController,scheduleViewModel)
    }
}

private fun NavGraphBuilder.userProfileRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    navigation(
        route = "userProfileRoute",
        startDestination = BottomNavItem.UserProfile.screenRoute
    ) {
        composable(BottomNavItem.UserProfile.screenRoute) {
            UserProfileView(navController, loginViewModel, mainNavHostController)
        }
    }
}