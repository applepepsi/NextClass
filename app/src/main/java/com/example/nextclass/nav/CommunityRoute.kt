package com.example.nextclass.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nextclass.items.CommunityTopNavItem
import com.example.nextclass.view.AllSchoolPostView
import com.example.nextclass.view.BestPostView
import com.example.nextclass.view.MySchoolPostView
import com.example.nextclass.viewmodel.CommunityViewModel


@Composable
fun CommunityGraph(
    navController: NavHostController,
    communityNavController: NavHostController,
    communityViewModel: CommunityViewModel,
) {


    NavHost(
        navController = communityNavController,
        startDestination = CommunityTopNavItem.AllSchool.screenRoute
    ) {
        composable(CommunityTopNavItem.AllSchool.screenRoute) {
            AllSchoolPostView(communityViewModel = communityViewModel, communityNavController = communityNavController,navController=navController)
        }
        composable(CommunityTopNavItem.MySchool.screenRoute) {
            MySchoolPostView(communityViewModel = communityViewModel,communityNavController = communityNavController,navController=navController)
        }
        composable(CommunityTopNavItem.BestPost.screenRoute) {
            BestPostView(communityViewModel = communityViewModel, communityNavController = communityNavController,navController=navController)
        }
    }
}