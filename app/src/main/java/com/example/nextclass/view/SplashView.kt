package com.example.nextclass.view

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextclass.R
import com.example.nextclass.ui.theme.Pastel_Red

@Composable
fun SplashScreen(){

    Column(
        modifier= Modifier
            .fillMaxSize()
            .background(Color.White).padding(bottom=70.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        Image(
//            imageVector = ImageVector.vectorResource(R.drawable.book_icon),
//            contentDescription = "",
//            modifier = Modifier.size(250.dp)
//
//        )

        Image(
            painter = painterResource(id = R.drawable.main_icon),
            contentDescription = "",
            modifier = Modifier.size(200.dp)
        )

        Text(
            text="다음 수업",
            style = TextStyle(
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom=10.dp)
        )
        Text(
            text="함께하는 고등학교 생활",
            style = TextStyle(
                fontSize = 23.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            ),
        )

    }
}


@Preview
@Composable
fun SplashPreview(){
    SplashScreen()
}