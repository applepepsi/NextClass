package com.example.nextclass.appComponent

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.utils.TokenManager
import com.example.nextclass.utils.UserInfoManager
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.UserInfoViewModel

@Composable
fun UserProfilePreviewComponent(
    name:String,
    schoolName:String,
    grade:Int,
){

    val gradeText=if(grade==0) "졸업생" else "$grade 학년"

    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(13.dp))
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
                    text = gradeText,
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
    navController:NavController= rememberNavController(),
    logOut:Boolean=false,
    onClick:()->Unit={}
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
                if(logOut){
                    onClick()
                }else{
                    navController.navigate(address) {
//                        launchSingleTop = true
                    }
                }
            },
        )
    }

}


@Composable
fun UserProfileSwitchItemComponent(
    image:ImageVector,
    text:String,
    switchState:Boolean,
    switchClick:()->Unit
){
    Row(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
//            .background(Color.Black)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier,
        ) {


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
        Switch(
            checked = switchState,
            onCheckedChange = { switchClick() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Pastel_Red,

                checkedTrackColor = Color.White,
                uncheckedThumbColor = Color.Gray,
                uncheckedTrackColor = Color.LightGray
            ),
            modifier = Modifier.scale(0.8f)
        )
    }
}

@Composable
fun CustomSwitch(
    scale: Float = 2f,
    width: Dp = 36.dp,
    height: Dp = 20.dp,
    strokeWidth: Dp = 2.dp,
    checkedTrackColor: Color = Color(0xFF35898F),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    gapBetweenThumbAndTrackEdge: Dp = 4.dp,
    switchON: Boolean,
    switchClick:()->Unit
) {



    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (switchON)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() },
        label = ""
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        switchClick()
                    }
                )
            }
    ) {
        // Track
        drawRoundRect(
            color = if (switchON) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = strokeWidth.toPx())
        )

        // Thumb
        drawCircle(
            color = if (switchON) checkedTrackColor else uncheckedTrackColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }


}


@SuppressLint("SuspiciousIndentation")
@Composable
fun ChangePasswordComponent(
    userInfoViewModel: UserInfoViewModel,
    navController: NavController,
    loginViewModel: LoginViewModel
){
    val scrollState = rememberScrollState()
    val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            ProgressBarFullComponent(state = userInfoViewModel.loading.value)
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
                        value = userInfoViewModel.changePasswordData.value.existing_password,
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
                        value = userInfoViewModel.changePasswordData.value.new_password,
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
            if(userInfoViewModel.passwordChangeResult.value){
                loginViewModel.logOut()
                TokenManager.clearToken(context = context)
                UserInfoManager.clearUserInfo(context)
            }
        }
    }


@Composable
fun ChangeEmailComponent(
    userInfoViewModel: UserInfoViewModel,
    navController: NavController
){
    val scrollState = rememberScrollState()


    LaunchedEffect(Unit) {
        userInfoViewModel.resetChangeEmailData()
    }
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
                    .height(420.dp)
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start,
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                EmailInputFieldComponent(
                    value = userInfoViewModel.changeEmail.value.email,
                    onValueChange = {
                        userInfoViewModel.updateNewEmail(it)
                    },
                    labelValue = "새 이메일",
                    emailCheckValue = userInfoViewModel.emailDuplicateCheck.value,
                    emailCheckProcess = { userInfoViewModel.emailDuplicateCheck() },
                    isError = userInfoViewModel.emailInputError.value,
                    errorMessage = userInfoViewModel.emailInputErrorMessage.value.asString(LocalContext.current),
                    duplicateCheckButtonState = userInfoViewModel.emailDuplicateButtonState.value
                )

                PasswordInputFieldComponent(
                    value = userInfoViewModel.changeEmail.value.password!!,
                    onValueChange = {
                        userInfoViewModel.updateOldPasswordOfChangeEmail(it)
                    },
                    labelValue = stringResource(id = R.string.password),
                    placeholderValue = stringResource(id = R.string.input_password),
                    passwordVisibleOption = userInfoViewModel.passwordVisibility.value,
                    togglePassWordVisibility = { userInfoViewModel.togglePasswordVisibility() })



                Spacer(modifier = Modifier.height(10.dp))

                TextInputHelpFieldComponent(

                    errorMessage = userInfoViewModel.changeEmailErrorMessage.value.asString(LocalContext.current),
                    isError = userInfoViewModel.changeEmailErrorState.value,
                    )

                InputButtonComponent(
                    value = "변경하기",
                    onClick = {
                        userInfoViewModel.getChangeEmailVerifyCode()
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
                            },
                        )


                        Spacer(modifier = Modifier.height(30.dp))

                        TextInputHelpFieldComponent(
                            errorMessage = userInfoViewModel.userInfoChangeErrorMessage.value.asString(LocalContext.current),
                            isError = userInfoViewModel.userInfoChangeErrorState.value,
                        )

                        InputButtonComponent(
                            value = "변경하기",
                            onClick = {
                                userInfoViewModel.postUserInfoChangeData()
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


@Preview(showBackground = true)
@Composable
fun UserProfileSwitchItemComponentPreview() {
    val navController=rememberNavController()
    UserProfileSwitchItemComponent(
        ImageVector.vectorResource(R.drawable.user_profile_icon),
        "사용자 정보 변경",
        switchState = false,
        {}
    )

}


