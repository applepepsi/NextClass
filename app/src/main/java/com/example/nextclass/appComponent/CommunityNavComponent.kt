package com.example.nextclass.appComponent

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
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
import com.example.nextclass.nav.CommunityGraph
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.viewmodel.CommunityViewModel



@Composable
fun CommunityTopNavComponent(navController: NavHostController,communityViewModel:CommunityViewModel,communityNavController:NavHostController) {
    val screens = listOf(
        CommunityTopNavItem.AllSchool,
        CommunityTopNavItem.MySchool,
        CommunityTopNavItem.MyPost,
    )


    val currentDestination = communityNavController.currentBackStackEntryAsState().value?.destination

    Column {
        Spacer(modifier = Modifier.height(15.dp))

        Surface(modifier = Modifier
            .fillMaxWidth())

        {

            Row(modifier = Modifier
//            .background(Color.White)
            ) {



                screens.forEach { screen ->
                    AddCommunityTopNavItem(screen = screen, currentDestination = currentDestination, communityNavController = communityNavController)
                }
            }
        }

        Column(
            modifier = Modifier.background(Background_Color2)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            CommunityGraph(navController = navController, communityViewModel = communityViewModel, communityNavController = communityNavController)

        }
    }
}

@Composable
fun RowScope.AddCommunityTopNavItem(
    screen: CommunityTopNavItem,
    currentDestination: NavDestination?,
    communityNavController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.screenRoute } == true
    val selectTextColor = if (selected) Color.Black else Color.LightGray
    val selectLabelColor=if (selected) Pastel_Red else Color.LightGray
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .height(35.dp)
            .weight(1f)
            .clickable(
                onClick = {
                    Log.d("선택된 루트", screen.screenRoute)
                    communityNavController.navigate(screen.screenRoute) {
                        popUpTo(communityNavController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                interactionSource = interactionSource,
                indication = null
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        Text(
            text = screen.title,
            style = TextStyle.Default.copy(color = selectTextColor, fontSize = 19.sp,fontWeight = FontWeight.Normal),
            modifier = Modifier
        )

        CommunityTopNavLabel(
            selectColor = selectLabelColor,
            modifier = Modifier.height(if (selected) 2.5.dp else 1.dp)

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
//            .padding(start = 15.dp, end = 15.dp)
            .background(selectColor)
            .height(2.5.dp)

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
        CommunityTopNavComponent(navController = mainNavController,communityViewModel, communityNavController = mainNavController)

    }
}