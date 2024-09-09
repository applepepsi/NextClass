package com.example.nextclass.appComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.Data.TimeTableData.ClassData
import com.example.nextclass.R
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.utils.ConvertDayOfWeek
import com.example.nextclass.utils.GetSemester
import com.example.nextclass.viewmodel.LoginViewModel
import com.example.nextclass.viewmodel.TimeTableViewModel

@Composable
fun TodaySingleClassComponent(
    singleClassData: ClassData
) {
    Column(
        modifier = Modifier
            .widthIn(150.dp)
            .heightIn(110.dp)
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Color.White)
            .border(
                BorderStroke(
                    0.5.dp,
                    Color.LightGray
                ),
                RoundedCornerShape(13.dp)
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text=singleClassData.title,
            style = TextStyle(
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(top=3.dp, start = 12.dp,end=12.dp)
        )
        Row(
            modifier = Modifier.padding(top=3.dp, start = 12.dp,end=12.dp)
        ){
            Text(
                text= ConvertDayOfWeek.convertDayOfWeekKorea(singleClassData.week),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                ),
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text =if(singleClassData.class_start_time==singleClassData.class_end_time){
                    "${singleClassData.class_start_time}교시"
                }else{
                    "${singleClassData.class_start_time}교시 - ${singleClassData.class_end_time}교시"
                },
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                ),
            )

        }
        Text(
            text=singleClassData.school,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(top=3.dp, start = 12.dp,end=12.dp,bottom=4.dp)
        )

    }
}

@Composable
fun EmptyHomeItemComponent(){
    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()

            .padding(start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Background_Color2)
            .border(
                BorderStroke(
                    0.5.dp,
                    Color.LightGray
                ),
                RoundedCornerShape(13.dp)
            ),
    )
}

@Composable
fun HomeScoreComponent(
    semester:String,
    averageScore:String,
    creditSum:String
){
    Column(
        modifier = Modifier
            .heightIn(125.dp)
            .widthIn(150.dp)
            .background(Background_Color2)

        ,

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(13.dp))
                .background(Color.White)
                .border(
                    BorderStroke(
                        0.5.dp,
                        Color.LightGray
                    ),
                    RoundedCornerShape(13.dp)
                )
                .padding(start=14.dp,end=14.dp,bottom=12.dp,top=12.dp)


        ){
            Text(
                text = buildAnnotatedString {
                    append("평균 학점: ")
                    withStyle(
                        style = SpanStyle(
                            color = Pastel_Red,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(averageScore)
                    }
                },
                fontSize = 15.sp
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = buildAnnotatedString {
                    append("취득 학점: ")
                    withStyle(
                        style = SpanStyle(
                            color = Pastel_Red,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(creditSum)
                    }


                },
                fontSize = 15.sp
            )
        }

        Text(
            text= GetSemester.convertSemester(semester),
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            ),
            modifier = Modifier.padding(top=5.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TodayClassPreview() {
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)


    TodaySingleClassComponent(
        singleClassData = ClassData()
    )

}

@Preview(showBackground = true)
@Composable
fun HomeScorePreview() {
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)


    HomeScoreComponent(
        "2025-1","25","25"
    )

}