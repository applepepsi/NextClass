package com.example.nextclass.appComponent


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AttachEmail
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MarkEmailRead

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.items.GradeDropDownItems
import com.example.nextclass.items.TopNavItem
import com.example.nextclass.nav.TopNavGraph
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.screen.JoinView
import com.example.nextclass.ui.theme.Background_Color
import com.example.nextclass.ui.theme.Feldgrau
import com.example.nextclass.ui.theme.Navi_Green
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
            .heightIn()
            .padding(start = 20.dp, top = 15.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        ),
        color= Color.Black,
        textAlign = TextAlign.Left
    )
}

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
    placeholderValue:String,
    labelValue: String,
    isError:Boolean,
    errorMessage:String,
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

            IconButton(onClick = emailCheckProcess) {
                Icon(imageVector = iconImage, contentDescription = description)
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

            placeholder = { Text(text = (stringResource(id = R.string.input_id))) },
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
            trailingIcon = {
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

                IconButton(onClick = idCheckProcess) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }
            },
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
fun PasswordInputFieldComponent(

    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    placeholderValue:String,
    passwordVisibleOption:Boolean,
    togglePassWordVisibility:()->Unit,
    isError:Boolean,
    errorMessage:String,
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
                placeholder = { Text(text = (stringResource(id = R.string.input_entranceYear))) },
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
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopNav(loginViewModel: LoginViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        color = Background_Color,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            // 중앙에 네비게이션 바 배치
            TopBarComponent(navController = navController)

            // 아래에 네비게이션 그래프 배치
            TopNavGraph(loginViewModel = loginViewModel, navController = navController)
        }
    }
}

@Composable
fun TopBarComponent(navController: NavHostController) {
    val screens = listOf(
        TopNavItem.Login,
        TopNavItem.Join
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Surface(
        modifier = Modifier
            .padding(top = 30.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(50.dp),
        color = Color.White,
        shape = RoundedCornerShape(40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: TopNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.screenRoute } == true
    val background = if (selected) Navi_Green else Color.White
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .weight(1f)
            .padding(6.dp)
            .height(60.dp)
    ) {
        Box(
            modifier = Modifier
                .background(background)
                .clickable(
                    onClick = {
                        navController.navigate(screen.screenRoute) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    interactionSource = interactionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = screen.title,
                color = if (selected) Color.White else Color.Gray.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun ClickableTextComponent() {

    val all="모든 "
    val terms="이용 약관"
    val agree= "에 동의합니다."



    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 18.sp)) {
            append(all)
            withStyle(style = SpanStyle(color = Color.Red)) { // 특정 부분 스타일 설정
                pushStringAnnotation(tag = terms, annotation = terms)
                append(terms)
            }
            append(agree)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also{span->
                Log.d("test", span.toString())
            }
    })

}

@Composable
fun CheckboxComponent(
    checked:Boolean,
    onClickCheckBox: () -> Unit
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

        ClickableTextComponent()
    }

}


@Preview(showBackground = true)
@Composable
fun NavPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    NextClassTheme {
        TopNav(loginViewModel)

    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxPreview() {
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)

    NextClassTheme {
        CheckboxComponent(                checked = loginViewModel.termsCheckBoxState.value,
            onClickCheckBox = {loginViewModel.toggleTermsCheckBoxValue()})

    }
}
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    val testRepository = TestRepository()
//    val loginViewModel = LoginViewModel(testRepository)
//
//    NextClassTheme {
//        JoinView(loginViewModel)
//    }
//}


