package com.example.nextclass.appComponent

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nextclass.R
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.view.ForGotId
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun FindFieldComponent(
    placeholderValue:String,
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    isError: Boolean = false,
    errorMessage: String = "",

){

    Log.d("isError", isError.toString())

    Text(
        text = labelValue,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        color = Color.DarkGray,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 5.dp, top = 10.dp,end=10.dp)
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small)
            .padding(start = 20.dp, end = 20.dp),

        placeholder = { Text(text = placeholderValue) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            errorContainerColor = Color.White,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,

            ),
        keyboardOptions = KeyboardOptions.Default,
        value = value,
        onValueChange = onValueChange,

        //에러 처리는이걸로
        isError = isError,
        shape = RoundedCornerShape(15.dp),
    )
    //에러라면 경고문을 띄움
    if (isError) {
        Text(
            text = errorMessage,
            color = Pastel_Red,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(start = 16.dp, top = 5.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun FindPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    NextClassTheme {
//        FindFieldComponent(
//            value = "가입 완료",
//            onClick = { loginViewModel.joinComplete() })
//        ForGotId(loginViewModel)
    }
}