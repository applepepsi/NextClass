package com.example.nextclass.appComponent

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red

@Composable
fun MainTextComponent(
    value: String,
    modifier: Modifier,
    color:Color= Color.Black
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
        color= color,
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
        modifier = modifier,
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        ),
        color= Color.Black,
        textAlign = TextAlign.Left
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun CountDownComponent(
    countDown: Long=5,
    countDownState:Boolean=false
) {


    val minutes = countDown / 60
    val seconds = countDown % 60

        Log.d("카운트다운", countDown.toString())

        Text(
            text = "남은 시간 ${String.format("%02d:%02d", minutes, seconds)}",
            modifier = Modifier,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,

            ),
            color= Pastel_Red,
            textAlign = TextAlign.Center
        )

}

@Composable
fun ChanceCountComponent(
    remainingChance: Int=5,
) {

    Text(
        text = "${remainingChance}번 남았습니다.",
        modifier = Modifier,
        style = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,

        ),
        color= Pastel_Red,
        textAlign = TextAlign.Center
    )

}



@Composable
fun AppBarTextAndButtonComponent(
    value: String,
    buttonText:String="",
    navRoute:String="",
    navController: NavController,
    showLeftButton:Boolean=true,
    showRightButton:Boolean=false,
    customRightButton:Boolean=false,
    customRightButtonIcon:ImageVector=ImageVector.vectorResource(R.drawable.arrow_back),
    rightButtonClick:()->Unit={},

) {
    val background =  Background_Color2
    Column(modifier = Modifier) {

        Box(
            modifier = Modifier
            ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(start = 15.dp)
                    .align(Alignment.CenterStart)
            ) {
                if(showLeftButton){
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
            }
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(end = 15.dp)
                    .align(Alignment.CenterEnd)
            ) {
                if (showRightButton) {
                    Box(
                        modifier = Modifier
                            .background(background)
                            .width(100.dp)
                            .height(35.dp)
                            .clickable(
                                onClick = {
                                    navController.navigate(navRoute)
                                },
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 18.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = buttonText,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Normal,
                                ),
                                color = Color.Black,
                                textAlign = TextAlign.Start
                            )
                            Icon(
                                modifier = Modifier.size(17.dp),
                                imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                                contentDescription = "",
                                tint = Color.Unspecified,
                            )
                        }
                    }
                }
                if(customRightButton){
                        Icon(
                            modifier = Modifier
                                .size(30.dp)
//                                .clickable { rightButtonClick() }
                                .clickable(
                                    onClick = {
                                        navController.navigate(navRoute)
                                    },
                                ),
                            imageVector = customRightButtonIcon,
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

    var navController= rememberNavController()

    NextClassTheme {
        AppBarTextAndButtonComponent(value = "test", navController = navController, customRightButtonIcon =ImageVector.vectorResource(R.drawable.plus_icon), rightButtonClick = {} )
    }
}