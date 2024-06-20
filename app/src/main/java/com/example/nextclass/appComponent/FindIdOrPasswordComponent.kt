package com.example.nextclass.appComponent

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.view.ForGotId
import com.example.nextclass.view.InsertPasswordCodeView
import com.example.nextclass.viewmodel.LoginViewModel

@Composable
fun FindFieldComponent(
    placeholderValue:String="",
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String="",
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

@Composable
fun VerifyCodeInputComponent(
    onValueChange: (String) -> Unit,) {
//    FocusRequester로 하나가 입력될때마다 다음으로 이동
    val focusRequesters = remember { List(4) { FocusRequester() } }
    val code = remember { mutableStateListOf("", "", "", "") }


    Row(
        //Arrangement.spacedBy(15.dp)로 입력창마다 간격을 줌
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        for (i in 0 until 4) {
            OutlinedTextField(
                modifier = Modifier
                    .height(80.dp)
                    .width(65.dp)
                    .focusRequester(focusRequesters[i])
                ,
                value = code[i],
                onValueChange = { newValue ->
                    if (newValue.length <= 1) {
                        code[i] = newValue
                        onValueChange(code.joinToString(""))
                        if (newValue.isNotEmpty() && i < 3) {
                            focusRequesters[i + 1].requestFocus()
                        }
                    }
                },
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "- - -",
                            textAlign = TextAlign.Center
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                shape = RoundedCornerShape(13.dp),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                ),
                singleLine = true,
                maxLines = 1,

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FindPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    val navController = rememberNavController()


    NextClassTheme {
//        FindIdResult(loginViewModel,navController)
//        LoginView(loginViewModel)
//        ForGotId(loginViewModel)
//        TermsAndConditionsView(loginViewModel,navController)
        InsertPasswordCodeView(loginViewModel,navController)
    }
}