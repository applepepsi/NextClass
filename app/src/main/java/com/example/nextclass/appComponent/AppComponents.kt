package com.example.nextclass.appComponent


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DismissValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon


import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.nextclass.items.GradeDropDownItems
import com.example.nextclass.screen.JoinView
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.viewmodel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,

){

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = {Text(text = labelValue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            cursorColor = Color.Black,
        ),
        keyboardOptions=KeyboardOptions.Default,
        value = value,
        onValueChange = onValueChange,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    passwordVisibleOption:Boolean,
    togglePassWordVisibility:()->Unit
    ){

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = {Text(text = labelValue)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            cursorColor = Color.Black,
        ),
        keyboardOptions=KeyboardOptions(keyboardType = KeyboardType.Password),
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            val iconImage=if(passwordVisibleOption){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }

            val description=if(passwordVisibleOption){
                "비빌번호 보이기"
            }else{
                "비밀번호 숨기기"
            }

            IconButton(onClick = togglePassWordVisibility) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        visualTransformation = if(passwordVisibleOption){
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        }
    )

}

@Composable
fun GradeDropDownMenuComponent(
    onValueChange: (String) -> Unit,
    labelValue: String,
    dropDownMenuOption:Boolean,
    toggleDropDownMenuOption:()->Unit
    )  {


    Column {
        Button(onClick = toggleDropDownMenuOption) {
            Text(text = labelValue)
        }

    }

    DropdownMenu(
        expanded = dropDownMenuOption,
        onDismissRequest = {
            toggleDropDownMenuOption()
        }
    ) {
        // 드롭 다운 메뉴의 각 항목을 나타냄
        DropdownMenuItem(
            text = { Text(text = "1학년") },
            onClick = {
                onValueChange("1학년")
                toggleDropDownMenuOption()
            })
        DropdownMenuItem(
            text = { Text(text = "2학년") },
            onClick = {
                onValueChange("2학년")
                toggleDropDownMenuOption()
            })
        DropdownMenuItem(
            text = { Text(text = "3학년") },
            onClick = {
                onValueChange("3학년")
                toggleDropDownMenuOption()
            })
        DropdownMenuItem(
            text = { Text(text = "졸업생") },
            onClick = {
                onValueChange("졸업생")
                toggleDropDownMenuOption()
            })
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NextClassTheme {
//        GradeDropDownMenuComponent()
    }
}

@Composable
fun TextInputHelpFieldComponent(loginViewModel:LoginViewModel){

    Text(
        text = "",
        fontSize = 10.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 5.dp),
    )
}