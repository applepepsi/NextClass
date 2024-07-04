package com.example.nextclass.appComponent

import android.location.Address
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Feldgrau
import com.example.nextclass.ui.theme.Pastel_Red

@Composable
fun UserProfilePreviewComponent(

){
    Row(
        modifier = Modifier
            .padding(start=10.dp,end=10.dp)
            .clip(RoundedCornerShape(35.dp))
            .fillMaxWidth()
            .height(120.dp)
//            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(15.dp))
            .background(Pastel_Red)
            .padding(start=25.dp),
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
                text = "홍길동",
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
                    text = "아산 고등학교",
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
                    text = "1 학년",
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
    navController:NavController,
){
    Row(
        modifier = Modifier
            .padding(start=30.dp,end=30.dp)
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
                navController.navigate(address) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            },
        )
    }

}


@Preview(showBackground = true)
@Composable
fun UserProfilePreviewComponentPreview() {
    UserProfilePreviewComponent()
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