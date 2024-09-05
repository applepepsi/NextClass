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
import com.example.nextclass.view.InsertOrModifyPostComponent
import com.example.nextclass.items.BottomNavItem
import com.example.nextclass.view.ChangeEmailInsertCodeView
import com.example.nextclass.view.ChangeEmailView
import com.example.nextclass.view.ChangePasswordView

import com.example.nextclass.view.ChangeUserInfoView
import com.example.nextclass.view.CommunitySearchView
import com.example.nextclass.view.CommunityView
import com.example.nextclass.view.HomeView
import com.example.nextclass.view.InsertOrModifyScheduleView
import com.example.nextclass.view.MemberDeletePasswordConfirmView
import com.example.nextclass.view.ModifyScoreView
import com.example.nextclass.view.MyCommentView
import com.example.nextclass.view.MyFavoritePostView
import com.example.nextclass.view.MyPostView
import com.example.nextclass.view.NotificationSettingView
import com.example.nextclass.view.PostDetailView
import com.example.nextclass.view.ScheduleView
import com.example.nextclass.view.TimeTableView
import com.example.nextclass.view.UserProfileView
import com.example.nextclass.viewmodel.CommunityViewModel
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel
import com.example.nextclass.viewmodel.TimeTableViewModel
import com.example.nextclass.viewmodel.UserInfoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    val scheduleViewModel:ScheduleViewModel = hiltViewModel()
    val userInfoViewModel:UserInfoViewModel= hiltViewModel()
    val communityViewModel:CommunityViewModel= hiltViewModel()
    val timeTableViewModel: TimeTableViewModel = hiltViewModel()


    NavHost(navController = navController, startDestination = "homeRoute") {
        homeRoute(navController, loginViewModel, mainNavHostController,scheduleViewModel,userInfoViewModel,communityViewModel,timeTableViewModel)
        timetableRoute(navController, loginViewModel, mainNavHostController,timeTableViewModel)
        communityRoute(navController, loginViewModel, mainNavHostController,communityViewModel)
        scheduleRoute(navController, loginViewModel, mainNavHostController,scheduleViewModel)
        userProfileRoute(navController, loginViewModel, mainNavHostController,userInfoViewModel,communityViewModel)
        postDetailView(navController,loginViewModel,mainNavHostController,communityViewModel)

    }
}


private fun NavGraphBuilder.homeRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    scheduleViewModel: ScheduleViewModel,
    userInfoViewModel: UserInfoViewModel,
    communityViewModel: CommunityViewModel,
    timeTableViewModel: TimeTableViewModel
) {
    navigation(
        route = "homeRoute",
        startDestination = BottomNavItem.Home.screenRoute
    ) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeView(navController, loginViewModel, mainNavHostController,communityViewModel,scheduleViewModel,timeTableViewModel,userInfoViewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun NavGraphBuilder.timetableRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    timeTableViewModel: TimeTableViewModel
) {

    navigation(
        route = "timetableRoute",
        startDestination = BottomNavItem.Timetable.screenRoute
    ) {
        composable(BottomNavItem.Timetable.screenRoute) {
            TimeTableView(navController, loginViewModel, mainNavHostController,timeTableViewModel)
        }
        composable("modifyScoreView") {
            ModifyScoreView(navController,timeTableViewModel = timeTableViewModel)
        }
    }
}

private fun NavGraphBuilder.communityRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    communityViewModel: CommunityViewModel,

) {


    navigation(
        route = "communityRoute",
        startDestination = BottomNavItem.Community.screenRoute
    ) {
        communityView(navController,loginViewModel,mainNavHostController,communityViewModel)
        postDetailView(navController,loginViewModel,mainNavHostController,communityViewModel)
        insertPostView(navController,loginViewModel,mainNavHostController,communityViewModel)
        modifyPostView(navController,loginViewModel,mainNavHostController,communityViewModel)
        communitySearchView(navController, loginViewModel, mainNavHostController,communityViewModel)
    }
}

private fun NavGraphBuilder.communityView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    communityViewModel: CommunityViewModel,

) {

    composable(BottomNavItem.Community.screenRoute) {
        CommunityView(navController, loginViewModel, mainNavHostController,communityViewModel)
    }

}

fun NavGraphBuilder.postDetailView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    communityViewModel: CommunityViewModel
) {
    composable("postDetailView") {
        PostDetailView(navController, loginViewModel, mainNavHostController,communityViewModel)
    }
}

private fun NavGraphBuilder.insertPostView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    communityViewModel: CommunityViewModel
) {
    composable("insertPostView") {
        InsertOrModifyPostComponent(navController, communityViewModel,loginViewModel, postType = {communityViewModel.postWritePostData()})
    }
}

private fun NavGraphBuilder.modifyPostView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    communityViewModel: CommunityViewModel
) {
    composable("modifyPostView") {
        InsertOrModifyPostComponent(navController, communityViewModel,loginViewModel, postType = {communityViewModel.postModifyPostData()})
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
        modifyScheduleView(navController,scheduleViewModel,loginViewModel)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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
        InsertOrModifyScheduleView(navController,scheduleViewModel,loginViewModel, postType = {scheduleViewModel.postScheduleData()})
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun NavGraphBuilder.modifyScheduleView(
    navController: NavHostController,
    scheduleViewModel: ScheduleViewModel,
    loginViewModel: LoginViewModel,
) {
    composable("modifyScheduleView") {
        InsertOrModifyScheduleView(navController,scheduleViewModel,loginViewModel, postType = {scheduleViewModel.postModifyScheduleData()})
    }
}

private fun NavGraphBuilder.userProfileRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel,
    communityViewModel: CommunityViewModel
) {
    navigation(
        route = "userProfileRoute",
        startDestination = BottomNavItem.UserProfile.screenRoute
    ) {

        userProfileView(navController, loginViewModel, mainNavHostController,userInfoViewModel)
        changePasswordRoute(loginViewModel = loginViewModel, navController = navController,mainNavHostController=mainNavHostController, userInfoViewModel = userInfoViewModel)
        changeEmailRoute(loginViewModel = loginViewModel, navController = navController,mainNavHostController=mainNavHostController, userInfoViewModel = userInfoViewModel)
        changeUserProfileRoute(loginViewModel = loginViewModel, navController = navController,mainNavHostController=mainNavHostController, userInfoViewModel = userInfoViewModel)
        memberDeleteRoute(loginViewModel = loginViewModel, navController = navController,mainNavHostController=mainNavHostController, userInfoViewModel = userInfoViewModel)
        myPostRoute(loginViewModel = loginViewModel, navController = navController,mainNavHostController=mainNavHostController, userInfoViewModel = userInfoViewModel,communityViewModel=communityViewModel)
        myCommentRoute(loginViewModel = loginViewModel, navController = navController,mainNavHostController=mainNavHostController, userInfoViewModel = userInfoViewModel,communityViewModel=communityViewModel)
        myFavoritePostRoute(loginViewModel = loginViewModel, navController = navController,mainNavHostController=mainNavHostController, userInfoViewModel = userInfoViewModel,communityViewModel=communityViewModel)
        notificationSettingRoute(navController=navController,userInfoViewModel=userInfoViewModel)
    }
}

private fun NavGraphBuilder.notificationSettingRoute(
    navController: NavHostController,
    userInfoViewModel: UserInfoViewModel,

) {
    navigation(
        route = "notificationSettingRoute",
        startDestination = "notificationSettingView"
    ) {

        notificationSettingView(navController=navController,userInfoViewModel=userInfoViewModel)
    }
}

private fun NavGraphBuilder.notificationSettingView(
    navController: NavHostController,

    userInfoViewModel: UserInfoViewModel,

) {
    composable("notificationSettingView") {
        NotificationSettingView(navController = navController, userInfoViewModel = userInfoViewModel)
    }
}


private fun NavGraphBuilder.myPostRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel,
    communityViewModel: CommunityViewModel
) {
    navigation(
        route = "myPostRoute",
        startDestination = "myPostView"
    ) {

        myPostView(navController, loginViewModel, mainNavHostController,userInfoViewModel,communityViewModel)
        communitySearchView(navController, loginViewModel, mainNavHostController,communityViewModel)
    }
}



private fun NavGraphBuilder.myPostView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel,
    communityViewModel: CommunityViewModel
) {
    composable("myPostView") {
        MyPostView(communityViewModel = communityViewModel, navController=navController)
    }
}
private fun NavGraphBuilder.myCommentRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel,
    communityViewModel: CommunityViewModel
) {
    navigation(
        route = "myCommentRoute",
        startDestination = "myCommentView"
    ) {

        myCommentView(navController, loginViewModel, mainNavHostController,userInfoViewModel,communityViewModel)
        communitySearchView(navController, loginViewModel, mainNavHostController,communityViewModel)
    }
}
private fun NavGraphBuilder.myCommentView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel,
    communityViewModel: CommunityViewModel
) {


    composable("myCommentView") {
        MyCommentView(communityViewModel = communityViewModel, navController=navController)
    }
}

private fun NavGraphBuilder.myFavoritePostRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel,
    communityViewModel: CommunityViewModel
) {
    navigation(
        route = "myFavoritePostRoute",
        startDestination = "myFavoritePostView"
    ) {

        myFavoritePostView(navController, loginViewModel, mainNavHostController,userInfoViewModel,communityViewModel)
        communitySearchView(navController, loginViewModel, mainNavHostController,communityViewModel)
    }
}
private fun NavGraphBuilder.myFavoritePostView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel,
    communityViewModel: CommunityViewModel
) {


    composable("myFavoritePostView") {
        MyFavoritePostView(communityViewModel = communityViewModel, navController=navController)
    }
}

private fun NavGraphBuilder.communitySearchView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,

    communityViewModel: CommunityViewModel
) {
    composable("communitySearchView") {
        CommunitySearchView(communityViewModel = communityViewModel, navController=navController)
    }
}



private fun NavGraphBuilder.userProfileView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {


    composable(BottomNavItem.UserProfile.screenRoute) {
        UserProfileView(navController,loginViewModel,mainNavHostController,userInfoViewModel)
    }
}

private fun NavGraphBuilder.changePasswordRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {
    navigation(
        route = "changePasswordRoute",
        startDestination = "passwordConfirmView"
    ) {

        changePasswordView(navController, loginViewModel, mainNavHostController,userInfoViewModel)

    }
}

private fun NavGraphBuilder.changeEmailRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {
    navigation(
        route = "changeEmailRoute",
        startDestination = "changeEmailView"
    ) {

        changeEmailView(navController, loginViewModel, mainNavHostController,userInfoViewModel)

        changeEmailInsertCodeView(navController, loginViewModel, mainNavHostController,userInfoViewModel)

    }
}

private fun NavGraphBuilder.changeUserProfileRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {
    navigation(
        route = "changeUserProfileRoute",
        startDestination = "changeUserInfoView"
    ) {

        changeUserInfoView(navController, loginViewModel, mainNavHostController,userInfoViewModel)

    }
}


private fun NavGraphBuilder.memberDeleteRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {
    navigation(
        route = "memberSecessionRoute",
        startDestination = "changeUserInfoView"
    ) {

        memberDeletePasswordCheckView(navController, loginViewModel, mainNavHostController,userInfoViewModel)

    }
}

private fun NavGraphBuilder.memberDeletePasswordCheckView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {
    composable("memberDeletePasswordConfirmView") {
        MemberDeletePasswordConfirmView(
            loginViewModel = loginViewModel,
            navController = navController,
            mainNavHostController = mainNavHostController
        )
    }
}

private fun NavGraphBuilder.changeUserInfoView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {
    composable("changeUserInfoView") {
        ChangeUserInfoView(navController = navController, userInfoViewModel = userInfoViewModel)
    }
}

private fun NavGraphBuilder.changePasswordView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {
    composable("changePasswordView") {
        ChangePasswordView(navController = navController, userInfoViewModel = userInfoViewModel,loginViewModel)
    }
}

private fun NavGraphBuilder.changeEmailView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {
    composable("changeEmailView") {
        ChangeEmailView(navController = navController, userInfoViewModel = userInfoViewModel)
    }
}

private fun NavGraphBuilder.changeEmailInsertCodeView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController,
    userInfoViewModel: UserInfoViewModel
) {
    composable("changeEmailInsertCodeView") {
        ChangeEmailInsertCodeView(navController = navController, userInfoViewModel = userInfoViewModel)
    }
}



