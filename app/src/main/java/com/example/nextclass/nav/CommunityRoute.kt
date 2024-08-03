package com.example.nextclass.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.appComponent.LoginOrJoinNav
import com.example.nextclass.appComponent.MainBottomNav
import com.example.nextclass.items.BottomNavItem
import com.example.nextclass.items.CommunityTopNavItem
import com.example.nextclass.view.AllSchoolPostView
import com.example.nextclass.view.MyPostView
import com.example.nextclass.view.MySchoolPostView
import com.example.nextclass.view.TimeTableView
import com.example.nextclass.viewmodel.CommunityViewModel
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel
import com.example.nextclass.viewmodel.UserInfoViewModel


@Composable
fun CommunityGraph(
    navController: NavHostController,
    communityViewModel: CommunityViewModel,
    communityNavController: NavController
) {


    NavHost(
        navController = navController,
        startDestination = CommunityTopNavItem.ALL_SCHOOL
    ) {
        composable(CommunityTopNavItem.ALL_SCHOOL) {
            AllSchoolPostView(communityViewModel = communityViewModel, navController = navController, communityNavController = communityNavController)
        }
        composable(CommunityTopNavItem.MY_SCHOOL) {
            MySchoolPostView(communityViewModel = communityViewModel, navController = navController, communityNavController = communityNavController)
        }
        composable(CommunityTopNavItem.MY_POST) {
            MyPostView(communityViewModel = communityViewModel, navController = navController, communityNavController = communityNavController)
        }
    }
}