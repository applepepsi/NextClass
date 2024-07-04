package com.example.nextclass.appComponent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.items.BottomNavItem
import com.example.nextclass.items.TopNavItem
import com.example.nextclass.nav.LoginOrJoinNavGraph
import com.example.nextclass.nav.MainNavGraph
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Navi_Green
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.viewmodel.LoginViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainBottomNav(loginViewModel: LoginViewModel, mainNavController: NavHostController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomNavScreens = listOf(
        BottomNavItem.Home.screenRoute,
        BottomNavItem.Timetable.screenRoute,
        BottomNavItem.Community.screenRoute,
        BottomNavItem.Schedule.screenRoute,
        BottomNavItem.UserProfile.screenRoute
    )


    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
//            color = Background_Color,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column {



                MainNavGraph(
                    loginViewModel = loginViewModel,
                    navController = navController,
                    mainNavHostController = mainNavController
                )
            }
        }

        if (currentRoute in showBottomNavScreens) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    MainBottomNavComponent(navController = navController)
                }
            }
        }
    }
}


@Composable
fun MainBottomNavComponent(navController: NavHostController) {
    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Timetable,
        BottomNavItem.Community,
        BottomNavItem.Schedule,
        BottomNavItem.UserProfile,
    )


    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Surface(
        modifier = Modifier

            .fillMaxWidth()
            .height(70.dp),

        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Background_Color2)
                .padding(bottom = 2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,

        ) {
            screens.forEach { screen ->
                AddBottomNavItem(screen = screen, currentDestination = currentDestination, navController = navController)
            }
        }
    }
}

@Composable
fun RowScope.AddBottomNavItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.screenRoute } == true
    val background = if (selected) Pastel_Red else Background_Color2
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .weight(1f)
            .padding(start = 12.dp, end = 12.dp, bottom = 10.dp)
            .height(35.dp)

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
            Image(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.title,
                modifier = Modifier
                    .size(28.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavPreview() {

    val mainNavController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    NextClassTheme {
        MainBottomNav(loginViewModel,mainNavController)

    }
}
