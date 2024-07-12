package com.example.nextclass.appComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import com.example.nextclass.R
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun SinglePostComponent(

){

    Column(
        modifier = Modifier
            .fillMaxWidth(),

    ){
        Column(
            modifier = Modifier
                .padding(10.dp)

        ) {
            Text(
                text="제목",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text="내용",
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
                    .padding(start=2.dp),

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
                    text="2024-05-08",
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
                        text = "2",
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
                        text = "3",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                }
            }
        }
    }


}

@Composable
fun PostDetailComponent(

){

    Column(
        modifier = Modifier
            .fillMaxWidth(),

        ){
        Column(
            modifier = Modifier
                .padding(10.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "제목",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    ),

                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min=200.dp)
            ){
                Text(
                    text="내용",
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
                    .padding(start=2.dp),

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
                    text="2024-05-08",
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
                        text = "2",
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
                        text = "3",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                }
            }
        }
    }


}

@Composable
fun CommentComponent(

){

    Column(
        modifier = Modifier
            .fillMaxWidth(),

        ){
        Row(
            modifier = Modifier
                .padding(start=10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "익명1",
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
                text = "댓글 내용",
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
                    .padding(start=2.dp),

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
                    text="2024-05-08",
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
                        text = "2",
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
                        text = "3",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                }
            }
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
        SinglePostComponent()
    }
}

@Preview(showBackground = true)
@Composable
fun PostDetailPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()


    NextClassTheme {
        PostDetailComponent()
    }
}


@Preview(showBackground = true)
@Composable
fun CommentComponentPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()


    NextClassTheme {
        CommentComponent()
    }
}