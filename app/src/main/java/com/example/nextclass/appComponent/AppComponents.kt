package com.example.nextclass.appComponent


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachEmail
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MarkEmailRead

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DismissValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import com.example.nextclass.R

import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.nextclass.items.GradeDropDownItems
import com.example.nextclass.screen.JoinView
import com.example.nextclass.ui.theme.Feldgrau
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.ui.theme.White_Smoke
import com.example.nextclass.viewmodel.LoginViewModel


@Composable
fun MainTextComponent(
    value: String,
    ) {

    Text(
        text=value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        ),
        color= Color.Black,
        textAlign = TextAlign.Left
    )
}



@Composable
fun TextInputFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    isError:Boolean,
    errorMessage:String,
){

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = {Text(text = labelValue)},
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
        ),
        keyboardOptions=KeyboardOptions.Default,
        value = value,
        onValueChange = onValueChange,
        isError=isError
    )
    if(isError){
        Text(
            text=errorMessage,
            color= Pastel_Red,
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
fun EmailInputFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    emailCheckValue:Boolean,
    emailCheckProcess:()->Unit,
    isError:Boolean,
    errorMessage:String,
){

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = {Text(text = labelValue)},
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
        ),
        keyboardOptions=KeyboardOptions.Default,
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            val iconImage=if(emailCheckValue){
                Icons.Filled.MarkEmailRead
            }else{
                Icons.Filled.Email
            }

            val description=if(emailCheckValue){
                "사용 가능한 이메일 입니다."
            }else{
                "이미 사용중인 이메일 입니다."
            }

            IconButton(onClick = emailCheckProcess) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        isError=isError
    )
    if(isError){
        Text(
            text=errorMessage,
            color= Pastel_Red,
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
fun IdInputFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    idDuplicateCheckValue:Boolean,
    idCheckProcess:()->Unit,
    isError:Boolean,
    errorMessage:String,
){

    Log.d("isError", isError.toString())
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = {Text(text = labelValue)},
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedBorderColor = Color.Black,
        ),
        keyboardOptions=KeyboardOptions.Default,
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            val iconImage=if(idDuplicateCheckValue){
                Icons.Filled.MarkEmailRead
            }else{
                Icons.Filled.Email
            }

            val description=if(idDuplicateCheckValue){
                "사용 가능한 이메일 입니다."
            }else{
                "이미 사용중인 이메일 입니다."
            }

            IconButton(onClick = idCheckProcess) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        //에러 처리는이걸로
        isError=isError
    )
    //에러라면 경고문을 띄움
    if(isError){
        Text(
            text=errorMessage,
            color= Pastel_Red,
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
fun PasswordInputFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    passwordVisibleOption:Boolean,
    togglePassWordVisibility:()->Unit,
    isError:Boolean,
    errorMessage:String,
    ){

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small),
        label = {Text(text = labelValue)},
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
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
        } ,
        isError=isError
    )
    if(isError){
        Text(
            text=errorMessage,
            color= Pastel_Red,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier.padding(start = 16.dp, top = 5.dp)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeDropDownMenuComponent(
    onValueChange: (String) -> Unit,
    labelValue: String,
    dropDownMenuOption: Boolean,
    toggleDropDownMenuOption: () -> Unit
) {


        ExposedDropdownMenuBox(
            expanded = dropDownMenuOption,
            onExpandedChange = { toggleDropDownMenuOption() }) {
            OutlinedTextField(
                value = labelValue,
                onValueChange = onValueChange,
                readOnly = true,

                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownMenuOption)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .padding(vertical = 8.dp)

            )

            ExposedDropdownMenu(
                expanded = dropDownMenuOption,
                onDismissRequest = { toggleDropDownMenuOption() }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "1학년")
                    },
                    onClick = {
                        onValueChange("1학년")
                        toggleDropDownMenuOption()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "2학년")
                    },
                    onClick = {
                        onValueChange("2학년")
                        toggleDropDownMenuOption()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "3학년")
                    },
                    onClick = {
                        onValueChange("3학년")
                        toggleDropDownMenuOption()
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "졸업생")
                    },
                    onClick = {
                        onValueChange("졸업생")
                        toggleDropDownMenuOption()
                    }
                )
            }
        }
    }

@Composable
fun InputButtonComponent(
    value: String,
    onClick:() -> Unit
    ){

    Button(
        onClick = { onClick()},
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding= PaddingValues(),
        colors=ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp)
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
//                brush = Brush.horizontalGradient(listOf(Pastel_Red, White_Smoke)),
                color= Pastel_Red,
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text=value,
            fontSize=18.sp,
            color= Color.White,
            fontWeight = FontWeight.Bold
            )
        }
    }

}


@Composable
fun TextInputHelpFieldComponent(){

    Text(
        text = "",
        fontSize = 10.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 5.dp),
    )
}