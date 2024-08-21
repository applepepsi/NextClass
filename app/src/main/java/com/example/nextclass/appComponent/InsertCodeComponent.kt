package com.example.nextclass.appComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nextclass.R
import com.example.nextclass.items.TopNavItem
import com.example.nextclass.repository.testRepo.TestRepository
import com.example.nextclass.ui.theme.Background_Color2
import com.example.nextclass.viewmodel.LoginViewModel

//비밀번호 찾기의 코드 입력 뷰
@Composable
fun InsertCodeView(
    loginViewModel: LoginViewModel,
    navController: NavController,

)  {


    LaunchedEffect(Unit) {
        loginViewModel.startCountDown()
    }

    if(loginViewModel.verifyCodeCheckResult.value){
        loginViewModel.joinComplete()
        loginViewModel.toggleVerifyCodeCheckResult()
    }

    if(loginViewModel.joinResult.value){
        navController.navigate(TopNavItem.Login.screenRoute) {
//            popUpTo(TopNavItem.Join.screenRoute) { inclusive = true }
        }
        loginViewModel.toggleJoinResult()
    }

    Column {
        if(loginViewModel.loading.value){
            ProgressBarFullComponent(state = loginViewModel.loading.value)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            AppBarTextAndButtonComponent(
                value = stringResource(id = R.string.InputVerifyCode),
                navController=navController)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .height(380.dp)
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Background_Color2)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),

                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {



                        MainTextComponent(
                            value= stringResource(id = R.string.InputVerityCode),
                            modifier= Modifier
                                .padding(top=20.dp)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        DescriptionTextComponent(
                            value= stringResource(id = R.string.InputVerityCodeDetailMessage),
                            modifier= Modifier.padding(start = 5.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        VerifyCodeInputComponent(
                            onValueChange = {
                                loginViewModel.updateVerifyCode(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(2.dp))


                        RePostPasswordCodeComponent {
                            loginViewModel.getVerifyCode()
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ){
                            CountDownComponent(
                                countDown = loginViewModel.countDown.value,
                                countDownState = loginViewModel.countDownState.value
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            ChanceCountComponent(
                                remainingChance = loginViewModel.remainingChance.value
                            )
                        }

                        Spacer(modifier = Modifier.height(5.dp))



                        TextInputHelpFieldComponent(
                            errorMessage = loginViewModel.verifyCodeInputErrorMessage.value.asString(
                                LocalContext.current),
                            isError = loginViewModel.verifyCodeInputError.value,
                        )

                        TextInputHelpFieldComponent(
                            errorMessage = loginViewModel.joinFailMessage.value.asString(LocalContext.current),
                            isError = loginViewModel.joinFail.value,
                        )

                        InputButtonComponent(
                            value = "확인",
                            onClick = { loginViewModel.submitVerifyCode() },
                            modifier = Modifier.padding(start=15.dp,end=15.dp),
                            showImage = true
                        )


                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
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
fun InsertCodeViewPreview() {
    val navController= rememberNavController()
    val testRepository = TestRepository()
    val loginViewModel = LoginViewModel(testRepository)
    InsertCodeView(
        loginViewModel,
        navController
    )

}
