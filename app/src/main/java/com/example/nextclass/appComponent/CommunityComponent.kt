package com.example.nextclass.appComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.nextclass.Data.CommunityData.CommunityCommentData
import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.Data.TimeTableData.PostSchoolType
import com.example.nextclass.R
import com.example.nextclass.repository.testRepo.CommunityTestRepository
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.utils.MaxTextCount
import com.example.nextclass.utils.TimeFormatter
import com.example.nextclass.view.InsertOrModifyPostComponent
import com.example.nextclass.viewmodel.CommunityViewModel
import com.example.nextclass.viewmodel.LoginViewModel


@Composable
fun InsertPostContentBox(
    text:String="",
    onValueChange:(String)->Unit
){
    Box(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .clip(RoundedCornerShape(15.dp))

            .background(Color.White)
            .border(1.5.dp, Color.LightGray, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {

        BasicTextField(
            value = text,
            onValueChange = {
                if (it.length <= MaxTextCount) {
                    onValueChange(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 500.dp)
                .padding(14.dp),
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.TopStart
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = "내용을 입력해 주세요",
                            style = TextStyle.Default.copy(color = Color.Gray, fontSize = 20.sp)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun InsertPostSubjectBox(
    text:String="",
    onValueChange:(String)->Unit
){
    Box(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .border(1.5.dp, Color.LightGray, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {

        BasicTextField(
            value = text,
            onValueChange = {
                    onValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
//                .height(50.dp)
                .padding(14.dp),
            singleLine = true,
            textStyle = TextStyle.Default.copy(fontSize = 15.sp),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = "제목을 입력해 주세요",
                            style = TextStyle.Default.copy(color = Color.Gray, fontSize = 15.sp)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun SinglePostComponent(
    singlePostData: CommunityPostData = CommunityPostData(subject = "NameTest", content = "DetailTest"),
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
                text=singlePostData.subject,
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
                text=singlePostData.content,
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
                    text=TimeFormatter.formatDate(singlePostData.reg_date),
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
                            .padding(start=2.dp),
                        text = singlePostData.comment_count.toString(),
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
                            .padding(start=2.dp),
                        text = singlePostData.vote_count.toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                        ),
                    )
                }

                //익명인지 아닌지 정보도 표기
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(14.dp)
                            .align(Alignment.CenterVertically),
                        imageVector = ImageVector.vectorResource(R.drawable.author_icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )

                    Text(
                        modifier = Modifier
                            .padding(start=2.dp),
                        text = singlePostData.author,
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
    selectPost: CommunityPostData = CommunityPostData(),
    deletePost: () -> Unit,
    likePost: () -> Unit,
    modifyPost: () -> Unit,
    postOwner: Boolean,
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),

        ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){
            if(postOwner){
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
                    text = selectPost.subject,
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
                    text=selectPost.content,
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
                    text=TimeFormatter.formatDate(selectPost.reg_date),
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
                        text = selectPost.comment_count.toString(),
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
                        text = selectPost.vote_count.toString(),
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
                            imageVector = ImageVector.vectorResource(R.drawable.author_icon),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(start=3.dp),
                        text = selectPost.author,
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
    singleCommentData: CommunityCommentData = CommunityCommentData(),
    modifyComment: () -> Unit = {},
    deleteComment: () -> Unit = {},
    likeComment: () -> Unit = {},
    commentClick: () -> Unit = {},
    optionVisible: Boolean = true,
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                commentClick()
            },

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
                if(optionVisible){

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
                    if(singleCommentData.is_owner){
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

            }

        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = singleCommentData.content,
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
                    text=TimeFormatter.formatDate(singleCommentData.reg_date.toString()),
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
                        text = singleCommentData.vote_count.toString(),
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
                        imageVector = ImageVector.vectorResource(R.drawable.author_icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )

                    Text(
                        modifier = Modifier
                            .padding(start=3.dp),
                        text = singleCommentData.author,
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
fun InsertCommentComponent(
    communityViewModel: CommunityViewModel,
    text: String = "",
    onValueChange: (String) -> Unit = {},
    isSecret: Boolean = true,
    toggleCommentSecretState: () -> Unit = {},
    postSequence: String,

) {

    Column(
        Modifier
            .fillMaxWidth()
            .background(Background_Color2)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(8.dp),

//
//            .imePadding()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White),
//                .border(1.5.dp, Color.LightGray, shape = RoundedCornerShape(15.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommentCheckboxComponent(
                checked = isSecret,
                onClickCheckBox = { toggleCommentSecretState() },
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                readOnly = false,
                singleLine = true,
                modifier = Modifier.weight(1f), // 남은 공간을 차지하도록 설정
                textStyle = TextStyle(
                    fontSize = 15.sp
                ),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (text.isEmpty()) {
                            Text(
                                text = "댓글을 입력해 주세요",
                                style = TextStyle.Default.copy(color = Color.Gray, fontSize = 15.sp)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Image(
                imageVector = ImageVector.vectorResource(R.drawable.write_icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp).clickable { communityViewModel.postCommentData(postSequence) },
                colorFilter = ColorFilter.tint(Pastel_Red),

            )
            Spacer(modifier = Modifier.width(10.dp))
        }
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
            .padding(end = 20.dp, bottom = 80.dp)

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

val selectPostType= listOf("내 게시물","내가 쓴 댓글")


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostOptionDropDownMenu(
    value: String="",
    onValueChange: (String) -> Unit,
    dropDownMenuOption: Boolean,
    toggleDropDownMenuOption: () -> Unit,
    dropDownIconVisible:Boolean=true,
    menuItems: List<String>,

){
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)


            .heightIn(min = 30.dp)
            .padding(2.dp),

        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ExposedDropdownMenuBox(
            expanded = dropDownMenuOption,
            onExpandedChange = { toggleDropDownMenuOption() },

            ) {

            Row(
                modifier = Modifier,
//                    .width(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = value,
                    modifier = Modifier
                        .menuAnchor()
                        .padding(start = 3.dp, top = 3.dp, bottom = 3.dp, end = 1.dp),

                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    ),
                )


                CustomTrailingIcon(expanded=dropDownMenuOption,size=20.dp)

            }

            ExposedDropdownMenu(
                expanded = dropDownMenuOption,
                onDismissRequest = { toggleDropDownMenuOption() },
                modifier = Modifier.width(120.dp)
            ) {
                menuItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text( text = item,) },
                        onClick = {
                            onValueChange(item)
                            toggleDropDownMenuOption()
                        },
                        modifier = Modifier


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
        SinglePostComponent(postClick = {})
    }
}


@Preview(showBackground = true)
@Composable
fun InsertPostPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val communityTestRepository=CommunityTestRepository()
    val navController = rememberNavController()
    val communityViewModel=CommunityViewModel(communityTestRepository)

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
            likePost = {},
            modifyPost = {},
            postOwner = true
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun CommentComponentPreview() {
//    val testRepository = TestRepository()
//    val loginViewModel = LoginViewModel(testRepository)
//
//    val navController = rememberNavController()
//
//
//    NextClassTheme {
//        CommentComponent(
//            modifyComment = {},
//            deleteComment = {},
//            likeComment = {},
//            optionVisible = false
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PostOptionDropDownPreview() {
//    val testRepository = TestRepository()
//    val loginViewModel = LoginViewModel(testRepository)
//
//    val navController = rememberNavController()
//
//
//    NextClassTheme {
//        PostOptionDropDownMenu(
//            value = "내가 쓴 글",
//            onValueChange = {},
//            dropDownMenuOption = false,
//            toggleDropDownMenuOption = { /*TODO*/ },
//            menuItems = selectPostType
//        )
//    }
//}


@Preview(showBackground = true)
@Composable
fun InsertSubjectPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()
    val communityTestRepository=CommunityTestRepository()

    val communityViewModel=CommunityViewModel(communityTestRepository)

    NextClassTheme {
        InsertCommentComponent(
            communityViewModel=communityViewModel,
            postSequence = communityViewModel.selectCommunityData.value.post_sequence
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommentPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()
    val communityTestRepository=CommunityTestRepository()

    val communityViewModel=CommunityViewModel(communityTestRepository)

    NextClassTheme {
        CommentComponent(
            singleCommentData= CommunityCommentData(),
            modifyComment = { communityViewModel.modifyComment(CommunityCommentData()) },
            deleteComment = { communityViewModel.deleteComment(CommunityCommentData()) },
            likeComment = { communityViewModel.likeComment(CommunityCommentData()) },
            optionVisible = false,

            )
    }
}