package com.example.nextclass.viewmodel

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextclass.Data.FindIDOrPasswordData
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.LoginRequest
import com.example.nextclass.Data.ModifyUserData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData
import com.example.nextclass.Data.VerifyCodeData
import com.example.nextclass.R
import com.example.nextclass.repository.UserInfoRepository
import com.example.nextclass.utils.CutEntranceYear
import com.example.nextclass.utils.DUPLICATE_PARAMETER
import com.example.nextclass.utils.INVALID_VERIFICATION_CODE
import com.example.nextclass.utils.NO_EMAIL_FOR_VERIFICATION
import com.example.nextclass.utils.SUCCESS_CODE
import com.example.nextclass.utils.StringValue
import com.example.nextclass.utils.USER_NOT_EXIST
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository
):ViewModel(){


    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _joinId = mutableStateOf("")
    val joinId: State<String> = _joinId

    private val _id = mutableStateOf("")
    val id: State<String> = _id

    private val _joinPassword = mutableStateOf("")
    val joinPassword: State<String> = _joinPassword

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _passwordConfirm = mutableStateOf("")
    val passwordConfirm: State<String> = _passwordConfirm

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _schoolName = mutableStateOf("")
    val schoolName: State<String> = _schoolName

    private val _entranceYear = mutableStateOf("")
    val entranceYear: State<String> = _entranceYear

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    private val _menuVisibility=mutableStateOf(false)
    val menuVisibility: State<Boolean> = _menuVisibility

    private val _termsCheckBoxState=mutableStateOf(false)
    val termsCheckBoxState: State<Boolean> = _termsCheckBoxState

    //회원가입에서 입력값 유효성 검사에 사용됨
    private val _emailDuplicateCheck= mutableStateOf(false)
    val emailDuplicateCheck: State<Boolean> = _emailDuplicateCheck

    private val _joinIdDuplicateCheck= mutableStateOf(false)
    val joinIdDuplicateCheck: State<Boolean> = _joinIdDuplicateCheck

    private val _joinIdInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val joinIdInputErrorMessage: State<StringValue> = _joinIdInputErrorMessage

    private val _joinIdInputError= mutableStateOf(false)
    val joinIdInputError: State<Boolean> = _joinIdInputError

    private val _passwordInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val passwordInputErrorMessage: State<StringValue> = _passwordInputErrorMessage

    private val _passwordInputError= mutableStateOf(false)
    val passwordInputError: State<Boolean> = _passwordInputError

    private val _passwordConfirmInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val passwordConfirmInputErrorMessage: State<StringValue> = _passwordConfirmInputErrorMessage

    private val _passwordConfirmInputError= mutableStateOf(false)
    val passwordConfirmInputError: State<Boolean> = _passwordConfirmInputError

    private val _emailInputError= mutableStateOf(false)
    val emailInputError: State<Boolean> = _emailInputError

    private val _emailInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val emailInputErrorMessage: State<StringValue> = _emailInputErrorMessage

    private val _nameInputError= mutableStateOf(false)
    val nameInputError: State<Boolean> = _nameInputError

    private val _nameInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val nameInputErrorMessage: State<StringValue> = _nameInputErrorMessage

    private val _schoolNameInputError= mutableStateOf(false)
    val schoolNameInputError: State<Boolean> = _schoolNameInputError

    private val _schoolNameInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val schoolNameInputErrorMessage: State<StringValue> = _schoolNameInputErrorMessage

    private val _entranceYearInputError= mutableStateOf(false)
    val entranceYearInputError: State<Boolean> = _entranceYearInputError

    private val _entranceYearInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val entranceYearInputErrorMessage: State<StringValue> = _entranceYearInputErrorMessage

    private val _joinFailMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val joinFailMessage: State<StringValue> = _joinFailMessage

    private val _joinFail=mutableStateOf(false)
    val joinFail: State<Boolean> = _joinFail

    private val _loginFailMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val loginFailMessage: State<StringValue> = _loginFailMessage

    private val _loginFail=mutableStateOf(false)
    val loginFail: State<Boolean> = _loginFail

    private val _findFailId=mutableStateOf(false)
    val findFailId: State<Boolean> = _findFailId

    private val _findFailIdMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val findFailIdMessage: State<StringValue> = _findFailIdMessage

    private val _findFailPassword=mutableStateOf(false)
    val findFailPassword: State<Boolean> = _findFailPassword

    private val _findFailPasswordMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val findFailPasswordMessage: State<StringValue> = _findFailPasswordMessage



    private val _newPassword=mutableStateOf("")
    val newPassword: State<String> = _newPassword

    private val _verifyCode = mutableStateOf("")
    val verifyCode: State<String> = _verifyCode



    private val _verifyCodeInputError=mutableStateOf(false)
    val verifyCodeInputError: State<Boolean> = _verifyCodeInputError

    private val _verifyCodeInputErrorMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val verifyCodeInputErrorMessage: State<StringValue> = _verifyCodeInputErrorMessage

    private val _userInfoModifyPasswordConfirm=mutableStateOf("")
    val userInfoModifyPasswordConfirm: State<String> = _userInfoModifyPasswordConfirm

    private val _userInfoModifyPasswordConfirmError=mutableStateOf(false)
    val userInfoModifyPasswordConfirmError: State<Boolean> = _userInfoModifyPasswordConfirmError

    private val _userInfoModifyPasswordConfirmErrorMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val userInfoModifyPasswordConfirmErrorMessage: State<StringValue> = _userInfoModifyPasswordConfirmErrorMessage

    private val _emailDuplicateButtonState=mutableStateOf(false)
    val emailDuplicateButtonState: State<Boolean> = _emailDuplicateButtonState

    private val _idDuplicateButtonState=mutableStateOf(false)
    val idDuplicateButtonState: State<Boolean> = _idDuplicateButtonState

    private val _joinResult=mutableStateOf(false)
    val joinResult: State<Boolean> = _joinResult

    private val _autoLoginState=mutableStateOf(false)
    val autoLoginState: State<Boolean> = _autoLoginState

    private val _loginResult=mutableStateOf(false)
    val loginResult: State<Boolean> = _loginResult

    private val _loading=mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _tokenData=mutableStateOf(TokenData())
    val tokenData: State<TokenData> = _tokenData

    private val _joinDataCheckResult=mutableStateOf(false)
    val joinDataCheckResult: State<Boolean> = _joinDataCheckResult

    private val _modifyUserInfo= mutableStateOf(ModifyUserData())
    val modifyUserInfo: State<ModifyUserData> = _modifyUserInfo

    private val _countDown=mutableStateOf(TimeUnit.MINUTES.toSeconds(1))
    val countDown: State<Long> = _countDown

    private val _countDownState=mutableStateOf(true)
    val countDownState: State<Boolean> = _countDownState

    private val _remainingChance=mutableStateOf(5)
    val remainingChance: State<Int> = _remainingChance

    private val _verifyCodeCheckResult=mutableStateOf(false)
    val verifyCodeCheckResult: State<Boolean> = _verifyCodeCheckResult

    private val _findIDOrPassword=mutableStateOf(FindIDOrPasswordData())
    val findIDOrPassword: State<FindIDOrPasswordData> = _findIDOrPassword

    private val _findIdSuccessMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val findIdSuccessMessage: State<StringValue> = _findIdSuccessMessage

    private val _findPasswordSuccessMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val findPasswordSuccessMessage: State<StringValue> = _findPasswordSuccessMessage

    private val _findIdSuccessState=mutableStateOf(false)
    val findIdSuccessState: State<Boolean> = _findIdSuccessState

    private val _findPasswordSuccessState=mutableStateOf(false)
    val findPasswordSuccessState: State<Boolean> = _findPasswordSuccessState

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        _emailDuplicateCheck.value=false
        emailCheck(newEmail)
    }

    private fun emailCheck(newEmail: String){

        val pattern: Pattern = Patterns.EMAIL_ADDRESS

        val errorMessage: StringValue = when {
            !pattern.matcher(newEmail).matches() -> {
                _emailDuplicateButtonState.value = false
                StringValue.StringResource(R.string.wrongEmailType)
            }
            !_emailDuplicateCheck.value -> {
                _emailDuplicateButtonState.value = true
                StringValue.StringResource(R.string.needEmailDuplicateCheck)
            }
            else -> {
                _emailDuplicateButtonState.value = true
                StringValue.Empty
            }
        }
        _emailInputErrorMessage.value = errorMessage
        _emailInputError.value = errorMessage != StringValue.Empty
    }

    fun updateJoinId(newjoinId: String) {
        _joinId.value = newjoinId
        _joinIdDuplicateCheck.value=false
        joinIdCheck(newjoinId)
        Log.d("joinIdTest",_joinId.value)
        //아이디 인증을 하고 텍스트를 바꾸면 인증을 다시하도록

    }

    private fun joinIdCheck(newjoinId: String){
        val errorMessage = when {
            newjoinId.length !in 5..20 -> {
                _idDuplicateButtonState.value=false
                StringValue.StringResource(R.string.joinIdSizeLimit)
            }
            !newjoinId.matches(Regex("^[a-zA-Z0-9]*$")) -> {
                _idDuplicateButtonState.value=false
                StringValue.StringResource(R.string.joinIdOnlyString)
            }
            !_joinIdDuplicateCheck.value -> {
                _idDuplicateButtonState.value=true
                StringValue.StringResource(R.string.needJoinIdDuplicateCheck)
            }
            else -> {
                _idDuplicateButtonState.value=true
                StringValue.Empty
            }
        }
        _joinIdInputErrorMessage.value = errorMessage
        _joinIdInputError.value = errorMessage != StringValue.Empty
    }

    fun updateId(newId: String) {
        _id.value = newId

    }

    fun updatePassword(newPassword: String) {

        _password.value = newPassword

    }
    fun updateJoinPassword(newJoinPassword: String) {

        _joinPassword.value = newJoinPassword
        joinPasswordCheck(newJoinPassword)
    }
    private fun joinPasswordCheck(newjoinPassword: String) {
        val errorMessage = when {
            newjoinPassword.length !in 9..18 -> StringValue.StringResource(R.string.wrongPasswordLimit)
            !newjoinPassword.matches(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>]).+$")) -> StringValue.StringResource(R.string.wrongPasswordRule)

            else -> StringValue.Empty
        }

        _passwordInputErrorMessage.value = errorMessage
        _passwordInputError.value = errorMessage != StringValue.Empty
    }

    fun updatePasswordConfirm(newPasswordConfirm: String) {
        _passwordConfirm.value = newPasswordConfirm

        passwordConfirmCheck(newPasswordConfirm)
    }

    private fun passwordConfirmCheck(newPasswordConfirm: String) {

        val errorMessage = when {
            newPasswordConfirm !=_joinPassword.value -> StringValue.StringResource(R.string.wrongPasswordConfirm)
            else -> StringValue.Empty
        }

        _passwordConfirmInputErrorMessage.value = errorMessage
        _passwordConfirmInputError.value = errorMessage != StringValue.Empty
    }

    fun updateName(newName: String) {
        _name.value = newName
        nameCheck(newName)
    }

    private fun nameCheck(newName: String) {

        val errorMessage = when {
            newName.length >= 12 -> StringValue.StringResource(R.string.nameLimit)
            !newName.matches(Regex("^[가-힣]+$")) -> StringValue.StringResource(R.string.nameOnlyKorean)
            else -> StringValue.Empty
        }

        _nameInputErrorMessage.value = errorMessage
        _nameInputError.value = errorMessage != StringValue.Empty
    }

    fun updateSchoolName(newSchoolName: String) {
        _schoolName.value = newSchoolName
        schoolNameCheck(newSchoolName)
    }

    private fun schoolNameCheck(newSchoolName: String) {

        val errorMessage = when {
            newSchoolName.length > 21 -> StringValue.StringResource(R.string.schoolNameLimit)
            else -> StringValue.Empty
        }

        _schoolNameInputErrorMessage.value = errorMessage
        _schoolNameInputError.value = errorMessage != StringValue.Empty
    }

    fun updateEntranceYear(newEntranceYear: String) {
        _entranceYear.value = newEntranceYear
    }

    fun togglePasswordVisibility() {
        _passwordVisibility.value = !_passwordVisibility.value
    }

    fun toggleTermsCheckBoxValue() {
        _termsCheckBoxState.value = !_termsCheckBoxState.value
    }

    fun toggleAutoLoginState() {

        _autoLoginState.value = !_autoLoginState.value
        Log.d("오토로그인 값",_autoLoginState.value.toString())
    }

    fun toggleJoinDataCheckResult(){
        _joinDataCheckResult.value=!_joinDataCheckResult.value
        Log.d("_joinDataCheckResult",_joinDataCheckResult.value.toString())
    }

    fun emailDuplicateCheck(){

        userInfoRepository.emailDuplicateCheck(email.value){serverResponse ->
            if(serverResponse!=null){
                if(serverResponse.code ==SUCCESS_CODE){
                    _emailDuplicateCheck.value=true
                    _emailInputErrorMessage.value = StringValue.Empty
                    _emailInputError.value = false
                }else if(serverResponse.errorCode==DUPLICATE_PARAMETER){
                    _emailInputErrorMessage.value = StringValue.StringResource(R.string.emailDuplicate)
                    _emailInputError.value = true
                }
                else{
                    _emailInputErrorMessage.value = StringValue.StringResource(R.string.emailDuplicate)
                    _emailInputError.value = true
                }
            }else{
                _emailInputErrorMessage.value = StringValue.StringResource(R.string.duplicateFail)
                _emailInputError.value = true
            }
        }

        //이메일 체크 버튼을 누르면 현재 작성된 이메일을 서버로 전송해서 체크해야함
//        _emailDuplicateCheck.value=!_emailDuplicateCheck.value
    }

    fun joinIdDuplicateCheck(){

        userInfoRepository.joinIdDuplicateCheck(joinId.value){serverResponse ->
            if(serverResponse!=null){
                if(serverResponse.code ==SUCCESS_CODE){
                    _joinIdDuplicateCheck.value=true
                    _joinIdInputErrorMessage.value = StringValue.Empty
                    _joinIdInputError.value = false
                }else if(serverResponse.errorCode==DUPLICATE_PARAMETER){
                    _joinIdInputErrorMessage.value = StringValue.StringResource(R.string.joinIdDuplicate)
                    _joinIdInputError.value = true
                }
                else{
                    _joinIdInputErrorMessage.value = StringValue.StringResource(R.string.joinIdDuplicate)
                    _joinIdInputError.value = true
                }
            }else{
                _joinIdInputErrorMessage.value = StringValue.StringResource(R.string.duplicateFail)
                _joinIdInputError.value = true
            }
        }
        //아이디 체크 버튼을 누르면 현재 작성된 이메일을 서버로 전송해서 체크해야함

    }

    fun toggleMenuVisibility() {

        _menuVisibility.value = !_menuVisibility.value
    }

    fun joinDataCheck(){
        if(joinEmptyAndErrorCheck() && duplicateCheck() && termsCheck()){
            _joinDataCheckResult.value=true
        }else{
            Log.d("가입 실패", "가입실패")
            _joinFailMessage.value=StringValue.StringResource(R.string.joinFailError)
            _joinFail.value=true
        }
    }


    fun joinComplete(){


        if(joinEmptyAndErrorCheck() && duplicateCheck() && termsCheck())
        {

            val joinRequest = JoinRequest(
                id=joinId.value,
                name=name.value,
                password=joinPassword.value,
                email=email.value,
                member_grade = CutEntranceYear.deleteGradeEntranceYear(entranceYear.value),
                member_school=schoolName.value
            )


            userInfoRepository.postUserJoinInfo(joinRequest){ joinRequestResult->
                Log.d("serverProductData", joinRequestResult.toString())
                if(joinRequestResult !=null){
                    if(joinRequestResult.code==SUCCESS_CODE){
                        _joinResult.value=true
                    }
                }else{

                    _joinFailMessage.value=StringValue.StringResource(R.string.duplicateFail)
                    _joinFail.value=true
                }
            }
            Log.d("가입 성공",
                "email : ${email.value}," +
                        "joinId : ${joinId.value}," +
                        "joinPassword : ${joinPassword.value}," +
                        "passwordConfirm : ${passwordConfirm.value}," +
                        "name : ${name.value},"+
                        "schoolName : ${schoolName.value},"+
                        "entranceYear : ${entranceYear.value},")


            _joinFailMessage.value=StringValue.Empty
            _joinFail.value=false
        }else{
            Log.d("가입 실패", "가입실패")
            _joinFailMessage.value=StringValue.StringResource(R.string.joinFailError)
            _joinFail.value=true
        }
    }


    fun tryLogin(){
        if(!loginInputCheck()){
            val loginRequest=LoginRequest(
                id=id.value,
                password=password.value
            )
            userInfoRepository.postUserLoginInfo(loginRequest){ loginRequestResult->
                if(loginRequestResult !=null){
                    if(loginRequestResult.code==SUCCESS_CODE){
                        Log.d("로그인성공", loginRequestResult.code.toString())
                        _loginResult.value=true
                        //로그인 성공후 토큰 데이터를 받는다
                        _tokenData.value= loginRequestResult.data!!
//                        saveUserInfo()
                        //todo 로그인 성공후 기능 구현해야함
                    }
//                    else if(loginRequestResult.errorCode=="E00202"){
//
//                    }
                    else{
                        _loginFailMessage.value=StringValue.StringResource(R.string.wrongIdOrPassword)
                        _loginFail.value=true
                    }
                }else{
                    _loginFailMessage.value=StringValue.StringResource(R.string.duplicateFail)
                    _loginFail.value=true
                }
            }
        }else{
            _loginFailMessage.value=StringValue.StringResource(R.string.emptyIdOrPassword)
            _loginFail.value=true
        }
    }

    //자동 로그인이 켜져있다면 사용자의 아이디와 비밀번호를 저장하여 어플을 시작할때 자동로그인이 되도록함
    //todo 로그아웃을 구현하고 로그아웃시 SharedPreferences에 저장된 아이디와 비밀번호 제거
    fun tryAutoLogin(autoLoginId:String?,autoLoginPassword:String?){
        if(autoLoginId!=null && autoLoginPassword !=null){
            Log.d("자동 로그인시도","자동")
            _loading.value=true
            val loginRequest=LoginRequest(
                id=autoLoginId,
                password=autoLoginPassword
            )
            userInfoRepository.postUserLoginInfo(loginRequest){ loginRequestResult->
                if(loginRequestResult !=null) {
                    if (loginRequestResult.code == SUCCESS_CODE) {
                        Log.d("자동 로그인성공", loginRequestResult.code.toString())
                        _loginResult.value=true
                        _loading.value=false
                        _tokenData.value= loginRequestResult.data!!
                    }else{
                        _loading.value=false
                    }
                }
            }
        }else{
            _loading.value=false
        }
    }


    private fun loginInputCheck():Boolean{
        return id.value.isEmpty() || password.value.isEmpty()
    }

    private fun joinEmptyAndErrorCheck(): Boolean {
        return listOf(
            _email to _emailInputError,
            _joinId to _joinIdInputError,
            _joinPassword to _passwordInputError,
            _passwordConfirm to _passwordConfirmInputError,
            _name to _nameInputError,
            _schoolName to _schoolNameInputError,
            _entranceYear to _entranceYearInputError
        ).all { (userInput, error) -> userInput.value.isNotEmpty() && !error.value }
    }

    private fun duplicateCheck(): Boolean {

        return _joinIdDuplicateCheck.value && _emailDuplicateCheck.value
    }

    private fun termsCheck(): Boolean{
        return _termsCheckBoxState.value
    }





    fun updateVerifyCode(value: String) {
        Log.d("value",value)
        _verifyCode.value = value
    }



    fun updateUserInfoModifyPasswordConfirm(value: String){
        _userInfoModifyPasswordConfirm.value=value
    }

    fun submitUserInfoModifyPasswordConfirm(){
        _userInfoModifyPasswordConfirmError.value=false
        _userInfoModifyPasswordConfirmErrorMessage.value=StringValue.StringResource(R.string.WrongVerityCodeMessage)

        //여기서 비밀번호가 맞다면 회원정보 변경창으로
    }

    fun logOut(){
        _loginResult.value=false
        _id.value=""
        _password.value=""
    }

    fun getUserInfo(){
        //서버에서 받아온 유저 정보를 삽입해야함
        _modifyUserInfo.value
    }

    fun getVerifyCode(){

        _loading.value=true
        userInfoRepository.getVerifyCode(VerifyCodeData(email.value)){ verifyCodeRequestResult->
            if(verifyCodeRequestResult !=null) {
                if (verifyCodeRequestResult.code == SUCCESS_CODE) {
                    Log.d("인증 코드 발급 성공", verifyCodeRequestResult.code.toString())
                    resetChance()
                    resetCountDown()
                }else{
                    _loading.value=false
                }
            }
            _loading.value=false
        }
    }

    fun submitVerifyCode(){

        val verifyCodeCheckBody=VerifyCodeData(
            email=email.value,
            code=verifyCode.value
        )

        if(countDown.value>0){
            if(_remainingChance.value>0) {
                _loading.value = true
                userInfoRepository.verifyCodeCheck(verifyCodeCheckBody) { verifyCodeCheckResult ->
                    if (verifyCodeCheckResult != null) {
                        if (verifyCodeCheckResult.code == SUCCESS_CODE) {
                            Log.d("인증 코드 체크 완료", verifyCodeCheckResult.code.toString())

                            _verifyCodeCheckResult.value = true
                            _verifyCodeInputError.value = false
                            _verifyCodeInputErrorMessage.value = StringValue.Empty

                        } else if (verifyCodeCheckResult.errorCode == INVALID_VERIFICATION_CODE) {
                            //인증 코드가 올바르지 않을때
                            _verifyCodeInputError.value = true
                            _verifyCodeInputErrorMessage.value =
                                StringValue.StringResource(R.string.WrongVerityCodeMessage)
                            reductionChance()
                        } else if (verifyCodeCheckResult.errorCode == NO_EMAIL_FOR_VERIFICATION) {
                            _verifyCodeInputError.value = true
                            _verifyCodeInputErrorMessage.value =
                                StringValue.StringResource(R.string.ExpirationCodeMessage)
                            reductionChance()
                        }
                        _loading.value = false
                    }
                }
            }else{
                _verifyCodeInputError.value=true
                _verifyCodeInputErrorMessage.value=StringValue.StringResource(R.string.noChance)
            }
        }else{
            _verifyCodeInputError.value=true
            _verifyCodeInputErrorMessage.value=StringValue.StringResource(R.string.ExpirationCodeMessage)
        }

        _loading.value=false
    }

    fun startCountDown(){

        if(_countDownState.value){
            viewModelScope.launch {
                while (_countDownState.value && _countDown.value>0){
                    delay(1000L)
                    _countDown.value-=1
                }
                _countDownState.value=false
            }
        }
    }

    fun stopCountDown(){
        _countDownState.value=false
    }

    fun resetCountDown(){
        _countDownState.value=true
        _countDown.value=TimeUnit.MINUTES.toSeconds(5)
    }

    fun resetChance(){
        _remainingChance.value=5
    }

    private fun reductionChance(){
        _remainingChance.value -= 1
    }

    fun toggleVerifyCodeCheckResult(){
        _verifyCodeCheckResult.value=false
    }
    fun toggleJoinResult(){
        _joinResult.value=false
    }




    fun findId(){
        _loading.value = true

        val findIdData = FindIDOrPasswordData(findIDOrPassword.value.email, null)

        userInfoRepository.postFindId(findIdData) { findIdResult ->
            if (findIdResult != null) {
                if (findIdResult.code == SUCCESS_CODE) {
                    Log.d("아이디 찾기 완료", findIdResult.code.toString())
                    _findFailId.value = false
                    _findFailIdMessage.value = StringValue.Empty

                    _findIdSuccessState.value = true
                    _findIdSuccessMessage.value = StringValue.StringResource(R.string.FindIDSuccessMessage)
                } else if (findIdResult.errorCode == USER_NOT_EXIST) {
                    // 아이디 또는 비밀번호가 올바르지 않을 때
                    _findFailId.value = true
                    _findFailIdMessage.value = StringValue.StringResource(R.string.FindFailMessage)

                    _findIdSuccessState.value = false
                    _findIdSuccessMessage.value = StringValue.Empty
                }
            } else {

                _findFailId.value = true
                _findFailIdMessage.value = StringValue.StringResource(R.string.FindFailMessage)

                _findIdSuccessState.value = false
                _findIdSuccessMessage.value = StringValue.Empty
            }

            _loading.value = false

            resetFindInputData()
        }
    }


    fun findPassword(){

        val findPasswordData=FindIDOrPasswordData(null,findIDOrPassword.value.id)

        userInfoRepository.postFindPassword(findPasswordData){ findPasswordResult->
            _loading.value=true

            if(findPasswordResult !=null) {
                if (findPasswordResult.code == SUCCESS_CODE) {
                    Log.d("아이디 찾기 완료", findPasswordResult.code.toString())
                    _findFailId.value=false
                    _findFailIdMessage.value=StringValue.Empty

                    _findPasswordSuccessState.value=true
                    _findPasswordSuccessMessage.value=StringValue.StringResource(R.string.FindPasswordSuccessMessage)

                }else if(findPasswordResult.errorCode==USER_NOT_EXIST){
                    //아이디 또는 비밀번호가 올바르지 않을때
                    _findFailId.value=true
                    _findFailIdMessage.value=StringValue.StringResource(R.string.FindFailMessage)

                    _findPasswordSuccessState.value=false
                    _findPasswordSuccessMessage.value=StringValue.Empty

                }
                _loading.value=false
            }
            resetFindInputData()
        }
        Log.d("로딩", _loading.value.toString())
    }

    fun updateFindId(email:String){
        _findIDOrPassword.value=_findIDOrPassword.value.copy(email=email)
    }

    fun updateFindPassword(id:String){
        _findIDOrPassword.value=_findIDOrPassword.value.copy(id=id)
    }

    fun resetFindInputData(){
        _findIDOrPassword.value= FindIDOrPasswordData()
    }

}

//todo 가입후 화면이동 + 로그인 처리