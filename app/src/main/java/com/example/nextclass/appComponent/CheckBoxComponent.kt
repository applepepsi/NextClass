package com.example.nextclass.appComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.ui.theme.Navi_Green
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.viewmodel.LoginViewModel



@Composable
fun CheckboxComponent(
    checked:Boolean,
    onClickCheckBox: () -> Unit,
    checkBoxTextComponent: @Composable () -> Unit,
    modifier: Modifier=Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 15.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Checkbox(checked=checked,
            onCheckedChange={
                onClickCheckBox()
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Pastel_Red,
                uncheckedColor = Color.Gray,
                checkmarkColor = Navi_Green
            ))

        checkBoxTextComponent()
    }

}

@Composable
fun CommentCheckboxComponent(
    checked:Boolean,
    onClickCheckBox: () -> Unit,

    modifier: Modifier=Modifier
) {

    Row(
        modifier = modifier

        ,
        verticalAlignment = Alignment.CenterVertically,
    ){

        val iconImage=if(checked){
            ImageVector.vectorResource(R.drawable.custom_checkbox_on)
        }else{
            ImageVector.vectorResource(R.drawable.custom_checkbox_off)
        }


        Image(
            contentDescription = null,
            imageVector=iconImage,
            modifier = Modifier.size(20.dp).clickable {
                onClickCheckBox()
            }
        )
        
        Spacer(modifier = Modifier.width(3.dp))
        
        Text(
            text = "익명",

            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
        )



    }


}


@Preview(showBackground = true)
@Composable
fun CheckBoxPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    NextClassTheme {
        CommentCheckboxComponent(
            checked = true,
            onClickCheckBox = {},
            modifier = Modifier
        )

    }
}