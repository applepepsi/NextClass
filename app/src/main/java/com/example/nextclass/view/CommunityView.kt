package com.example.nextclass.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.CheckboxComponent
import com.example.nextclass.appComponent.CommentComponent
import com.example.nextclass.appComponent.CommunitySearchBox
import com.example.nextclass.appComponent.CommunitySortTypeDownMenuComponent
import com.example.nextclass.appComponent.CommunityTopNavComponent
import com.example.nextclass.appComponent.DividerComponent
import com.example.nextclass.appComponent.FloatingActionButtonComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.InsertCommentComponent
import com.example.nextclass.appComponent.InsertPostContentBox
import com.example.nextclass.appComponent.InsertPostSubjectBox
import com.example.nextclass.appComponent.PostDetailComponent
import com.example.nextclass.appComponent.ProgressBarFullComponent
import com.example.nextclass.appComponent.ProgressBarSmallComponent
import com.example.nextclass.appComponent.RecentSearchWordComponent
import com.example.nextclass.appComponent.SinglePostComponent
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.repository.testRepo.CommunityTestRepository
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.utils.RecentSearchWordManager
import com.example.nextclass.viewmodel.CommunityViewModel
import com.example.nextclass.viewmodel.LoginViewModel

@SuppressLint("RememberReturnType")
@Composable
fun CommunityView(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController,
    communityViewModel: CommunityViewModel,

) {
    val context = LocalContext.current
    val communityNavController= rememberNavController()
    val currentRoute = remember { mutableStateOf("") }
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
                    customRightButton = true,
                    customRightButtonIcon = Icons.Default.Search,
                    navRoute = "communitySearchView"
                )

            Spacer(modifier = Modifier.height(10.dp))



            CommunityTopNavComponent(
                navController=navController,
                communityViewModel=communityViewModel,
                communityNavController=communityNavController,

            )


        }
        if(communityViewModel.currentRoute.value!="BEST_POST"){
            FloatingActionButtonComponent(
                navController=navController,
                navRoute = "insertPostView",
            )
        }
    }
}

//val testCommunityData= listOf(
//    CommunityPostData(subject = "가나다", content = "라마바사", reg_date = LocalDateTime.now().toString(), comment_count = 2, vote_count = 1),
//    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 3, vote_count = 2),
//    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 4, vote_count = 3),
//    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now().toString(), comment_count = 5, vote_count = 4),
//    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 3, vote_count = 2),
//    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 4, vote_count = 3),
//    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now().toString(), comment_count = 5, vote_count = 4),
//
//    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 3, vote_count = 2),
//    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 4, vote_count = 3),
//    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now().toString(), comment_count = 5, vote_count = 4),
//
//    CommunityPostData(subject = "야야", content = "ㅈㅇㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 3, vote_count = 2),
//    CommunityPostData(subject = "아아", content = "ㅈㅇㅂ", reg_date = LocalDateTime.now().toString(), comment_count = 4, vote_count = 3),
//    CommunityPostData(subject = "바자", content = "ㅈㅂㅈㅇ", reg_date = LocalDateTime.now().toString(), comment_count = 5, vote_count = 4),
//    )
//
//
//val testCommentList= listOf(
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    CommunityCommentData(content = "가나다라", vote_count = 2, reg_date = LocalDateTime.now().toString()),
//    )

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
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (communityViewModel.loading.value) {
            ProgressBarFullComponent(state = communityViewModel.loading.value)
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
                .weight(1f),

            horizontalAlignment = Alignment.CenterHorizontally
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

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = communityViewModel.communityCommentDataList.value) { singleCommentData ->
                    CommentComponent(
                        singleCommentData,
                        modifyComment = { communityViewModel.modifyComment(singleCommentData) },
                        deleteComment = { communityViewModel.deleteComment(singleCommentData) },
                        likeComment = { communityViewModel.likeComment(singleCommentData) },
                        optionVisible = true,
                    )
                DividerComponent(modifier = Modifier
                    .height(0.5.dp),
                    dividerColor = Color.LightGray
                ) }

                item{
                    if(communityViewModel.commentLoading.value){
                        ProgressBarSmallComponent(communityViewModel.commentLoading.value)
                    }
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
fun CommunityPostView(
    communityViewModel: CommunityViewModel,
    navController: NavHostController,
    sortType: String,
    padding: Boolean=true
) {
    val listState = rememberLazyListState()


    LaunchedEffect(Unit) {
        communityViewModel.resetPostList()
        communityViewModel.toggleMorePostLoadState()
        communityViewModel.getPostList(sort = sortType, post_sequence = null)
    }

    if (communityViewModel.getPostDetailResultState.value) {
        navController.navigate("postDetailView")
        communityViewModel.togglePostDetailState()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(
                if (!padding) Modifier.padding(top = 10.dp) else Modifier
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (communityViewModel.loading.value) {
            ProgressBarFullComponent(state = communityViewModel.loading.value)
        }

        LazyColumn(
            state = listState,
            modifier =  if (padding) Modifier.padding(bottom = 75.dp) else Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = communityViewModel.communityDataList.value) { singlePostData ->
                SinglePostComponent(
                    singlePostData,
                    postClick = {
                        communityViewModel.setSelectedCommunityData(singlePostData.post_sequence)
                    }
                )
            }
            item {
                if (communityViewModel.postLoading.value) {
                    ProgressBarSmallComponent(communityViewModel.postLoading.value)
                }
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                if (communityViewModel.morePostLoadState.value) {
                    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
                    communityViewModel.loadMorePostsCheck(lastVisibleItemIndex, sortType = sortType)
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
    CommunityPostView(
        communityViewModel = communityViewModel,
        navController = navController,
        sortType = "all",
        padding = true
    )
}

@Composable
fun BestPostView(
    communityViewModel: CommunityViewModel,

    communityNavController: NavController,
    navController: NavHostController
) {
    CommunityPostView(
        communityViewModel = communityViewModel,
        navController = navController,
        sortType = "vote",
        padding = true
    )
}

@Composable
fun MySchoolPostView(
    communityViewModel: CommunityViewModel,

    communityNavController: NavController,
    navController: NavHostController
) {
    CommunityPostView(
        communityViewModel = communityViewModel,
        navController = navController,
        sortType = "my_school",
        padding = true
    )
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

    ) {
        AppBarTextAndButtonComponent(
            value = "작성한 댓글",
            navController = navController,
            showLeftButton = true,
            showRightButton = false,
            buttonText = "작성하기",
            customRightButton = false,
            customRightButtonIcon = Icons.Default.Search,
            navRoute = "communitySearchView"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            Modifier.background(Background_Color2)
        ) {

            CommunityPostView(
                communityViewModel = communityViewModel,
                navController = navController,
                sortType = "my_comment",
                padding=false
            )
        }

    }
}

@Composable
fun MyFavoritePostView(
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
            value = "추천한 게시물",
            navController = navController,
            showLeftButton = true,
            showRightButton = false,
            buttonText = "작성하기",
            customRightButton = false,
            customRightButtonIcon = Icons.Default.Search,
            navRoute = "communitySearchView"
        )


        Spacer(modifier = Modifier.height(10.dp))

        Column(
            Modifier.background(Background_Color2)
        ) {

            CommunityPostView(
                communityViewModel = communityViewModel,
                navController = navController,
                sortType = "my_vote",
                padding=false
            )
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
            value = "작성한 게시물",
            navController = navController,
            showLeftButton = true,
            showRightButton = false,
            buttonText = "작성하기",
            customRightButton = false,
            customRightButtonIcon = Icons.Default.Search,
            navRoute = "communitySearchView"
        )


        Spacer(modifier = Modifier.height(10.dp))
        
        Column(
            Modifier.background(Background_Color2)
        ) {

            CommunityPostView(
                communityViewModel = communityViewModel,
                navController = navController,
                sortType = "my_post",
                padding=false
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
        Spacer(modifier = Modifier.height(20.dp))
        Column(

            modifier = Modifier
                .fillMaxSize()
//                .padding(start = 5.dp, end = 5.dp,top=10.dp)
                .verticalScroll(scrollState)
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                .background(Background_Color2),

        ) {


//
                    Spacer(modifier = Modifier.height(20.dp))

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

                    Spacer(modifier = Modifier.padding(top = 10.dp))


        }
    }
}
val communitySortType = listOf("모든 게시물","내 학교","베스트 게시물")
@Composable
fun CommunitySearchView(
    communityViewModel: CommunityViewModel,
    navController: NavHostController,

) {
    val listState = rememberLazyListState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
//        communityViewModel.resetPostList()
        communityViewModel.toggleMorePostLoadState()
        communityViewModel.setRecentSearchList(RecentSearchWordManager.loadRecentSearchList(context))
    }

    if (communityViewModel.getPostDetailResultState.value) {
        navController.navigate("postDetailView")
        communityViewModel.togglePostDetailState()
    }

    Column(
        modifier = Modifier.fillMaxSize(),

//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(top = 20.dp),

            ) {
            AppBarTextAndButtonComponent(
                value = "검색",
                navController = navController,
                showLeftButton = true,
                showRightButton = false,
            )
        }
        Spacer(modifier = Modifier.height(25.dp))

        if (communityViewModel.loading.value) {
            ProgressBarFullComponent(state = communityViewModel.loading.value)
        }

        CommunitySortTypeDownMenuComponent(
            onValueChange = {communityViewModel.updateSearchType(it)},
            value = communityViewModel.searchData.value.sort,
            dropDownMenuOption = communityViewModel.searchSortTypeDropDownMenuState.value,
            menuItems = communitySortType,
            dropDownMenuName = "게시판 종류",
            toggleDropDownMenuOption = {communityViewModel.toggleSearchSortTypeDropDownMenuState()}
        )

        Spacer(modifier = Modifier.height(10.dp))
        CommunitySearchBox(
            text=communityViewModel.searchData.value.search_word,
            onValueChange ={ communityViewModel.updateSearchWord(it) } ,
            search = {
                communityViewModel.postSearchText()
                communityViewModel.setRecentSearchList(RecentSearchWordManager.saveSearchText(context = context,communityViewModel.searchData.value.search_word))
            },
            deleteInputText = { communityViewModel.resetSearchWord() }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text="최근 검색어",
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            ),
            modifier = Modifier.padding(start=7.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(top=5.dp,bottom=12.dp,end=5.dp,start=7.dp),
        ){

            items(items = communityViewModel.recentSearchList.value) { searchWord ->
                if(searchWord!=""){
                    RecentSearchWordComponent(
                        text=searchWord,
                        deleteSearchWord = {
                            communityViewModel.setRecentSearchList(RecentSearchWordManager.deleteRecentSearchText(context = context,searchWord))
                        },
                        search = {
                            communityViewModel.updateSearchWord(searchWord)
                            communityViewModel.postSearchText()

                        }
                    )
                }else{
                    Spacer(modifier = Modifier.height(35.dp))
                }
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(Background_Color2)
                .padding(top=8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            items(items = communityViewModel.communitySearchDataList.value) { singlePostData ->
                SinglePostComponent(
                    singlePostData,
                    postClick = {
                        communityViewModel.setSelectedCommunityData(singlePostData.post_sequence)
                    }
                )
            }
            item {
                if (communityViewModel.postLoading.value) {
                    ProgressBarSmallComponent(communityViewModel.postLoading.value)
                }
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                if (communityViewModel.morePostLoadState.value) {
                    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
                    communityViewModel.loadMorePostsCheck(lastVisibleItemIndex, sortType = communityViewModel.currentSortType.value,)
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun InsertOrModifyPostPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val communityTestRepository=CommunityTestRepository()

    val loginViewModel=LoginViewModel(testRepository)
    val communityViewModel=CommunityViewModel(communityTestRepository)

    NextClassTheme {
        InsertOrModifyPostComponent(navController = navController, communityViewModel = communityViewModel, loginViewModel = loginViewModel) {
            
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val communityTestRepository=CommunityTestRepository()

    val loginViewModel=LoginViewModel(testRepository)
    val communityViewModel=CommunityViewModel(communityTestRepository)

    NextClassTheme {
        CommunitySearchView(navController = navController, communityViewModel = communityViewModel,)
    }
}