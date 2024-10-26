package com.example.nextclass.view

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.ChanceCountComponent
import com.example.nextclass.appComponent.ChangeEmailComponent
import com.example.nextclass.appComponent.ChangePasswordComponent
import com.example.nextclass.appComponent.ChangeUserInfoComponent
import com.example.nextclass.appComponent.CountDownComponent
import com.example.nextclass.appComponent.DescriptionTextComponent
import com.example.nextclass.appComponent.DividerComponent
import com.example.nextclass.appComponent.FindFieldComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.PasswordInputFieldComponent
import com.example.nextclass.appComponent.ProgressBarFullComponent
import com.example.nextclass.appComponent.RePostPasswordCodeComponent
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.appComponent.UserProfileItemComponent
import com.example.nextclass.appComponent.UserProfilePreviewComponent
import com.example.nextclass.appComponent.UserProfileSwitchItemComponent
import com.example.nextclass.appComponent.VerifyCodeInputComponent
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Feldgrau
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.utils.TokenManager
import com.example.nextclass.utils.UserInfoManager
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.UserInfoViewModel

@Composable
fun UserProfileView(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainNavController: NavController,
    userInfoViewModel:UserInfoViewModel,
) {
    val context = LocalContext.current




    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
        ,

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        LaunchedEffect(Unit) {
            userInfoViewModel.getUserInfo()
        }



        Column(
            modifier = Modifier
                .padding(top = 20.dp,bottom=20.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            AppBarTextAndButtonComponent(
                value = "회원 정보",
                navController = navController,
                showRightButton = false,
                showLeftButton = false,
            )
        }



            UserProfilePreviewComponent(
                name = userInfoViewModel.userProfile.value.name,
                schoolName = userInfoViewModel.userProfile.value.member_school,
                grade = userInfoViewModel.userProfile.value.member_grade
            )


        Surface(
            shape = RoundedCornerShape(35.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 80.dp)

        ) {
                    Column(
                        modifier = Modifier
                            .background(Feldgrau)
                            .fillMaxSize()

                            ,

//                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        MainTextComponent(
                            value = "사용자 정보",
                            modifier= Modifier
                                .padding(bottom=20.dp)
                            ,
                            color = Color.White
                        )

                        DividerComponent(modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .height(1.dp)
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.email_icon),
                            text = "이메일 변경",
                            address = "changeEmailView",
                            navController = navController
                        )
                        Spacer(modifier = Modifier.height(40.dp))

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.password_icon),
                            text = "비밀번호 변경",
                            address = "changePasswordView",
                            navController = navController
                        )
                        Spacer(modifier = Modifier.height(40.dp))

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.user_profile_icon),
                            text = "사용자 정보 변경",
                            address = "changeUserInfoView",
                            navController = navController
                        )
                        Spacer(modifier = Modifier.height(40.dp))



                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.log_out_icon),
                            text = "로그 아웃",
                            address = "passwordConfirmView",
                            navController = navController,
                            logOut = true,
                            onClick = {
                                loginViewModel.logOut()

                                UserInfoManager.clearUserInfo(context)
                                TokenManager.clearToken(context)

                                mainNavController.navigate("loginOrJoinGraph") {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        inclusive = true
                                    }
//                                    popUpTo("mainNav") { inclusive = true }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(20.dp))


                        MainTextComponent(
                            value = "커뮤니티",
                            modifier= Modifier
                                .padding(bottom=20.dp)
                            ,
                            color = Color.White
                        )


                        DividerComponent(modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .height(1.dp)
                        )
                        Spacer(modifier = Modifier.height(30.dp))

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.my_post_icon),
                            text = "작성한 게시물",
                            address = "myPostView",
                            navController = navController
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.my_comment_icon),
                            text = "작성한 댓글",
                            address = "myCommentView",
                            navController = navController
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.empty_heart_icon),
                            text = "추천한 게시물",
                            address = "myFavoritePostView",
                            navController = navController
                        )


                        Spacer(modifier = Modifier.height(20.dp))


                        MainTextComponent(
                            value = "알림",
                            modifier= Modifier
                                .padding(bottom=20.dp)
                            ,
                            color = Color.White
                        )
                        DividerComponent(modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                            .height(1.dp)
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.notification_icon),
                            text = "알림 설정",
                            address = "notificationSettingView",
                            navController = navController
                        )

                        Spacer(modifier = Modifier.height(30.dp))


                        TextButton(
                            content={
                                Text(
                                    text="회원 탈퇴",
                                    color=Color.White,
                                    fontSize = 17.sp

                                )},
                            onClick = {
                                navController.navigate("memberDeletePasswordConfirmView") {
                                    launchSingleTop = true
                                }
                            },
                            modifier = Modifier)

                        Spacer(modifier = Modifier.height(20.dp))
                    }

            }

    }
}



//todo 비밀번호 재설정창 제작해야함
//회원정보 수정의 비밀번호 인증 뷰
@Composable
fun PasswordConfirm(
    loginViewModel: LoginViewModel,
    navController: NavController,
    mainNavHostController: NavHostController,

) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        AppBarTextAndButtonComponent(value = stringResource(id = R.string.UserInfoModify),
            navController=navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .height(350.dp)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Background_Color2)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),

                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    MainTextComponent(
                        value = stringResource(id = R.string.UserInfoModify),
                        modifier=Modifier
                            .padding(top=20.dp)
                    )

                    DescriptionTextComponent(
                        value= stringResource(id = R.string.InputUserInfoModifyPassword),
                        modifier=Modifier.padding(start = 5.dp,top=5.dp)
                    )

                    FindFieldComponent(
                        value = loginViewModel.userInfoModifyPasswordConfirm.value,
                        onValueChange = { loginViewModel.updateUserInfoModifyPasswordConfirm(it) },

                        placeholderValue = stringResource(id = R.string.input_password),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextInputHelpFieldComponent(
                        errorMessage = loginViewModel.userInfoModifyPasswordConfirmErrorMessage.value.asString(LocalContext.current),
                        isError = loginViewModel.userInfoModifyPasswordConfirmError.value,
                    )

                    InputButtonComponent(
                        value = "비밀번호 확인",
                        onClick = { loginViewModel.submitUserInfoModifyPasswordConfirm() },
                        modifier = Modifier.padding(start=15.dp,end=15.dp))

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun MemberDeletePasswordConfirmView(
    loginViewModel: LoginViewModel,
    navController: NavController,
    mainNavHostController: NavHostController,

    ) {
    val context = LocalContext.current

    if(loginViewModel.deleteUserResult.value) {
        loginViewModel.logOut()

        UserInfoManager.clearUserInfo(context)
        TokenManager.clearToken(context)

        mainNavHostController.navigate("loginOrJoinGraph") {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }

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
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .height(330.dp)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(Background_Color2)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),

                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    MainTextComponent(
                        value = "회원 탈퇴",
                        modifier=Modifier
                            .padding(top=20.dp)
                    )
//
//                    DescriptionTextComponent(
//                        value= "탈퇴를 위해서는 비밀번호 확인이 필요합니다.",
//                        modifier=Modifier.padding(start=17.dp,top=10.dp,end=5.dp).fillMaxWidth()
//                    )

                    PasswordInputFieldComponent(
                        value = loginViewModel.deleteUserPasswordConfirm.value,
                        onValueChange = { loginViewModel.updateDeleteUserPasswordConfirm(it) },

                        placeholderValue = stringResource(id = R.string.input_password),
                        togglePassWordVisibility = { loginViewModel.toggleDeleteUserPasswordConfirmVisibleState() },
                        passwordVisibleOption = loginViewModel.deleteUserPasswordConfirmVisibleState.value,
                        labelValue = "탈퇴를 위해서는 비밀번호 확인이 필요합니다."
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TextInputHelpFieldComponent(
                        errorMessage = loginViewModel.deleteUserPasswordConfirmErrorMessage.value.asString(LocalContext.current),
                        isError = loginViewModel.deleteUserPasswordConfirmErrorState.value,
                    )

                    InputButtonComponent(
                        value = "비밀번호 확인",
                        onClick = { loginViewModel.submitDeleteUserPassword() },
                        modifier = Modifier.padding(start=15.dp,end=15.dp))

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}


@Composable
fun NotificationSettingView(

    navController: NavController,
    userInfoViewModel: UserInfoViewModel

    ) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        userInfoViewModel.getNotificationState()
    }

    LaunchedEffect(userInfoViewModel.userInfoToastMessage.value) {
        userInfoViewModel.userInfoToastMessage.value?.let{
            Toast.makeText(context, userInfoViewModel.userInfoToastMessage.value, Toast.LENGTH_SHORT,)
                .show()
            userInfoViewModel.clearToastMessage()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if(userInfoViewModel.loading.value){
            ProgressBarFullComponent(state = userInfoViewModel.loading.value)
        }

        AppBarTextAndButtonComponent(
            value = "알림 설정",
            navController = navController
        )


        Surface(
            shape = RoundedCornerShape(35.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 10.dp)

        ) {
            Column(
                modifier = Modifier
                    .background(Feldgrau)
                    .fillMaxSize(),

//                        verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                MainTextComponent(
                    value = "알림 설정",
                    modifier = Modifier
                        .padding(bottom = 20.dp),
                    color = Color.White
                )

                DividerComponent(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .height(1.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                UserProfileSwitchItemComponent(
                    image = ImageVector.vectorResource(R.drawable.notification_icon),
                    text = "커뮤니티 알림 설정",
                    switchState = userInfoViewModel.notificationStates.value.community.is_notification_activated,
                    switchClick = {
                        userInfoViewModel.toggleNotificationState(
                            category = userInfoViewModel.notificationStates.value.community.category,
                            state = userInfoViewModel.notificationStates.value.community.is_notification_activated
                        )
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))

                UserProfileSwitchItemComponent(
                    image = ImageVector.vectorResource(R.drawable.notification_icon),
                    text = "스케쥴 알림 설정",
                    switchState = userInfoViewModel.notificationStates.value.schedule.is_notification_activated,
                    switchClick = {
                        userInfoViewModel.toggleNotificationState(
                            category = userInfoViewModel.notificationStates.value.schedule.category,
                            state = userInfoViewModel.notificationStates.value.schedule.is_notification_activated
                        )
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))

            }

        }



    }

}


@Composable
fun ChangeUserInfoView(

    navController: NavController,
    userInfoViewModel: UserInfoViewModel
) {
    val scrollState = rememberScrollState()

    Column() {

        Column(
            modifier = Modifier
                .padding(top = 20.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            AppBarTextAndButtonComponent(value = stringResource(id = R.string.UserInfoModify),
                navController=navController)
        }

        ChangeUserInfoComponent(
            userInfoViewModel = userInfoViewModel,
            navController=navController
        )
    }
}

@Composable
fun ChangePasswordView(

    navController: NavController,
    userInfoViewModel: UserInfoViewModel,
    loginViewModel: LoginViewModel
){
    val scrollState = rememberScrollState()

    Column() {

        Column(
            modifier = Modifier
                .padding(top = 20.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AppBarTextAndButtonComponent(
                value = stringResource(id = R.string.changePassword),
                navController = navController
            )
        }
        ChangePasswordComponent(
            userInfoViewModel = userInfoViewModel,
            navController=navController,
            loginViewModel=loginViewModel
        )
    }
}

@Composable
fun ChangeEmailView(

    navController: NavController,
    userInfoViewModel: UserInfoViewModel,
){
    val scrollState = rememberScrollState()


    if(userInfoViewModel.verifyCodeIssueState.value){
        navController.navigate("changeEmailInsertCodeView")
        userInfoViewModel.toggleVerifyCodeIssueState()
    }


    Column() {
        if(userInfoViewModel.loading.value){
            ProgressBarFullComponent(state = userInfoViewModel.loading.value)
        }
        Column(
            modifier = Modifier
                .padding(top = 20.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AppBarTextAndButtonComponent(
                value = stringResource(id = R.string.changeEmail),
                navController = navController
            )
        }
        ChangeEmailComponent(
            userInfoViewModel = userInfoViewModel,
            navController=navController
        )
    }
}



@Composable
fun ChangeEmailInsertCodeView(
    userInfoViewModel: UserInfoViewModel,
    navController: NavController)  {

    LaunchedEffect(Unit) {
        userInfoViewModel.startCountDown()
    }

    //테스트 해야함
    if(userInfoViewModel.verifyCodeCheckResult.value){
        userInfoViewModel.changeEmailComplete()
        userInfoViewModel.toggleVerifyCodeCheckResult()
    }

    if(userInfoViewModel.changeEmailState.value){

        navController.navigate("userProfileRoute"){
//            popUpTo("changeEmailInsertCodeView") { inclusive = true }
        }
        userInfoViewModel.toggleChangeEmailState()
    }

    Column {

        if(userInfoViewModel.loading.value){
            ProgressBarFullComponent(state = userInfoViewModel.loading.value)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            AppBarTextAndButtonComponent(value = stringResource(id = R.string.InputVerifyCode),
                navController=navController)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Surface(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .height(380.dp)
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Background_Color2)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),

                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {



                        MainTextComponent(
                            value= stringResource(id = R.string.InputVerityCode),
                            modifier=Modifier
                                .padding(top=20.dp)
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                        DescriptionTextComponent(
                            value= stringResource(id = R.string.InputVerityCodeDetailMessage),
                            modifier= Modifier.padding(start = 5.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        VerifyCodeInputComponent(
                            onValueChange = {
                                userInfoViewModel.updateVerifyCode(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))


                        RePostPasswordCodeComponent(
                            onClick = {
                                userInfoViewModel.getChangeEmailVerifyCode()
                            }
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ){
                            CountDownComponent(
                                countDown = userInfoViewModel.countDown.value,
                                countDownState = userInfoViewModel.countDownState.value
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            ChanceCountComponent(
                                remainingChance = userInfoViewModel.remainingChance.value
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        TextInputHelpFieldComponent(
                            errorMessage = userInfoViewModel.verifyCodeInputErrorMessage.value.asString(LocalContext.current),
                            isError = userInfoViewModel.verifyCodeInputError.value,
                        )


                        InputButtonComponent(
                            value = "확인",
                            onClick = { userInfoViewModel.submitVerifyCode() },
                            modifier = Modifier.padding(start=15.dp,end=15.dp),
                            showImage = true
                        )


                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }

}

fun deleteUserInfo(context: Context) {
    val autoLoginInfo = context.getSharedPreferences("autoLoginInfo", Context.MODE_PRIVATE)
    val editor = autoLoginInfo.edit()


    editor.putString("userId", null)
    editor.putString("userPassword", null)
    editor.apply()
}


@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    val mainNavController= rememberNavController()
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val userInfoViewModel=UserInfoViewModel(testRepository)

    MaterialTheme {
        UserProfileView(mainNavController,loginViewModel,navController,userInfoViewModel)
    }
}


@Preview(showBackground = true)
@Composable
fun ChangeUserInfoPreview() {
    val navController = rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val mainNavHostController= rememberNavController()
    val userInfoViewModel=UserInfoViewModel(testRepository)

    NextClassTheme {
//        LoginView(loginViewModel)
//        ForGotId(loginViewModel)
//        TopNav(loginViewModel)
        ChangeUserInfoView(
            navController = navController,
            userInfoViewModel
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordComponentPreview() {
    val navController=rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val mainNavHostController= rememberNavController()
    val userInfoViewModel=UserInfoViewModel(testRepository)
    ChangePasswordView(
        navController,
        userInfoViewModel,
        loginViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun ChangeEmailComponentPreview() {
    val navController=rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val mainNavHostController= rememberNavController()
    val userInfoViewModel=UserInfoViewModel(testRepository)
    MemberDeletePasswordConfirmView(
        loginViewModel = loginViewModel,
        navController = navController,
        mainNavHostController = mainNavHostController
    )
}


@Preview(showBackground = true)
@Composable
fun NotificationSettingPreview() {
    val navController=rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val mainNavHostController= rememberNavController()
    val userInfoViewModel=UserInfoViewModel(testRepository)
    NotificationSettingView(

        navController = navController,
        userInfoViewModel
    )
}