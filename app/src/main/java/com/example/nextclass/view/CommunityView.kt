package com.example.nextclass.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.CommunityData.CommunityCommentData
import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.CommentComponent
import com.example.nextclass.appComponent.CommunityTopNavComponent
import com.example.nextclass.appComponent.FloatingActionButtonComponent
import com.example.nextclass.appComponent.PostDetailComponent
import com.example.nextclass.appComponent.SinglePostComponent
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.CommunityViewModel
import com.example.nextclass.viewmodel.LoginViewModel
import java.time.LocalDateTime

@Composable
fun CommunityView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController,
    communityViewModel: CommunityViewModel,

) {
    val context = LocalContext.current
    val communityNavController= rememberNavController()

//todo 테스트 해야함

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
                navController=navController,
                communityViewModel=communityViewModel,
                communityNavController=communityNavController
            )

        }
        FloatingActionButtonComponent(
            navController=navController,
            navRoute = "insertPostView"
        )
    }
}

val testCommunityData= listOf(
    CommunityPostData(subject = "가나다", content = "라마바사", reg_date = LocalDateTime.now(), comment_count = 2, vote_count = 1),
    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now(), comment_count = 3, vote_count = 2),
    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now(), comment_count = 4, vote_count = 3),
    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now(), comment_count = 5, vote_count = 4),
    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now(), comment_count = 3, vote_count = 2),
    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now(), comment_count = 4, vote_count = 3),
    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now(), comment_count = 5, vote_count = 4),

    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now(), comment_count = 3, vote_count = 2),
    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now(), comment_count = 4, vote_count = 3),
    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now(), comment_count = 5, vote_count = 4),

    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now(), comment_count = 3, vote_count = 2),
    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now(), comment_count = 4, vote_count = 3),
    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now(), comment_count = 5, vote_count = 4),
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
                        likeComment = { communityViewModel.likeComment(singleCommentData) },
                        optionVisible = false
                    )
                }
            }
        }
    }


}


@Composable
fun AllSchoolPostView(
    communityViewModel: CommunityViewModel,

    communityNavController: NavController
) {


    LazyColumn(
        modifier = Modifier
    ) {


        items(items = testCommunityData) { singlePostData ->

            SinglePostComponent(
                singlePostData,
                postClick = {
                    communityViewModel.setSelectedCommunityData(singlePostData)

                }
            )
        }
    }
}


@Composable
fun BestPostView(
    communityViewModel: CommunityViewModel,

    communityNavController: NavController
) {

    LazyColumn(
        modifier = Modifier
    ) {


        items(items = testCommunityData) { singlePostData ->

            SinglePostComponent(
                singlePostData,
                postClick = {
                    communityViewModel.setSelectedCommunityData(singlePostData)

                }
            )
        }
    }
}

@Composable
fun MySchoolPostView(
    communityViewModel: CommunityViewModel,

    communityNavController: NavController
) {


    LazyColumn(
        modifier = Modifier
    ) {

//        item{
//            Text(
//                text="내학교"
//            )
//        }
        items(items = testCommunityData) { singlePostData ->

            SinglePostComponent(
                singlePostData,
                postClick = {
                    communityViewModel.setSelectedCommunityData(singlePostData)

                }
            )
        }
    }
}

@Composable
fun MyCommentView(
    communityViewModel: CommunityViewModel,
    navController: NavHostController,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        AppBarTextAndButtonComponent(
            value = "회원 탈퇴",
            navController=navController)

        Spacer(modifier = Modifier.height(15.dp))

    LazyColumn(
        modifier = Modifier
    ) {


        items(items = testCommentList) { singleCommentData ->
            //내가 쓴 댓글을 터치하면 해당 게시물로 이동
            CommentComponent(
                singleCommentData = singleCommentData,

                commentClick = {
                    communityViewModel.setSelectCommentData(singleCommentData)
                },
                optionVisible = false,
            )
        }

        }
    }
}

@Composable
fun MyPostView(
    communityViewModel: CommunityViewModel,
    navController: NavHostController,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AppBarTextAndButtonComponent(
            value = "회원 탈퇴",
            navController = navController
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            modifier = Modifier
        ) {

//        item{
//            PostOptionDropDownMenu(
//                value = communityViewModel.myPostFilter.value,
//                onValueChange = { communityViewModel.updateMyPostTypeDropDownText(it) },
//                dropDownMenuOption = communityViewModel.toggleMyPostTypeState.value,
//                toggleDropDownMenuOption = { communityViewModel.toggleMyPostTypeType() },
//                menuItems = selectPostType
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//        }


            items(items = testCommunityData) { singlePostData ->

                SinglePostComponent(
                    singlePostData,
                    postClick = {
                        communityViewModel.setSelectedCommunityData(singlePostData)
//                    navController.navigate("postDetailView")
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CommunityViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()

    val communityNavController= rememberNavController()
    val loginViewModel=LoginViewModel(testRepository)
    val communityViewModel=CommunityViewModel()

    NextClassTheme {
        CommunityView(
            navController = navController,
            loginViewModel = loginViewModel,
            mainNavController = mainNavController,
            communityViewModel = communityViewModel,

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
