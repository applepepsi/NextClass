package com.example.nextclass.appComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.items.TopNavItem
import com.example.nextclass.nav.LoginOrJoinNavGraph


import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color
import com.example.nextclass.ui.theme.Navi_Green
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun LoginOrJoinNav(loginViewModel: LoginViewModel, mainNavController: NavHostController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        color = Background_Color,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            if (currentRoute != "findIdView" && currentRoute != "findPasswordView" && currentRoute != "termsAndConditionsView") {
                TopBarComponent(navController = navController)
            }

            LoginOrJoinNavGraph(
                loginViewModel = loginViewModel,
                navController = navController,
                mainNavHostController = mainNavController
            )
        }
    }
}

@Composable
fun TopBarComponent(navController: NavHostController) {
    val screens = listOf(
        TopNavItem.Login,
        TopNavItem.Join
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Surface(
        modifier = Modifier
            .padding(top = 30.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(50.dp),
        color = Color.White,
        shape = RoundedCornerShape(40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                AddTopNavItem(screen = screen, currentDestination = currentDestination, navController = navController)
            }
        }
    }
}

@Composable
fun RowScope.AddTopNavItem(
    screen: TopNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.screenRoute } == true
    val background = if (selected) Navi_Green else Color.White
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .weight(1f)
            .padding(6.dp)
            .height(60.dp)
    ) {
        Box(
            modifier = Modifier
                .background(background)
                .clickable(
                    onClick = {
                        navController.navigate(screen.screenRoute) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    interactionSource = interactionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = screen.title,
                color = if (selected) Color.White else Color.Gray.copy(alpha = 0.5f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NavPreview() {

    val mainNavController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    NextClassTheme {
        LoginOrJoinNav(loginViewModel,mainNavController)

    }
}
