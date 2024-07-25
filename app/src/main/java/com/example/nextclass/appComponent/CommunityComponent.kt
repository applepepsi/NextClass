package com.example.nextclass.appComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.CommunityCommentData
import com.example.nextclass.Data.CommunityPostData
import com.example.nextclass.R
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.utils.TimeFormatter
import com.example.nextclass.viewmodel.LoginViewModel
import java.time.LocalDateTime

@Composable
fun SinglePostComponent(
    singlePostData:CommunityPostData=CommunityPostData(),
    postClick:()->Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                postClick()
            },

    ){
//        DividerComponent(modifier = Modifier
//            .height(0.5.dp),
//            dividerColor = Color.LightGray
//        )

        Column(
            modifier = Modifier
                .padding(10.dp)

        ) {
            Text(
                text=singlePostData.postName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text=singlePostData.postDetail,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
        DividerComponent(modifier = Modifier
            .height(0.5.dp),
            dividerColor = Color.LightGray
        )
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