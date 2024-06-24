package com.example.nextclass.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.nextclass.items.TopNavItem
import com.example.nextclass.view.ForGotId
import com.example.nextclass.view.ForGotPassword
import com.example.nextclass.view.JoinView
import com.example.nextclass.view.LoginView
import com.example.nextclass.view.TermsAndConditionsView
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun LoginOrJoinNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    NavHost(navController = navController, startDestination = "loginGraph") {
        loginRoute(navController, loginViewModel, mainNavHostController)
        joinRoute(navController, loginViewModel)
    }
}

private fun NavGraphBuilder.loginRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    navigation(
        route = "loginGraph",
        startDestination = TopNavItem.Login.screenRoute
    ) {
        loginView(navController, loginViewModel, mainNavHostController)
        forgotId(navController, loginViewModel)
        forgotPassword(navController, loginViewModel)
    }
}

private fun NavGraphBuilder.joinRoute(
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    navigation(
        route = "joinGraph",
        startDestination = TopNavItem.Join.screenRoute
    ) {
        joinView(navController, loginViewModel)
        termsAndConditionsView(navController, loginViewModel)
    }
}

private fun NavGraphBuilder.loginView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavHostController: NavHostController
) {
    composable(TopNavItem.Login.screenRoute) {
        LoginView(loginViewModel, navController, mainNavHostController)
    }
}

private fun NavGraphBuilder.joinView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
) {
    composable(TopNavItem.Join.screenRoute) {
        JoinView(loginViewModel, navController)
    }
}
private fun NavGraphBuilder.termsAndConditionsView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
) {
    composable("termsAndConditionsView") {
        TermsAndConditionsView(loginViewModel, navController)
    }
}
private fun NavGraphBuilder.forgotId(
    navController: NavHostController,
    loginViewModel: LoginViewModel,

    ) {
    composable("findIdView") {
        ForGotId(loginViewModel, navController)
    }
}

private fun NavGraphBuilder.forgotPassword(
    navController: NavHostController,
    loginViewModel: LoginViewModel,

    ) {
    composable("findPasswordView") {
        ForGotPassword(loginViewModel, navController)
    }
}