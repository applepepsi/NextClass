package com.example.nextclass.appComponent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.CommunityCommentData
import com.example.nextclass.Data.CommunityPostData
import com.example.nextclass.Data.PostSchoolType
import com.example.nextclass.R
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.utils.TimeFormatter
import com.example.nextclass.utils.TokenManager
import com.example.nextclass.utils.UserInfoManager
import com.example.nextclass.viewmodel.CommunityViewModel
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel
import java.time.LocalDateTime


@Composable
fun InsertOrModifyPostComponent(
    navController: NavController,
    communityViewModel: CommunityViewModel,
    loginViewModel: LoginViewModel,
    postType:()->Unit
) {
    val context = LocalContext.current

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
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
        ) {


            Spacer(modifier = Modifier.padding(top = 15.dp))

            Surface(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    modifier = Modifier
                        .background(Background_Color2),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {



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

                    Spacer(modifier = Modifier.padding(top = 40.dp))
                }
            }
        }
    }
}
@Composable
fun SinglePostComponent(
    singlePostData:CommunityPostData=CommunityPostData(postName = "NameTest", postDetail = "DetailTest"),
    postClick:()->Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()

            .padding(start = 5.dp, end = 5.dp, bottom = 7.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable {
                postClick()
            },
        verticalArrangement = Arrangement.SpaceEvenly
    ){
//        DividerComponent(modifier = Modifier
//            .height(0.5.dp),
//            dividerColor = Color.LightGray
//        )

        Column(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp)
                .fillMaxWidth(),
//            verticalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text=singlePostData.postName,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text=singlePostData.postDetail,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top=3.dp)

            )
        }
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(start = 2.dp),

                ){
                Icon(
                    modifier = Modifier
                        .size(17.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = ImageVector.vectorResource(R.drawable.schedule_icon),
                    contentDescription = "",
                    tint =  Color.Black,
                )

                Text(
                    modifier = Modifier
                        .padding(start=3.dp),
                    text=TimeFormatter.formatDate(singlePostData.postTime),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    ),
                )
            }

            Row(
                modifier = Modifier
                    .padding(end=5.dp),
                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.CenterVertically),
                        imageVector = ImageVector.vectorResource(R.drawable.chat_icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )

                    Text(
                        modifier = Modifier
                            .padding(start=3.dp),
                        text = singlePostData.commentCount.toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                }

                Spacer(modifier = Modifier.width(3.dp))

                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(12.dp)
                            .align(Alignment.CenterVertically),
                        imageVector = ImageVector.vectorResource(R.drawable.heart_icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )

                    Text(
                        modifier = Modifier
                            .padding(start=3.dp),
                        text = singlePostData.likeCount.toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                }
            }
        }
//        DividerComponent(modifier = Modifier
//            .height(0.5.dp),
//            dividerColor = Color.LightGray
//        )
    }


}

@Composable
fun PostDetailComponent(
    selectPost:CommunityPostData=CommunityPostData(),
    deletePost:()->Unit,
    likePost:()->Unit,
    modifyPost:()->Unit,
){

    Column(
        modifier = Modifier
            .fillMaxWidth(),

        ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){
            Text(
                modifier = Modifier
                    .clickable {
                        modifyPost()
                    }
                    .padding(5.dp),
                text = "수정",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
                color=Color.Gray
            )
            Text(
                modifier = Modifier
                    .clickable {
                        deletePost()
                    }
                    .padding(5.dp),
                text = "제거",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
                color=Color.Gray
            )
        }

        DividerComponent(modifier = Modifier
            .height(0.5.dp),
            dividerColor = Color.LightGray
        )
        Column(
            modifier = Modifier
                .padding(10.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = selectPost.postName,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    ),

                )
            }

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp)
            ){
                Text(
                    text=selectPost.postDetail,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    ),
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(start = 2.dp),

                ){
                Icon(
                    modifier = Modifier
                        .size(18.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = ImageVector.vectorResource(R.drawable.schedule_icon),
                    contentDescription = "",
                    tint =  Color.Black,
                )

                Text(
                    modifier = Modifier
                        .padding(start=3.dp),
                    text=TimeFormatter.formatDate(selectPost.postTime),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    ),
                )
            }

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically),
                        imageVector = ImageVector.vectorResource(R.drawable.chat_icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )

                    Text(
                        modifier = Modifier
                            .padding(start=3.dp),
                        text = selectPost.commentCount.toString(),
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            likePost()
                        },
                        modifier = Modifier.size(20.dp)
                    )
                    {
                        Icon(
                            modifier = Modifier
                                .size(17.dp)
                                .align(Alignment.CenterVertically),
                            imageVector = ImageVector.vectorResource(R.drawable.heart_icon),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(start=3.dp),
                        text = selectPost.likeCount.toString(),
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                }
            }
        }
        DividerComponent(modifier = Modifier
            .height(0.5.dp),
            dividerColor = Color.LightGray
        )
    }
}

@Composable
fun CommentComponent(
    singleCommentData: CommunityCommentData=CommunityCommentData(),
    modifyComment:()->Unit,
    deleteComment:()->Unit,
    likeComment:()->Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth(),

        ){
        DividerComponent(modifier = Modifier
            .height(0.5.dp),
            dividerColor = Color.LightGray
        )

        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "익명",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier
                        .clickable {
                            likeComment()
                        }
                        .padding(5.dp),
                    text = "추천",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ),
                    color=Color.Gray
                )

                Text(
                    modifier = Modifier
                        .clickable {
                            modifyComment()
                        }
                        .padding(5.dp),
                    text = "수정",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ),
                    color=Color.Gray
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            deleteComment()
                        }
                        .padding(5.dp),
                    text = "제거",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ),
                    color=Color.Gray
                )
            }

        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = singleCommentData.commentDetail,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(start = 2.dp),

                ){
                Icon(
                    modifier = Modifier
                        .size(15.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = ImageVector.vectorResource(R.drawable.schedule_icon),
                    contentDescription = "",
                    tint =  Color.Black,
                )

                Text(
                    modifier = Modifier
                        .padding(start=3.dp),
                    text=TimeFormatter.formatDate(singleCommentData.commentTime),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    ),
                )
            }

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(12.dp)
                            .align(Alignment.CenterVertically),
                        imageVector = ImageVector.vectorResource(R.drawable.heart_icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )

                    Text(
                        modifier = Modifier
                            .padding(start=3.dp),
                        text = singleCommentData.commentLikeCount.toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                }
            }
        }

        DividerComponent(modifier = Modifier
            .height(0.5.dp),
            dividerColor = Color.LightGray
        )
    }


}

@Composable
fun CommunityPostSchoolTypeComponent(
    togglePostSchoolType:()->Unit,
    postSchoolTypeState: Boolean

){
    val postSchoolType = if (postSchoolTypeState) {
        PostSchoolType(
            icon = ImageVector.vectorResource(R.drawable.all_school_icon),
            text = "모든 학교"
        )
    } else {
        PostSchoolType(
            icon = ImageVector.vectorResource(R.drawable.single_school_icon),
            text = "내 학교만"
        )
    }




        Column(modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                togglePostSchoolType()
            }
            .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                modifier = Modifier
                    .size(25.dp),
                imageVector = postSchoolType.icon, 
                contentDescription = "",
                tint = Color.Black,
                )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text=postSchoolType.text,
                style = TextStyle(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                ),
            )
        }
}

@Composable
fun CommunityUserSettingComponent(

){
    val postSchoolType =
        PostSchoolType(
            icon = ImageVector.vectorResource(R.drawable.user_profile_icon),
            text = "작성글 보기"
        )





    Column(modifier = Modifier
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {

        }
        .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            modifier = Modifier
                .size(28.dp),
            imageVector = postSchoolType.icon,
            contentDescription = "",
            tint = Color.Black,
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text=postSchoolType.text,
            style = TextStyle(
                fontSize = 9.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
        )
    }
}

@Composable
fun CommunityPostSortComponent(
    togglePostSortType:()->Unit,

){
    val postSchoolType = PostSchoolType(
            icon = ImageVector.vectorResource(R.drawable.sorting_icon),
            text = "게시물 정렬"
        )




    Column(modifier = Modifier
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            togglePostSortType()
        }
        .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            modifier = Modifier
                .size(25.dp),
            imageVector = postSchoolType.icon,
            contentDescription = "",
            tint = Color.Black,
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text=postSchoolType.text,
            style = TextStyle(
                fontSize = 9.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
        )
    }


}

@Composable
fun FloatingActionButtonComponent(
    navRoute:String="",
    navController: NavController,
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
            .padding(end=20.dp,bottom=80.dp)

    ) {
        LargeFloatingActionButton(
            onClick = {
                navController.navigate(navRoute)
            },
            containerColor = Pastel_Red,
            shape = CircleShape,
            modifier = Modifier
                .size(35.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()


    NextClassTheme {
        SinglePostComponent(postClick = {})
    }
}


@Preview(showBackground = true)
@Composable
fun InsertPostPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()
    val communityViewModel=CommunityViewModel()

    NextClassTheme {
        InsertOrModifyPostComponent(communityViewModel = communityViewModel, navController = navController, loginViewModel = loginViewModel, postType = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PostDetailPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()


    NextClassTheme {
        PostDetailComponent(
            deletePost = {},
            modifyPost = {},
            likePost = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CommentComponentPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()


    NextClassTheme {
        CommentComponent(
            deleteComment = {},
            modifyComment = {},
            likeComment = {}
        )
    }
}