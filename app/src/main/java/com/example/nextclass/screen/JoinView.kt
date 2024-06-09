package com.example.nextclass.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun JoinView(loginViewModel: LoginViewModel) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "회원가입", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = loginViewModel.email.value,
            onValueChange = { loginViewModel.updateEmail(it) },
            label = { Text(text = "이메일") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginViewModel.id.value,
            onValueChange = { loginViewModel.updateId(it) },
            label = { Text(text = "아이디") },
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginViewModel.password.value,
            onValueChange = { loginViewModel.updatePassword(it) },
            label = { Text(text = "비밀번호") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginViewModel.passwordConfirm.value,
            onValueChange = { loginViewModel.updatePasswordConfirm(it) },
            label = { Text(text = "비밀번호 확인") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginViewModel.name.value,
            onValueChange = { loginViewModel.updateName(it) },
            label = { Text(text = "이름") },
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginViewModel.schoolName.value,
            onValueChange = { loginViewModel.updateSchoolName(it) },
            label = { Text(text = "학교명") },
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginViewModel.entranceYear.value,
            onValueChange = { loginViewModel.updateEntranceYear(it) },
            label = { Text(text = "입학 년도") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            loginViewModel.setLoginInput()
        }) {
            Text(text = "가입 완료")
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