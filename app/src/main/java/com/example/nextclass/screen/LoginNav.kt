package com.example.nextclass.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.LoginView
import com.example.nextclass.items.LoginNavItems
import com.example.nextclass.viewmodel.LoginViewModel


@Composable
fun LoginNav(loginViewModel: LoginViewModel = hiltViewModel()) {

//    val loginNav= rememberNavController()
//
//    NavHost(navController = loginNav, startDestination = LoginNavItems.JOIN.name) {
//        composable(route=LoginNavItems.LOGIN.name) {
//            LoginView(loginViewModel)
//        }
//        composable(route=LoginNavItems.JOIN.name) {
//            JoinView(loginViewModel)
//        }
//        composable(route=LoginNavItems.FIND_ID.name) {
//
//        }
//        composable(route=LoginNavItems.FIND_PASSWORD.name) {
//
//        }
//    }
}