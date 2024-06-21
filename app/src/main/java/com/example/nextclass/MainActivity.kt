package com.example.nextclass

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.ClassData
import com.example.nextclass.appComponent.BasicClass
import com.example.nextclass.appComponent.TopNav
import com.example.nextclass.appComponent.sampleEvents
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.view.InsertPasswordCodeView


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val testRepository = TestRepository()
            val loginViewModel = LoginViewModel(testRepository)

            val navController = rememberNavController()

            NextClassTheme {
                Greeting()
//                BasicClass(sampleEvents)
//                Greeting()
//            InsertPasswordCodeView(loginViewModel = loginViewModel, navController = navController)
            }
        }
    }
}

@Composable
fun Greeting() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = hiltViewModel()
    TopNav()

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NextClassTheme {

    }
}












