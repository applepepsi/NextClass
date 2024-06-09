package com.example.nextclass.screen

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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextclass.appComponent.PasswordInputFieldComponent
import com.example.nextclass.appComponent.TextInputFieldComponent
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.R
import com.example.nextclass.appComponent.GradeDropDownMenuComponent
import com.example.nextclass.appComponent.InputButtonComponent
import com.example.nextclass.appComponent.MainTextComponent

@Composable
fun JoinView(loginViewModel: LoginViewModel) {



    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(60.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            MainTextComponent(value = "회원가입")

            Spacer(modifier = Modifier.height(40.dp))

            TextInputFieldComponent(
                value = loginViewModel.email.value,
                onValueChange = { loginViewModel.updateEmail(it) },
                labelValue = stringResource(id = R.string.email),
            )

            TextInputFieldComponent(
                value = loginViewModel.id.value,
                onValueChange = { loginViewModel.updateId(it) },
                labelValue = stringResource(id = R.string.id),
            )

            PasswordInputFieldComponent(
                value = loginViewModel.password.value,
                onValueChange = { loginViewModel.updatePassword(it) },
                labelValue = stringResource(id = R.string.password),
                passwordVisibleOption = loginViewModel.passwordVisibility.value,
                togglePassWordVisibility = { loginViewModel.togglePasswordVisibility() }
            )

            PasswordInputFieldComponent(
                value = loginViewModel.passwordConfirm.value,
                onValueChange = { loginViewModel.updatePasswordConfirm(it) },
                labelValue = stringResource(id = R.string.passwordConfirm),
                passwordVisibleOption = loginViewModel.passwordVisibility.value,
                togglePassWordVisibility = { loginViewModel.togglePasswordVisibility() }
            )


            TextInputFieldComponent(
                value = loginViewModel.name.value,
                onValueChange = { loginViewModel.updateName(it) },
                labelValue = stringResource(id = R.string.name),
            )



            TextInputFieldComponent(
                value = loginViewModel.schoolName.value,
                onValueChange = { loginViewModel.updateSchoolName(it) },
                labelValue = stringResource(id = R.string.schoolName),
            )

            GradeDropDownMenuComponent(
                onValueChange={loginViewModel.updateEntranceYear(it)},
                labelValue=loginViewModel.entranceYear.value,
                dropDownMenuOption=loginViewModel.menuVisibility.value,
                toggleDropDownMenuOption={loginViewModel.toggleMenuVisibility()}
            )

            InputButtonComponent("가입 완료")

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NextClassTheme {
        JoinView(LoginViewModel())

    }
}