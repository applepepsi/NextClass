package com.example.nextclass.appComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.example.nextclass.R
import com.example.nextclass.ui.theme.Background_Color2

@Composable
fun UserProfilePreviewComponent(

){
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(15.dp))
            .background(Background_Color2)
            .padding(15.dp)
    ) {
        Box(

            modifier = Modifier
//                                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))

                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .align(Alignment.CenterHorizontally)

        ){
            Icon(
                modifier = Modifier
                    .size(65.dp),
                imageVector = ImageVector.vectorResource(R.drawable.user_profile_icon),
                contentDescription = "",
                tint = Color.LightGray,
            )
        }
        Column(
            modifier = Modifier
                .padding(top=5.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                modifier = Modifier
                    .padding(2.dp),
                text = "홍길동",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
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
                color= Color.Gray
            )
            Text(
                modifier = Modifier
                    .padding(2.dp),
                text = "아산 고등학교",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                ),
                color= Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfilePreviewComponentPreview() {
    UserProfilePreviewComponent()
}

