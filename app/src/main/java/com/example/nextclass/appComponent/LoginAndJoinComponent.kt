package com.example.nextclass.appComponent


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.ui.theme.NextClassTheme
import com.example.nextclass.ui.theme.Pastel_Red
import com.example.nextclass.view.JoinView
import com.example.nextclass.viewmodel.LoginViewModel




@Composable
fun NormalTextComponent(
    value: String,
) {

    Text(
        text=value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .padding(start = 15.dp, top = 15.dp),
        style = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
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
    placeholderValue:String="",
    labelValue: String="",
    isError:Boolean=false,
    errorMessage:String="",
    readOnly:Boolean=false
){

    Text(
        text = labelValue,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        color = Color.DarkGray,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, bottom = 5.dp, top = 10.dp)
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
        keyboardOptions=KeyboardOptions.Default,
        value = value,
        onValueChange = onValueChange,
        isError=isError,
        shape = RoundedCornerShape(15.dp),
        readOnly=readOnly
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
            modifier = Modifier.padding(start = 22.dp, top = 5.dp)
        )
    }

}

@Composable
fun LoginInputFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderValue:String,
    labelValue: String,
){

    Text(
        text = labelValue,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        color = Color.DarkGray,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, bottom = 5.dp, top = 10.dp)
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
        keyboardOptions=KeyboardOptions.Default,
        value = value,
        onValueChange = onValueChange,

        shape = RoundedCornerShape(15.dp),
    )

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
    duplicateCheckButtonState:Boolean,
){

    Text(
        text = labelValue,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        color = Color.DarkGray,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, bottom = 5.dp, top = 10.dp)
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShape.small)
            .padding(start = 20.dp, end = 20.dp),
        placeholder = { Text(text = (stringResource(id = R.string.input_email))) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            errorContainerColor = Color.White,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
        ),
        keyboardOptions=KeyboardOptions.Default,
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            Log.d("emailCheckValue", emailCheckValue.toString())
            val iconImage=if(emailCheckValue){
                ImageVector.vectorResource(R.drawable.email_duplicate_check_yes)

            }else{
                ImageVector.vectorResource(R.drawable.email_duplicate_check_no)
            }

            val description=if(emailCheckValue){
                "사용 가능한 이메일 입니다."
            }else{
                "이미 사용중인 이메일 입니다."
            }

            IconButton(
                onClick = emailCheckProcess,
                enabled = duplicateCheckButtonState) {
                Image(imageVector = iconImage, contentDescription = description)
            }

        },
        isError=isError,
        shape = RoundedCornerShape(15.dp),
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
            modifier = Modifier.padding(start = 22.dp, top = 5.dp)
        )
    }
}





@Composable
fun IdInputFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    idDuplicateCheckValue:Boolean=false,
    idCheckProcess:()->Unit,
    isError: Boolean = false,
    errorMessage: String = "",
    showTrailingIcon: Boolean = true,
    duplicateCheckButtonState:Boolean=false,
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
            .padding(start = 25.dp, bottom = 5.dp, top = 10.dp)
    )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(componentShape.small)
                .padding(start = 20.dp, end = 20.dp),

            placeholder = { Text(text = (stringResource(id = R.string.input_Id))) },
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
            trailingIcon = if (showTrailingIcon) {
                {
                    val iconImage = if (idDuplicateCheckValue) {
                        ImageVector.vectorResource(R.drawable.id_duplicate_check_yes)
                    } else {
                        ImageVector.vectorResource(R.drawable.id_duplicate_check_no)
                    }

                    val description = if (idDuplicateCheckValue) {
                        "사용 가능한 이메일 입니다."
                    } else {
                        "이미 사용중인 이메일 입니다."
                    }

                    IconButton(
                        onClick = idCheckProcess,
                        enabled = duplicateCheckButtonState) {
                        Image(imageVector = iconImage, contentDescription = description)
                    }
                }
            } else null,
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
                modifier = Modifier.padding(start = 22.dp, top = 5.dp)
            )
        }

}



@Composable
fun PasswordInputFieldComponent(

    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    placeholderValue:String,
    passwordVisibleOption:Boolean,
    togglePassWordVisibility:()->Unit,
    isError: Boolean = false,
    errorMessage: String = "",
    ){

    Text(
        text = labelValue,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        color = Color.DarkGray,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, bottom = 5.dp, top = 10.dp)
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
        isError=isError,
        shape = RoundedCornerShape(15.dp),
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
            modifier = Modifier.padding(start = 22.dp, top = 5.dp)
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeDropDownMenuComponent(

    onValueChange: (String) -> Unit,
    labelValue: String,
    dropDownMenuOption: Boolean,
    toggleDropDownMenuOption: () -> Unit,
    placeholderValue: String=stringResource(id = R.string.input_entranceYear),

) {

    Text(
        text = stringResource(id = R.string.entranceYear),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        color = Color.DarkGray,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, bottom = 5.dp, top = 10.dp)
    )
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
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
//                    .padding(vertical = 8.dp)
                    .padding(start = 20.dp, end = 20.dp),

                shape = RoundedCornerShape(15.dp),
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
    onClick: () -> Unit,
    modifier: Modifier,
    showImage:Boolean=false,

    ) {

        Button(
            onClick = { onClick() },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .padding(start = 10.dp, end = 10.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(50.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp)
                    .background(
                        color = Pastel_Red,
                        shape = RoundedCornerShape(50.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = value,
                    fontSize = 19.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                if(showImage){
                    Icon(
                        modifier = Modifier
                            .padding(start = 140.dp)
                            .size(20.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.arrow),
                        contentDescription = "가입완료 아이콘",
                        tint = Color.Unspecified,
                    )
                }
            }
        }
    }


@Composable
fun TextInputHelpFieldComponent(
    errorMessage: String,
    isError:Boolean
){
    if(isError){
        Text(
            text = errorMessage,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            color = Pastel_Red,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(20.dp)
        )
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    val navController = rememberNavController()
    NextClassTheme {
        JoinView(loginViewModel,navController)
    }
}


