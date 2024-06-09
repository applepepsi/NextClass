package com.example.nextclass

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.appComponent.GradeDropDownMenuComponent
import com.example.nextclass.screen.LoginNav

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NextClassTheme {
                Greeting()
//                GradeDropDownMenuComponent()
            }
        }
    }
}

@Composable
fun Greeting() {
//    BottomNav()
    LoginNav()
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NextClassTheme {
//        BottomNav()
        LoginNav()
    }
}

@Composable
fun LoginView(loginViewModel: LoginViewModel) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "다음 수업", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = loginViewModel.id.value,
            onValueChange = { loginViewModel.updateId(it) },
            label = { Text(text = "아이디") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = loginViewModel.password.value,
            onValueChange = { loginViewModel.updatePassword(it) },
            label = { Text(text = "비밀번호") },
            visualTransformation = PasswordVisualTransformation()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(all = 8.dp),

        ) {
            TextButton(onClick = {
                Log.d("회원가입 버튼 터치","회원가입 버튼 터치" )

            }) {
                Text(text = "회원가입")
            }

            TextButton(onClick = {
                Log.d("아이디 찾기 버튼 터치","아이디 찾기 버튼 터치")

            }) {
                Text(text = "아이디 찾기")
            }

            TextButton(onClick = {
                Log.d("비밀번호 찾기 버튼 터치","비밀번호 찾기 버튼 터치")

            }) {
                Text(text = "비밀번호 찾기")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                loginViewModel.setLoginInput()
            }) {
                Text(text = "로그인")
            }


    }

}










