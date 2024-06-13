package com.example.nextclass.screen

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nextclass.appComponent.PasswordInputFieldComponent
import com.example.nextclass.appComponent.TextInputFieldComponent
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.R
import com.example.nextclass.appComponent.CheckboxComponent
import com.example.nextclass.appComponent.EmailInputFieldComponent
import com.example.nextclass.appComponent.GradeDropDownMenuComponent
import com.example.nextclass.appComponent.IdInputFieldComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.MainTextComponent
import com.example.nextclass.appComponent.TextInputHelpFieldComponent
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color

@Composable
fun JoinView(loginViewModel: LoginViewModel) {



        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) ,

        ) {

            Spacer(modifier = Modifier.height(15.dp))

            MainTextComponent(value = "회원가입")

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
                value = loginViewModel.id.value,
                onValueChange = { loginViewModel.updateId(it) },
                labelValue = stringResource(id = R.string.id),
                idDuplicateCheckValue = loginViewModel.idDuplicateCheck.value,
                idCheckProcess = { loginViewModel.idDuplicateCheck() },
                isError = loginViewModel.idInputError.value,
                errorMessage=loginViewModel.idInputErrorMessage.value.asString(LocalContext.current)

            )

            PasswordInputFieldComponent(
                value = loginViewModel.password.value,
                onValueChange = { loginViewModel.updatePassword(it) },
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
                onClickCheckBox = {loginViewModel.toggleTermsCheckBoxValue()}
            )


            Spacer(modifier = Modifier.height(20.dp))

            TextInputHelpFieldComponent(

                errorMessage = loginViewModel.joinFailMessage.value.asString(LocalContext.current),
                isError = loginViewModel.joinFail.value,
            )



            InputButtonComponent(value="가입 완료", onClick = {loginViewModel.joinComplete()})

        }
    }




//프리뷰는 hilt를 쓰면 의존성주입을 초기화 하지 않는다고 해서 테스트 용으로 하나 더 만듬
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    NextClassTheme {
        JoinView(loginViewModel)
    }
}