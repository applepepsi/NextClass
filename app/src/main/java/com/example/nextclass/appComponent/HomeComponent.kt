package com.example.nextclass.appComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
            .padding(start=10.dp,end=10.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Background_Color2),
    ) {
        Text(
            text=singleClassData.title+"wdwdwdwd",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
        )
        Row(){
            Text(
                text=singleClassData.week,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
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
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
            )

        }

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