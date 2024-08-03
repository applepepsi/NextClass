package com.example.nextclass.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.CommunityCommentData
import com.example.nextclass.Data.CommunityPostData
import com.example.nextclass.Data.PostSchoolType
import com.example.nextclass.R
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.CommentComponent
import com.example.nextclass.appComponent.CommunityPostSchoolTypeComponent
import com.example.nextclass.appComponent.CommunityPostSortComponent
import com.example.nextclass.appComponent.CommunityTopNavComponent
import com.example.nextclass.appComponent.CommunityUserSettingComponent
import com.example.nextclass.appComponent.DividerComponent
import com.example.nextclass.appComponent.FloatingActionButtonComponent
import com.example.nextclass.appComponent.PostDetailComponent
import com.example.nextclass.appComponent.SinglePostComponent
import com.example.nextclass.appComponent.SortBottomSheetComponent
import com.example.nextclass.nav.CommunityGraph
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.CommunityViewModel
import com.example.nextclass.viewmodel.LoginViewModel
import java.time.LocalDateTime

@Composable
fun CommunityView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController,
    communityViewModel: CommunityViewModel
) {
    val context = LocalContext.current
    val communityNavController= rememberNavController()



    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

                .padding(top = 20.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AppBarTextAndButtonComponent(
                value = "게시판",
                navController = navController,
                showLeftButton = false,
                showRightButton = false,
                buttonText = "작성하기",
                navRoute = ""
            )

            Spacer(modifier = Modifier.height(10.dp))


            CommunityTopNavComponent(
                communityNavController
            )



            Column(
                modifier = Modifier
                    .background(Background_Color2)
            ) {
            Spacer(modifier = Modifier.height(10.dp))

            CommunityGraph(navController = navController, communityViewModel = communityViewModel, communityNavController = communityNavController)

            }
        }
        FloatingActionButtonComponent(
            navController=navController,
            navRoute = "insertPostView"
        )
    }
}

val testCommunityData= listOf(
    CommunityPostData(postName = "가나다", postDetail = "라마바사", postTime = LocalDateTime.now(), commentCount = 2, likeCount = 1),
    CommunityPostData(postName = "야야", postDetail = "ㅈㅇㅇㅂ", postTime = LocalDateTime.now(), commentCount = 3, likeCount = 2),
    CommunityPostData(postName = "아아", postDetail = "ㅈㅇㅂ", postTime = LocalDateTime.now(), commentCount = 4, likeCount = 3),
    CommunityPostData(postName = "바자", postDetail = "ㅈㅂㅈㅇ", postTime = LocalDateTime.now(), commentCount = 5, likeCount = 4),
    CommunityPostData(postName = "야야", postDetail = "ㅈㅇㅇㅂ", postTime = LocalDateTime.now(), commentCount = 3, likeCount = 2),
    CommunityPostData(postName = "아아", postDetail = "ㅈㅇㅂ", postTime = LocalDateTime.now(), commentCount = 4, likeCount = 3),
    CommunityPostData(postName = "바자", postDetail = "ㅈㅂㅈㅇ", postTime = LocalDateTime.now(), commentCount = 5, likeCount = 4),

    CommunityPostData(postName = "야야", postDetail = "ㅈㅇㅇㅂ", postTime = LocalDateTime.now(), commentCount = 3, likeCount = 2),
    CommunityPostData(postName = "아아", postDetail = "ㅈㅇㅂ", postTime = LocalDateTime.now(), commentCount = 4, likeCount = 3),
    CommunityPostData(postName = "바자", postDetail = "ㅈㅂㅈㅇ", postTime = LocalDateTime.now(), commentCount = 5, likeCount = 4),

    CommunityPostData(postName = "야야", postDetail = "ㅈㅇㅇㅂ", postTime = LocalDateTime.now(), commentCount = 3, likeCount = 2),
    CommunityPostData(postName = "아아", postDetail = "ㅈㅇㅂ", postTime = LocalDateTime.now(), commentCount = 4, likeCount = 3),
    CommunityPostData(postName = "바자", postDetail = "ㅈㅂㅈㅇ", postTime = LocalDateTime.now(), commentCount = 5, likeCount = 4),
    )


val testCommentList= listOf(
    CommunityCommentData(commentDetail = "가나다라", commentLikeCount = 2, commentTime = LocalDateTime.now()),
    CommunityCommentData(commentDetail = "가나qwfqw다라", commentLikeCount = 2, commentTime = LocalDateTime.now()),
    CommunityCommentData(commentDetail = "가나다wd라", commentLikeCount = 2, commentTime = LocalDateTime.now()),
    CommunityCommentData(commentDetail = "가나qwfqwf다라", commentLikeCount = 2, commentTime = LocalDateTime.now()),
    CommunityCommentData(commentDetail = "가qwfqwf나다라", commentLikeCount = 3, commentTime = LocalDateTime.now()),
    CommunityCommentData(commentDetail = "가qwfqwf나다라", commentLikeCount = 3, commentTime = LocalDateTime.now()),
    CommunityCommentData(commentDetail = "가qwfqwf나다라", commentLikeCount = 3, commentTime = LocalDateTime.now()),
    CommunityCommentData(commentDetail = "가qwfqwf나다라", commentLikeCount = 3, commentTime = LocalDateTime.now()),
    CommunityCommentData(commentDetail = "가qwfqwf나다라", commentLikeCount = 3, commentTime = LocalDateTime.now()),
    CommunityCommentData(commentDetail = "가qwfqwf나다라", commentLikeCount = 3, commentTime = LocalDateTime.now()),
    )

@Composable
fun PostDetailView(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController,
    communityViewModel: CommunityViewModel
) {
    val context = LocalContext.current

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),


            ){
            AppBarTextAndButtonComponent(
                value = "",
                navController = navController,
                showLeftButton = true,
                showRightButton = false,
            )

        }

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PostDetailComponent(
                selectPost=communityViewModel.selectCommunityData.value,
                deletePost = { communityViewModel.deletePost() },
                modifyPost = {
                    navController.navigate("modifyPostView")
                },
                likePost = { communityViewModel.likePost() },
            )

            LazyColumn {

                items(items = testCommentList){singleCommentData->
                    CommentComponent(
                        singleCommentData,
                        modifyComment = { communityViewModel.modifyComment(singleCommentData) },
                        deleteComment = { communityViewModel.deleteComment(singleCommentData) },
                        likeComment = { communityViewModel.likeComment(singleCommentData) }
                    )
                }
            }
        }
    }


}


@Composable
fun AllSchoolPostView(
    communityViewModel: CommunityViewModel,
    navController: NavController,
    communityNavController: NavController
) {

    LazyColumn(
        modifier = Modifier
    ) {

        item{
            Text(
                text="모든학교"
            )
        }

        items(items = testCommunityData) { singlePostData ->

            SinglePostComponent(
                singlePostData,
                postClick = {
                    communityViewModel.setSelectedCommunityData(singlePostData)
                    navController.navigate("postDetailView")
                }
            )
        }
    }
}

@Composable
fun MySchoolPostView(
    communityViewModel: CommunityViewModel,
    navController: NavController,
    communityNavController: NavController
) {


    LazyColumn(
        modifier = Modifier
    ) {

        item{
            Text(
                text="내학교"
            )
        }
        items(items = testCommunityData) { singlePostData ->

            SinglePostComponent(
                singlePostData,
                postClick = {
                    communityViewModel.setSelectedCommunityData(singlePostData)
                    navController.navigate("postDetailView")
                }
            )
        }
    }
}

@Composable
fun MyPostView(
    communityViewModel: CommunityViewModel,
    navController: NavController,
    communityNavController: NavController
) {

    LazyColumn(
        modifier = Modifier
    ) {
        item{
            Text(
                text="내 게시물"
            )
        }
        items(items = testCommunityData) { singlePostData ->

            SinglePostComponent(
                singlePostData,
                postClick = {
                    communityViewModel.setSelectedCommunityData(singlePostData)
                    navController.navigate("postDetailView")
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CommunityViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()


    val loginViewModel=LoginViewModel(testRepository)
    val communityViewModel=CommunityViewModel()

    NextClassTheme {
        CommunityView(
            navController = navController,
            loginViewModel = loginViewModel,
            mainNavController = mainNavController,
            communityViewModel = communityViewModel
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PostDetailPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()


    val loginViewModel=LoginViewModel(testRepository)
    val communityViewModel=CommunityViewModel()

    NextClassTheme {
        PostDetailView(
            navController = navController,
            loginViewModel = loginViewModel,
            mainNavController = mainNavController,
            communityViewModel = communityViewModel
        )
    }
}
