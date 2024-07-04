package com.example.nextclass.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.nextclass.items.BottomNavItem
import com.example.nextclass.view.ChangeUserInfo
import com.example.nextclass.view.CommunityView
import com.example.nextclass.view.HomeView
import com.example.nextclass.view.InsertScheduleView
import com.example.nextclass.view.PasswordConfirm
import com.example.nextclass.view.ScheduleView
import com.example.nextclass.view.TimeTableView
import com.example.nextclass.view.UserProfileView
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel

@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
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
        scheduleView(navController,scheduleViewModel,loginViewModel)
        insertScheduleView(navController,scheduleViewModel,loginViewModel)
    }
}

private fun NavGraphBuilder.scheduleView(
    navController: NavHostController,
    scheduleViewModel: ScheduleViewModel,
    loginViewModel: LoginViewModel,
) {
    composable(BottomNavItem.Schedule.screenRoute) {
        ScheduleView(navController,scheduleViewModel,loginViewModel)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun NavGraphBuilder.insertScheduleView(
    navController: NavHostController,
    scheduleViewModel: ScheduleViewModel,
    loginViewModel: LoginViewModel,
) {
    composable("insertScheduleView") {
        InsertScheduleView(navController,scheduleViewModel,loginViewModel)
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
            userProfileView(navController, loginViewModel, mainNavHostController)
            passwordConfirmView(loginViewModel = loginViewModel, navController = navController,mainNavHostController=mainNavHostController)
            changeUserInfo(loginViewModel = loginViewModel, navController = navController,mainNavHostController=mainNavHostController)
        }
    }
}



private fun NavGraphBuilder.userProfileView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    composable(BottomNavItem.UserProfile.screenRoute) {
        UserProfileView(navController,loginViewModel,mainNavHostController)
    }
}


private fun NavGraphBuilder.passwordConfirmView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    composable("passwordConfirmView") {
        PasswordConfirm(loginViewModel,navController,mainNavHostController)
    }
}


private fun NavGraphBuilder.changeUserInfo(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    composable("changeUserInfo") {
        ChangeUserInfo(loginViewModel,navController,mainNavHostController)
    }
}

