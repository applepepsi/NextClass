package com.example.nextclass.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.appComponent.AppBarTextAndButtonComponent
import com.example.nextclass.appComponent.CheckboxComponent
import com.example.nextclass.appComponent.DescriptionTextComponent
import com.example.nextclass.appComponent.EmailInputFieldComponent
import com.example.nextclass.appComponent.FindFieldComponent

import com.example.nextclass.appComponent.FindIdOrPasswordTextComponent
import com.example.nextclass.appComponent.GradeDropDownMenuComponent
import com.example.nextclass.appComponent.IdInputFieldComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.PasswordInputFieldComponent
import com.example.nextclass.appComponent.RePostPasswordCodeComponent
import com.example.nextclass.appComponent.RememberUserComponent
import com.example.nextclass.appComponent.TermsAndConditionsTextComponent
import com.example.nextclass.appComponent.TextInputFieldComponent
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.appComponent.TopNav
import com.example.nextclass.appComponent.VerifyCodeInputComponent
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun TimetableScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Text(
            text = "wdwdwd",

            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun ScheduleScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Text(
            text = "wdwdwd",

            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun LoginView(loginViewModel: LoginViewModel,navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(100.dp))

        MainTextComponent(value = "로그인",
            modifier=Modifier)

        Spacer(modifier = Modifier.height(15.dp))

        IdInputFieldComponent(
            value = loginViewModel.id.value,
            onValueChange = { loginViewModel.updateId(it) },
            labelValue = stringResource(id = R.string.id),
            idCheckProcess = { loginViewModel.joinIdDuplicateCheck() },
            showTrailingIcon = false
        )

        PasswordInputFieldComponent(
            value = loginViewModel.password.value,
            onValueChange = { loginViewModel.updatePassword(it) },
            labelValue = stringResource(id = R.string.password),
            passwordVisibleOption = loginViewModel.passwordVisibility.value,
            togglePassWordVisibility = { loginViewModel.togglePasswordVisibility() },

            placeholderValue = stringResource(id = R.string.input_password),
        )

        CheckboxComponent(
            checked = loginViewModel.termsCheckBoxState.value,
            onClickCheckBox = {loginViewModel.toggleTermsCheckBoxValue()},
            checkBoxTextComponent = {
                RememberUserComponent(

                )}
        )

        Spacer(modifier = Modifier.height(10.dp))

        FindIdOrPasswordTextComponent(navController)

        Spacer(modifier = Modifier.height(30.dp))

        TextInputHelpFieldComponent(

            errorMessage = loginViewModel.loginFailMessage.value.asString(LocalContext.current),
            isError = loginViewModel.loginFail.value,

        )

        InputButtonComponent(
            value="로그인",
            onClick = {loginViewModel.tryLogin()},
            modifier = Modifier)
    }

}


@Composable
fun JoinView(loginViewModel: LoginViewModel,navController: NavController) {



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) ,

        ) {

        Spacer(modifier = Modifier.height(15.dp))

        MainTextComponent(
            value = "회원가입",
            modifier=Modifier)

        Spacer(modifier = Modifier.height(10.dp))

        EmailInputFieldComponent(
            value = loginViewModel.email.value,
            onValueChange = { loginViewModel.updateEmail(it) },
            labelValue = stringResource(id = R.string.email),
            emailCheckValue = loginViewModel.emailDuplicateCheck.value,
            emailCheckProcess = { loginViewModel.emailDuplicateCheck() },
            isError = loginViewModel.emailInputError.value,
            errorMessage=loginViewModel.emailInputErrorMessage.value.asString(LocalContext.current)
        )

        IdInputFieldComponent(
            value = loginViewModel.joinId.value,
            onValueChange = { loginViewModel.updateJoinId(it) },
            labelValue = stringResource(id = R.string.id),
            idDuplicateCheckValue = loginViewModel.joinIdDuplicateCheck.value,
            idCheckProcess = { loginViewModel.joinIdDuplicateCheck() },
            isError = loginViewModel.joinIdInputError.value,
            errorMessage=loginViewModel.joinIdInputErrorMessage.value.asString(LocalContext.current)

        )

        PasswordInputFieldComponent(
            value = loginViewModel.joinPassword.value,
            onValueChange = { loginViewModel.updateJoinPassword(it) },
            labelValue = stringResource(id = R.string.password),
            passwordVisibleOption = loginViewModel.passwordVisibility.value,
            togglePassWordVisibility = { loginViewModel.togglePasswordVisibility() },
            isError = loginViewModel.passwordInputError.value,
            errorMessage=loginViewModel.passwordInputErrorMessage.value.asString(LocalContext.current),
            placeholderValue = stringResource(id = R.string.input_password),
        )

        PasswordInputFieldComponent(
            value = loginViewModel.passwordConfirm.value,
            onValueChange = { loginViewModel.updatePasswordConfirm(it) },
            labelValue = stringResource(id = R.string.passwordConfirm),
            passwordVisibleOption = loginViewModel.passwordVisibility.value,
            togglePassWordVisibility = { loginViewModel.togglePasswordVisibility() },
            isError = loginViewModel.passwordConfirmInputError.value,
            errorMessage=loginViewModel.passwordConfirmInputErrorMessage.value.asString(LocalContext.current),
            placeholderValue = stringResource(id = R.string.input_passwordConfirm),
        )


        TextInputFieldComponent(
            value = loginViewModel.name.value,
            onValueChange = { loginViewModel.updateName(it) },
            labelValue = stringResource(id = R.string.name),
            isError = loginViewModel.nameInputError.value,
            errorMessage=loginViewModel.nameInputErrorMessage.value.asString(LocalContext.current),
            placeholderValue = stringResource(id = R.string.input_name),
        )


        TextInputFieldComponent(
            value = loginViewModel.schoolName.value,
            onValueChange = { loginViewModel.updateSchoolName(it) },
            labelValue = stringResource(id = R.string.schoolName),
            isError = loginViewModel.schoolNameInputError.value,
            errorMessage=loginViewModel.schoolNameInputErrorMessage.value.asString(LocalContext.current),
            placeholderValue = stringResource(id = R.string.input_schoolName),
        )

        GradeDropDownMenuComponent(
            onValueChange={loginViewModel.updateEntranceYear(it)},
            labelValue=loginViewModel.entranceYear.value,
            dropDownMenuOption=loginViewModel.menuVisibility.value,
            toggleDropDownMenuOption={loginViewModel.toggleMenuVisibility()}
        )



        CheckboxComponent(
            checked = loginViewModel.termsCheckBoxState.value,
            onClickCheckBox = {loginViewModel.toggleTermsCheckBoxValue()},
            checkBoxTextComponent = {
                TermsAndConditionsTextComponent(
                navController =navController
            )}
        )


        Spacer(modifier = Modifier.height(20.dp))

        TextInputHelpFieldComponent(

            errorMessage = loginViewModel.joinFailMessage.value.asString(LocalContext.current),
            isError = loginViewModel.joinFail.value,
        )



        InputButtonComponent(
            value="가입 완료",
            onClick = {loginViewModel.joinComplete()},
            modifier = Modifier)

    }
}

@Composable
fun HomeView(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "wdwdwd",
            fontSize = 40.sp
        )
    }
}

@Composable
fun ForGotPassword(
    loginViewModel: LoginViewModel,
    navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AppBarTextAndButtonComponent(
            value = stringResource(id = R.string.FindPassword),
            navController = navController)
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
                        value = stringResource(id = R.string.FindPassword),
                        modifier=Modifier
                            .padding(top=20.dp)
                    )

                    DescriptionTextComponent(
                        value= stringResource(id = R.string.inputJoinId),
                        modifier=Modifier.padding(start = 5.dp,top=5.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    FindFieldComponent(
                        value = loginViewModel.findPasswordId.value,
                        onValueChange = { loginViewModel.updateForGotPasswordInput(it) },
                        labelValue = stringResource(id = R.string.input_Id),
                        isError = loginViewModel.findFailPassword.value,
                        errorMessage=loginViewModel.findFailPasswordMessage.value.asString(LocalContext.current),
                        placeholderValue = stringResource(id = R.string.id),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextInputHelpFieldComponent(
                        errorMessage = loginViewModel.findFailIdMessage.value.asString(LocalContext.current),
                        isError = loginViewModel.findFailId.value,
                    )

                    InputButtonComponent(
                        value = stringResource(id = R.string.FindPassword),
                        onClick = { loginViewModel.findPassword() },
                        modifier = Modifier.padding(start=15.dp,end=15.dp))

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }


}

@Composable
fun ForGotId(
    loginViewModel: LoginViewModel,
    navController: NavController) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        AppBarTextAndButtonComponent(value = stringResource(id = R.string.FindId),
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
                        value = stringResource(id = R.string.FindId),
                        modifier=Modifier
                            .padding(top=20.dp)
                    )

                    DescriptionTextComponent(
                        value= stringResource(id = R.string.inputJoinEmail),
                        modifier=Modifier.padding(start = 5.dp,top=5.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    FindFieldComponent(
                        value = loginViewModel.findIdEmail.value,
                        onValueChange = { loginViewModel.updateForGotIdInput(it) },
                        labelValue = stringResource(id = R.string.input_email),
                        placeholderValue = stringResource(id = R.string.email),
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextInputHelpFieldComponent(
                        errorMessage = loginViewModel.findFailIdMessage.value.asString(LocalContext.current),
                        isError = loginViewModel.findFailId.value,
                    )

                    InputButtonComponent(
                        value = "아이디 찾기",
                        onClick = { loginViewModel.findId() },
                        modifier = Modifier.padding(start=15.dp,end=15.dp))

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun TermsAndConditionsView(
    loginViewModel: LoginViewModel,
    navController: NavController)  {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        AppBarTextAndButtonComponent(value = stringResource(id = R.string.TermsAndConditions),
            navController=navController)
    }
}

@Composable
fun FindIdResult(
    loginViewModel: LoginViewModel,
    navController: NavController)  {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        AppBarTextAndButtonComponent(value = stringResource(id = R.string.FindId),
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
                        value= stringResource(id = R.string.FindIdComplete),
                        modifier=Modifier
                            .padding(top=20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextInputFieldComponent(
                        value = loginViewModel.findId.value,
                        onValueChange = { loginViewModel.updateName(it) },
                        labelValue = "아이디를 확인해 주세요",
                        readOnly = true
                    )

                    InputButtonComponent(
                        value = "로그인 화면으로 돌아가기",
                        onClick = { loginViewModel.findId() },
                        modifier = Modifier.padding(start=15.dp,end=15.dp))

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun InsertPasswordCodeView(
    loginViewModel: LoginViewModel,
    navController: NavController)  {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        AppBarTextAndButtonComponent(value = stringResource(id = R.string.FindPassword),
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
                            loginViewModel.updateVerifyCode(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                    RePostPasswordCodeComponent(
                    )

                    Spacer(modifier = Modifier.height(15.dp))


                    TextInputHelpFieldComponent(
                        errorMessage = loginViewModel.verifyCodeInputErrorMessage.value.asString(LocalContext.current),
                        isError = loginViewModel.verifyCodeInputError.value,
                    )



                    InputButtonComponent(
                        value = "다음",
                        onClick = { loginViewModel.submitVerifyCode() },
                        modifier = Modifier.padding(start=15.dp,end=15.dp),
                        showImage = true
                    )



                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}



//프리뷰는 hilt를 쓰면 의존성주입을 초기화 하지 않는다고 해서 테스트 용으로 하나 더 만듬
@Preview(showBackground = true)
@Composable
fun FindPasswordPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()


    NextClassTheme {
//        FindIdResult(loginViewModel,navController)
//        LoginView(loginViewModel)
//        ForGotId(loginViewModel)
//        TermsAndConditionsView(loginViewModel,navController)
        InsertPasswordCodeView(loginViewModel,navController)
    }
}


@Preview(showBackground = true)
@Composable
fun FindIdPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    NextClassTheme {
//        LoginView(loginViewModel)
//        ForGotId(loginViewModel)
        TopNav(loginViewModel)
    }
}