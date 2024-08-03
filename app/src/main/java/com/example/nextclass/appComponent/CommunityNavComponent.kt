package com.example.nextclass.appComponent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.items.CommunityTopNavItem
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.CommunityViewModel



@Composable
fun CommunityTopNavComponent(navController: NavHostController) {
    val screens = listOf(
        CommunityTopNavItem.AllSchool,
        CommunityTopNavItem.MySchool,
        CommunityTopNavItem.MyPost,
    )


    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Surface(modifier = Modifier
        .fillMaxWidth()) {
        Row(modifier = Modifier
//            .background(Color.White)
        ) {
            screens.forEach { screen ->
                AddCommunityTopNavItem(screen = screen, currentDestination = currentDestination, navController = navController)
            }
        }
    }
}

@Composable
fun RowScope.AddCommunityTopNavItem(
    screen: CommunityTopNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.screenRoute } == true
    val selectColor = if (selected) Color.Black else Color.Gray

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .weight(1f)
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
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = screen.title,
            style = TextStyle.Default.copy(color = selectColor, fontSize = 15.sp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        CommunityTopNavLabel(
            selectColor = selectColor,
            modifier = Modifier.height(if (selected) 2.dp else 0.7.dp)
        )

    }
}

@Composable
fun CommunityTopNavLabel(
    selectColor: Color,
    modifier: Modifier
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .background(selectColor)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CommunityTopNavPreview() {

    val mainNavController= rememberNavController()
    val testRepository = TestRepository()
    val communityViewModel = CommunityViewModel()

    NextClassTheme {
        CommunityTopNavComponent(navController = mainNavController)

    }
}