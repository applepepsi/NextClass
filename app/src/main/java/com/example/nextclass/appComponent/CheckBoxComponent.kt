package com.example.nextclass.appComponent

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Navi_Green
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.viewmodel.LoginViewModel



@Composable
fun CheckboxComponent(
    checked:Boolean,
    onClickCheckBox: () -> Unit,
    checkBoxTextComponent: @Composable () -> Unit,

) {

    Row(
        modifier = Modifier
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




@Preview(showBackground = true)
@Composable
fun CheckBoxPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

//    NextClassTheme {
//        CheckboxComponent(
//            checked = loginViewModel.termsCheckBoxState.value,
//            onClickCheckBox = {loginViewModel.toggleTermsCheckBoxValue()},
//            checkBoxTextComponent = {
//                TermsAndConditionsTextComponent(
//
//                )}
//        )
//    }
}