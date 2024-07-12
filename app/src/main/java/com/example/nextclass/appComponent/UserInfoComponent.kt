package com.example.nextclass.appComponent

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.viewmodel.UserInfoViewModel

@Composable
fun UserProfilePreviewComponent(
    name:String,
    schoolName:String,
    grade:Int,
){
    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(35.dp))
            .fillMaxWidth()
            .height(100.dp)
//            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(15.dp))
            .background(Pastel_Red)
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(

            modifier = Modifier
//                                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
                .size(70.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .align(Alignment.CenterVertically)

        ){
            Icon(
                modifier = Modifier
                    .size(55.dp)
                    .align(Alignment.Center),
                imageVector = ImageVector.vectorResource(R.drawable.user_profile_icon),
                contentDescription = "",
                tint = Color.Black,
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                modifier = Modifier
                    .padding(2.dp),
                text = name,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),

            )
            Row(){

                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = schoolName,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    ),
                    color= Color.Black
                )
                Text(
                    modifier = Modifier
                        .padding(2.dp),
                    text = "$grade 학년",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    ),
                    color= Color.Black
                )
            }
        }
    }
}

@Composable
fun UserProfileItemComponent(
    image:ImageVector,
    text:String,
    address: String,
    navController:NavController,
){
    Row(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier,
        ) {

//            Box(
//                modifier = Modifier
//                    .height(37.dp)
//                    .width(50.dp)
//                    .clip(RoundedCornerShape(20.dp))
//                    .background(Pastel_Red),
//            ) {
//                Icon(
//                    modifier = Modifier
//                        .size(25.dp)
//                        .align(Alignment.Center),
//                    imageVector = image,
//                    contentDescription = "",
//                    tint = Color.Black,
//                )
//            }
            Icon(
                modifier = Modifier
                    .size(25.dp),
                imageVector = image,
                contentDescription = "",
                tint = Color.White,

            )




            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
                color =  Color.White
            )
        }
        IconButton(
            modifier = Modifier
                .size(20.dp),
            content = {
                Icon(
                    modifier = Modifier,
                    imageVector = ImageVector.vectorResource(R.drawable.arrow),
                    contentDescription = "",
                    tint =  Color.White,
                )
            },
            onClick = {
                navController.navigate(address) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
        )
    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun ChangePasswordComponent(
    userInfoViewModel: UserInfoViewModel,
    navController: NavController

){
    val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Surface(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier

                    .padding(start = 10.dp, end = 10.dp,bottom=20.dp)
            ) {


                Column(
                    modifier = Modifier
                        .background(Background_Color2)
                        .padding(top = 30.dp, bottom = 20.dp)
                    ,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    PasswordInputFieldComponent(
                        value = userInfoViewModel.changePasswordData.value.existingPassword,
                        onValueChange = {
                            userInfoViewModel.updateOldPassword(it)
                        },
                        labelValue = "기존 비밀번호",
                        placeholderValue = "",
                        passwordVisibleOption = userInfoViewModel.oldPasswordVisibility.value,
                        togglePassWordVisibility = {
                            userInfoViewModel.toggleOldPasswordVisibility()
                        })

                    PasswordInputFieldComponent(
                        value = userInfoViewModel.changePasswordData.value.newPassword,
                        onValueChange = {
                            userInfoViewModel.updateNewPassword(it)
                        },
                        labelValue = "새 비밀번호",
                        placeholderValue = "",
                        passwordVisibleOption = userInfoViewModel.newPasswordVisibility.value,
                        togglePassWordVisibility = {
                            userInfoViewModel.toggleNewPasswordVisibility()
                        })

                    PasswordInputFieldComponent(
                        value = userInfoViewModel.newPasswordConfirm.value,
                        onValueChange = {
                            userInfoViewModel.updateNewPasswordConfirm(it)
                        },
                        labelValue = "새 비밀번호 확인",
                        placeholderValue = "",
                        passwordVisibleOption = userInfoViewModel.newPasswordVisibility.value,
                        togglePassWordVisibility = {
                            userInfoViewModel.toggleNewPasswordVisibility()
                        })

                    Spacer(modifier = Modifier.height(30.dp))

                    TextInputHelpFieldComponent(

                        errorMessage = userInfoViewModel.passwordChangeErrorMessage.value.asString(LocalContext.current),
                        isError = userInfoViewModel.passwordChangeErrorState.value,

                        )

                    InputButtonComponent(
                        value = "변경하기",
                        onClick = {
                            userInfoViewModel.postPasswordChangeData()
                        },
                        modifier = Modifier.padding(start=15.dp,end=15.dp))

                }
            }

        }
    }


@Composable
fun ChangeEmailComponent(
    userInfoViewModel: UserInfoViewModel,
    navController: NavController
){
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier

                .padding(start = 10.dp, end = 10.dp,bottom=10.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Background_Color2)
                    .height(350.dp)
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                EmailInputFieldComponent(
                    value = "",
                    onValueChange = { },
                    labelValue = "새 이메일",
                    emailCheckValue = false,
                    emailCheckProcess = { /*TODO*/ },
                    isError = false,
                    errorMessage = "",
                    duplicateCheckButtonState = true
                )

                PasswordInputFieldComponent(
                    value = "",
                    onValueChange = {},
                    labelValue = stringResource(id = R.string.password),
                    placeholderValue = stringResource(id = R.string.input_password),
                    passwordVisibleOption = false,
                    togglePassWordVisibility = { /*TODO*/ })



                Spacer(modifier = Modifier.height(30.dp))

                InputButtonComponent(
                    value = "변경하기",
                    onClick = {

                    },
                    modifier = Modifier.padding(start=15.dp,end=15.dp))

            }
        }

    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun ChangeUserInfoComponent(
    userInfoViewModel: UserInfoViewModel,
    navController: NavController
) {
    val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Surface(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier

                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Background_Color2)
                ) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        MainTextComponent(
                            value = stringResource(id = R.string.UserInfoModify),
                            modifier=Modifier
                                .padding(top=20.dp)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        TextInputFieldComponent(
                            value = userInfoViewModel.changeUserData.value.name,
                            onValueChange = {
                                            userInfoViewModel.updateNewName(it)
                            },
                            labelValue = stringResource(id = R.string.name),
                            isError = false,
                            errorMessage="",
                            placeholderValue =  stringResource(id = R.string.input_name),
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        TextInputFieldComponent(
                            value = userInfoViewModel.changeUserData.value.member_school,
                            onValueChange = {
                                userInfoViewModel.updateNewSchool(it)
                            },
                            labelValue = stringResource(id = R.string.schoolName),
                            isError = false,
                            errorMessage="",
                            placeholderValue = stringResource(id = R.string.input_schoolName),
                        )

                        Spacer(modifier = Modifier.height(15.dp))


                        GradeDropDownMenuComponent(
                            onValueChange={
                                userInfoViewModel.updateNewGrade(it)
                            },
                            labelValue=userInfoViewModel.changeUserData.value.member_grade,
                            dropDownMenuOption=userInfoViewModel.gradeDropDownMenuState.value,
                            toggleDropDownMenuOption={
                                userInfoViewModel.toggleGradeDropBox()
                            }
                        )


                        Spacer(modifier = Modifier.height(30.dp))

                        TextInputHelpFieldComponent(
                            errorMessage = "",
                            isError = false,
                        )

                        InputButtonComponent(
                            value = "변경하기",
                            onClick = {

                            },
                            modifier = Modifier.padding(start=15.dp,end=15.dp))

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }



@Preview(showBackground = true)
@Composable
fun UserProfilePreviewComponentPreview() {

    UserProfilePreviewComponent(name = "DK", schoolName = "아산고", grade = 1)
}

@Preview(showBackground = true)
@Composable
fun UserProfileItemComponentPreview() {
    val navController=rememberNavController()
    UserProfileItemComponent(
        ImageVector.vectorResource(R.drawable.user_profile_icon),
        "사용자 정보 변경",
        "",
        navController
    )

}



