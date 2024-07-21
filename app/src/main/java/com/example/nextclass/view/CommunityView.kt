package com.example.nextclass.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.CommunityPostData
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.DividerComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.SinglePostComponent
import com.example.nextclass.repository.ScheduleTestRepository
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel
import java.time.LocalDateTime

@Composable
fun CommunityView(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        AppBarTextAndButtonComponent(
            value = "게시판",
            navController = navController,
            showLeftButton = false,
            showRightButton = false,
        )


        LazyColumn {



            items(items = testCommunityData){singlePostData->


                SinglePostComponent(
                    postTitle=singlePostData.postName,
                    postDetail = singlePostData.postDetail,
                    postTime = singlePostData.postTime,
                    commentCount = singlePostData.commentCount,
                    likeCount = singlePostData.likeCount,
                )

            }
        }

    }

}

val testCommunityData= listOf(
    CommunityPostData(postName = "가나다", postDetail = "라마바사", postTime = LocalDateTime.now(), commentCount = 2, likeCount = 1),
    CommunityPostData(postName = "야야", postDetail = "ㅈㅇㅇㅂ", postTime = LocalDateTime.now(), commentCount = 3, likeCount = 2),
    CommunityPostData(postName = "아아", postDetail = "ㅈㅇㅂ", postTime = LocalDateTime.now(), commentCount = 4, likeCount = 3),
    CommunityPostData(postName = "바자", postDetail = "ㅈㅂㅈㅇ", postTime = LocalDateTime.now(), commentCount = 5, likeCount = 4),
)

@Preview(showBackground = true)
@Composable
fun CommunityViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()


    val loginViewModel=LoginViewModel(testRepository)

    NextClassTheme {
        CommunityView(navController = navController, loginViewModel = loginViewModel, mainNavController = mainNavController)
    }
}
