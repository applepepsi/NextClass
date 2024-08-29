package com.example.nextclass.view

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.EmptyHomeItemComponent
import com.example.nextclass.appComponent.GridScheduleItem
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.LazyScheduleItem
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.ProgressBarFullComponent
import com.example.nextclass.appComponent.ScheduleTextInsertPreview
import com.example.nextclass.appComponent.SinglePostComponent
import com.example.nextclass.appComponent.TodaySingleClassComponent
import com.example.nextclass.appComponent.UserProfilePreviewComponent
import com.example.nextclass.repository.testRepo.CommunityTestRepository
import com.example.nextclass.repository.testRepo.ScheduleTestRepository
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.repository.testRepo.TimeTableTestRepo
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.CommunityViewModel
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.ScheduleViewModel
import com.example.nextclass.viewmodel.TimeTableViewModel
import com.example.nextclass.viewmodel.UserInfoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeView(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController,
    communityViewModel: CommunityViewModel,
    scheduleViewModel: ScheduleViewModel,
    timeTableViewModel: TimeTableViewModel,
    userInfoViewModel:UserInfoViewModel
) {
    val context = LocalContext.current


    val scrollState = rememberScrollState()


    LaunchedEffect(Unit) {

        //오늘의 수업
        timeTableViewModel.getTimeTable(splitTodayClass = true)
//        timeTableViewModel.splitTodayClass()

        //투두리스트 가져오기
        scheduleViewModel.resetScheduleData()
        scheduleViewModel.getScheduleData(splitToday = true)


        //베스트 게시물 가져오기
        communityViewModel.resetPostList()
        communityViewModel.getPostList(sort = "vote", post_sequence = null, size = 4)

        userInfoViewModel.getUserInfo()
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            ProgressBarFullComponent(state = timeTableViewModel.loading.value)
        }

//        item {
//            Column(
//                modifier = Modifier
//                    .padding(top = 20.dp,bottom=10.dp),
//
//                verticalArrangement = Arrangement.Top,
//                horizontalAlignment = Alignment.CenterHorizontally
//
//            ) {
//
//                AppBarTextAndButtonComponent(
//                    value = "다음 수업",
//                    navController = navController,
//                    showRightButton = false,
//                    showLeftButton = false,
//                )
//            }
//        }

        item {
            Spacer(Modifier.height(30.dp))
        }

        item {
            UserProfilePreviewComponent(
                name = userInfoViewModel.userProfile.value.name,
                schoolName = userInfoViewModel.userProfile.value.member_school,
                grade = userInfoViewModel.userProfile.value.member_grade
            )
        }

        item {
            Spacer(Modifier.height(30.dp))
        }

        item {
            Text(
                text = "오늘의 수업",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
                modifier = Modifier.padding(start = 10.dp, bottom = 5.dp)
            )
        }

        item {

            if(timeTableViewModel.todayClassDataList.value.isEmpty()){
                EmptyHomeItemComponent()
            }else{
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(110.dp),
                    contentPadding = PaddingValues(start = 5.dp)
                ) {
                    items(items = timeTableViewModel.todayClassDataList.value) { singleClassData ->
                        Log.d("singleClassData", singleClassData.toString())
                        TodaySingleClassComponent(singleClassData = singleClassData)
                    }
                }
            }
        }

        item {
            Spacer(Modifier.height(30.dp))
        }

        item {
            Text(
                text = "오늘의 할일",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
                modifier = Modifier.padding(start = 10.dp, bottom = 5.dp)
            )
        }

        if(scheduleViewModel.groupByDateScheduleData.value.toList().isEmpty()){

            item{
                EmptyHomeItemComponent()
            }

        }else{
            items(scheduleViewModel.groupByDateScheduleData.value.toList()) { (date, schedules) ->
                Log.d("schedules2", schedules.toString())



                LazyScheduleItem(
                    date = date.toString(),
                    schedules = schedules,
                    scheduleViewModel = scheduleViewModel,
                    navController = navController
                )


            }
        }

        item {
            Spacer(Modifier.height(30.dp))
        }

        item {
            Text(
                text = "오늘의 인기글",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
                modifier = Modifier.padding(start = 10.dp, bottom = 5.dp)
            )
        }

        if(communityViewModel.communityDataList.value.isEmpty()){
            item{
                EmptyHomeItemComponent()
            }

        }else{
            items(items = communityViewModel.communityDataList.value) { singlePostData ->
                SinglePostComponent(
                    singlePostData,
                    postClick = {
                        communityViewModel.setSelectedCommunityData(singlePostData.post_sequence)
                    }
                )
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
//    val testRepository = TestRepository()
//    val loginViewModel = LoginViewModel(testRepository)
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val timeTableTestRepo=TimeTableTestRepo()
    val communityTestRepository=CommunityTestRepository()
    val scheduleTestRepository= ScheduleTestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val mainNavHostController= rememberNavController()


    val userInfoViewModel= UserInfoViewModel(testRepository)
    val communityViewModel=CommunityViewModel(communityTestRepository)
    val scheduleViewModel=ScheduleViewModel(scheduleTestRepository)
    val timeTableViewModel=TimeTableViewModel(timeTableTestRepo)


    NextClassTheme {
        HomeView(
            navController = navController,
            loginViewModel = loginViewModel,
            mainNavController = mainNavHostController,
            communityViewModel = communityViewModel,
            scheduleViewModel = scheduleViewModel,
            timeTableViewModel = timeTableViewModel,
            userInfoViewModel=userInfoViewModel,

        )
    }
}