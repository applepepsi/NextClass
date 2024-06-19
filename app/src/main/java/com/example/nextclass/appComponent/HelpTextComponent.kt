package com.example.nextclass.appComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.nextclass.R
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Navi_Green
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.view.JoinView
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun MainTextComponent(
    value: String,
    modifier: Modifier
) {

    Text(
        text=value,
        modifier = modifier
            .fillMaxWidth()
            .heightIn()
            .padding(start = 20.dp, top = 15.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        ),
        color= Color.Black,
        textAlign = TextAlign.Left
    )
}

@Composable
fun DescriptionTextComponent(
    value: String,
    modifier: Modifier
) {

    Text(
        text=value,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 15.dp),
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        color= Color.Black,
        textAlign = TextAlign.Left
    )
}

@Composable
fun AppBarTextAndButtonComponent(
    value: String,
    navController: NavController
) {
    val background =  Background_Color2
    Column(modifier = Modifier) {

        Box(
            modifier = Modifier
            ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(start=15.dp)
                    .align(Alignment.CenterStart)
            ) {
                Box(
                    modifier = Modifier

                        .background(background)
                        .width(50.dp)
                        .height(35.dp)
                        .clickable(
                            onClick = {
                                navController.navigateUp()
                            },
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier

                            .align(Alignment.Center)
                            .size(20.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                }
            }
            Text(
                text=value,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
                color= Color.Black,
                textAlign = TextAlign.Center
            )
        }

    }
}




@Preview(showBackground = true)
@Composable
fun HelpTextPreview() {


    NextClassTheme {
//        AppBarTextAndButtonComponent(value = "아이디 찾기") {
//
//        }
    }
}