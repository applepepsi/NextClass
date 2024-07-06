package com.example.nextclass.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.ChangeEmailComponent
import com.example.nextclass.appComponent.ChangePasswordComponent
import com.example.nextclass.appComponent.ChangeUserInfoComponent
import com.example.nextclass.appComponent.DescriptionTextComponent
import com.example.nextclass.appComponent.FindFieldComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.RePostPasswordCodeComponent
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.appComponent.UserProfileItemComponent
import com.example.nextclass.appComponent.UserProfilePreviewComponent
import com.example.nextclass.appComponent.VerifyCodeInputComponent
import com.example.nextclass.repository.TestRepository
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

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

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



        UserProfilePreviewComponent()


            Surface(
                shape = RoundedCornerShape(35.dp),
                modifier = Modifier

                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 15.dp),

            ) {
                UserProfilePreviewComponent()

                    Column(
                        modifier = Modifier
                            .background(Feldgrau),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        MainTextComponent(
                            value = "사용자 정보",
                            modifier= Modifier
                                .padding(top=20.dp),
                            color = Color.White
                        )

                        WhiteDividerComponent()

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.email_icon),
                            text = "이메일 변경",
                            address = "passwordConfirmView",
                            navController = navController
                        )

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.password_icon),
                            text = "비밀번호 변경",
                            address = "passwordConfirmView",
                            navController = navController
                        )

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.user_profile_icon),
                            text = "사용자 정보 변경",
                            address = "passwordConfirmView",
                            navController = navController
                        )

                        WhiteDividerComponent()

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.notification_icon),
                            text = "알림 설정",
                            address = "passwordConfirmView",
                            navController = navController
                        )

                        UserProfileItemComponent(
                            image = ImageVector.vectorResource(R.drawable.notification_icon),
                            text = "알림 설정",
                            address = "passwordConfirmView",
                            navController = navController
                        )


                        WhiteDividerComponent()

                        TextButton(
                            content={
                                Text(
                                text="로그아웃",
                                    color=Color.White,
                                    fontSize = 17.sp

                            )},
                            onClick = {
                                //todo 토큰을 제거하는 기능도 추가해야함
                                loginViewModel.logOut()

                                UserInfoManager.clearUserInfo(context)
                                TokenManager.clearToken(context)

                                mainNavController.navigate("loginOrJoinGraph") {
                                    popUpTo("mainNav") { inclusive = true }
                                }
                            },
                            modifier = Modifier)
                    }

            }

    }
}

@Composable
fun WhiteDividerComponent(

) {
    Divider(
        color = Color.White,
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.White)
    )
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

    navController:NavController,
    userInfoViewModel: UserInfoViewModel
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
            navController=navController
        )
    }
}

@Composable
fun ChangeEmailView(

    navController: NavController,
    userInfoViewModel: UserInfoViewModel,
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

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                        value= stringResource(id = R.string.InputVerityCode),
                        modifier=Modifier
                            .padding(top=20.dp)
                    )

                    DescriptionTextComponent(
                        value= stringResource(id = R.string.InputVerityCodeDetailMassage),
                        modifier=Modifier.padding(start = 5.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    VerifyCodeInputComponent(
                        onValueChange = {
                            userInfoViewModel.updateVerifyCode(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                    RePostPasswordCodeComponent(
                    )

                    Spacer(modifier = Modifier.height(15.dp))


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
        userInfoViewModel
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
    ChangeEmailView(

        navController,
        userInfoViewModel
    )
}