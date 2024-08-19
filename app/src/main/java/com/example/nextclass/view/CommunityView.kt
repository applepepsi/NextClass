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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
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
import com.example.nextclass.appComponent.CheckboxComponent
import com.example.nextclass.appComponent.CommentComponent
import com.example.nextclass.appComponent.CommunityTopNavComponent
import com.example.nextclass.appComponent.FloatingActionButtonComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.InsertCommentComponent
import com.example.nextclass.appComponent.InsertPostContentBox
import com.example.nextclass.appComponent.InsertPostSubjectBox
import com.example.nextclass.appComponent.PostDetailComponent
import com.example.nextclass.appComponent.ProgressBarComponent
import com.example.nextclass.appComponent.SinglePostComponent
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.repository.testRepo.CommunityTestRepository
import com.example.nextclass.repository.testRepo.TestRepository
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
    CommunityPostData(subject = "가나다", content = "라마바사", reg_date = LocalDateTime.now().toString(), comment_count = 2, vote_count = 1),
    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 3, vote_count = 2),
    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 4, vote_count = 3),
    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now().toString(), comment_count = 5, vote_count = 4),
    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 3, vote_count = 2),
    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 4, vote_count = 3),
    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now().toString(), comment_count = 5, vote_count = 4),

    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 3, vote_count = 2),
    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 4, vote_count = 3),
    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now().toString(), comment_count = 5, vote_count = 4),

    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 3, vote_count = 2),
    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 4, vote_count = 3),
    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now().toString(), comment_count = 5, vote_count = 4),
    )


val testCommentList= listOf(
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
    )

@Composable
fun PostDetailView(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController,
    communityViewModel: CommunityViewModel
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()

    if(communityViewModel.postDeleteResultState.value){
        navController.navigateUp()
        communityViewModel.togglePostDeleteState()
    }

    //처음 댓글 가져오기
    LaunchedEffect(Unit) {
        communityViewModel.resetCommentList()
        communityViewModel.toggleMoreCommentLoadState()
        communityViewModel.getCommentList(
            post_sequence =communityViewModel.selectCommunityData.value.post_sequence,
            comment_sequence = null
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        if (communityViewModel.loading.value) {
            ProgressBarComponent(state = communityViewModel.loading.value)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
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
                .weight(1f)
//                .fillMaxSize()
        ) {
            PostDetailComponent(
                selectPost = communityViewModel.selectCommunityData.value,
                deletePost = { communityViewModel.deletePost() },
                modifyPost = {
                    communityViewModel.setPostModifyData()
                    navController.navigate("modifyPostView")
                },
                likePost = { communityViewModel.likePost() },
                postOwner = communityViewModel.selectCommunityData.value.owner
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                state = listState,

            ) {
                items(items = communityViewModel.communityCommentDataList.value) { singleCommentData ->
                    CommentComponent(
                        singleCommentData,
                        modifyComment = { communityViewModel.modifyComment(singleCommentData) },
                        deleteComment = { communityViewModel.deleteComment(singleCommentData) },
                        likeComment = { communityViewModel.likeComment(singleCommentData) },
                        optionVisible = true,

                    )
                }
            }
        }


        InsertCommentComponent(
            communityViewModel = communityViewModel,
            onValueChange = { communityViewModel.updateCommentContent(it) },
            text = communityViewModel.commentWriteData.value.content,
            isSecret = communityViewModel.commentWriteData.value.is_secret,
            toggleCommentSecretState = { communityViewModel.toggleCommentSecretState() },
            postSequence=communityViewModel.selectCommunityData.value.post_sequence
        )
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                if(communityViewModel.moreCommentLoadState.value){
                    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
                    communityViewModel.loadMoreCommentCheck(lastVisibleItemIndex,)
                }
            }
    }
}




@Composable
fun AllSchoolPostView(
    communityViewModel: CommunityViewModel,

    communityNavController: NavController,
    navController: NavHostController
) {
    val listState = rememberLazyListState()

//    val pullRefreshState = rememberPullRefreshState(
//        refreshing = true or false
//                onRefresh = refresh
//    )


    LaunchedEffect(Unit) {
        communityViewModel.resetPostList()
        communityViewModel.toggleMorePostLoadState()
        communityViewModel.getPostList(sort="all",post_sequence=null)
    }



    if(communityViewModel.getPostDetailResultState.value){
        navController.navigate("postDetailView")
        communityViewModel.togglePostDetailState()
    }

    // LazyColumn 설정
    LazyColumn(
        state = listState,
        modifier = Modifier
            .padding(bottom = 75.dp)
    ) {
        item {
            Log.d("communityViewModel.loading",communityViewModel.loading.value.toString())

            if (communityViewModel.loading.value) {
                ProgressBarComponent(state = communityViewModel.loading.value)
            }
        }


        items(items = communityViewModel.communityDataList.value) { singlePostData ->

            SinglePostComponent(
                singlePostData,
                postClick = {
                    communityViewModel.setSelectedCommunityData(singlePostData.post_sequence)

                }
            )
        }
    }

    //todo 테스트 해봐야함
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                //현재 보이는 아이템의 마지막 인덱스
                if(communityViewModel.morePostLoadState.value){
                    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
                    communityViewModel.loadMorePostsCheck(lastVisibleItemIndex, sortType = "all")
                }
            }
    }
}

@Composable
fun BestPostView(
    communityViewModel: CommunityViewModel,

    communityNavController: NavController,
    navController: NavHostController
) {

    LazyColumn(
        modifier = Modifier
    ) {


        items(items = testCommunityData) { singlePostData ->

            SinglePostComponent(
                singlePostData,
                postClick = {
                    communityViewModel.setSelectedCommunityData(singlePostData.post_sequence)

                }
            )
        }
    }
}

@Composable
fun MySchoolPostView(
    communityViewModel: CommunityViewModel,

    communityNavController: NavController,
    navController: NavHostController
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
                    communityViewModel.setSelectedCommunityData(singlePostData.post_sequence)

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
                        communityViewModel.setSelectedCommunityData(singlePostData.post_sequence)
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
    val communityTestRepository=CommunityTestRepository()
    val communityViewModel=CommunityViewModel(communityTestRepository)

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
    val communityTestRepository=CommunityTestRepository()

    val loginViewModel=LoginViewModel(testRepository)
    val communityViewModel=CommunityViewModel(communityTestRepository)

    NextClassTheme {
        PostDetailView(
            navController = navController,
            loginViewModel = loginViewModel,
            mainNavController = mainNavController,
            communityViewModel = communityViewModel
        )
    }
}

@Composable
fun InsertOrModifyPostComponent(
    navController: NavController,
    communityViewModel: CommunityViewModel,
    loginViewModel: LoginViewModel,
    postType:()->Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    if(communityViewModel.postResultState.value){
        navController.navigateUp()
        communityViewModel.togglePostResultState()
        communityViewModel.resetWritePostData()
    }



    Column(
        modifier = Modifier

            .fillMaxSize(),

        ) {

        Row(
            modifier = Modifier
                .padding(top = 20.dp),

            ) {
            AppBarTextAndButtonComponent(
                value = "작성 하기",
                navController = navController,
                showLeftButton = true,
                showRightButton = false,
            )
        }



        Column(

            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
        ) {


            Spacer(modifier = Modifier.padding(top = 8.dp))

            Surface(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)

                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    modifier = Modifier
                        .background(Background_Color2),
//                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Spacer(modifier = Modifier.height(25.dp))

                    InsertPostSubjectBox(
                        text = communityViewModel.postWriteData.value.subject,
                        onValueChange = {communityViewModel.updateSubject(it)}
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    InsertPostContentBox(
                        text = communityViewModel.postWriteData.value.content,
                        onValueChange = {communityViewModel.updateContent(it)}
                    )

                    CheckboxComponent(
                        checked = communityViewModel.postWriteData.value.is_secret,
                        onClickCheckBox = { communityViewModel.togglePostSecretState() },
                        checkBoxTextComponent = {
                            Text(
                                text = "익명"
                            )
                        })

                    TextInputHelpFieldComponent(
                        errorMessage = communityViewModel.postErrorMessage.value.asString(
                            LocalContext.current
                        ),
                        isError = communityViewModel.postErrorState.value,
                    )

                    Spacer(modifier = Modifier.padding(top = 5.dp))

                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {


                        InputButtonComponent(
                            value = "작성 하기",
                            onClick = { postType() },
                            modifier = Modifier
                                .padding(start = 30.dp, end = 30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }
            }
        }
    }
}