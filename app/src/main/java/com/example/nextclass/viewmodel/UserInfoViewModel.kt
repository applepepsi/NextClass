package com.example.nextclass.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextclass.Data.UserInfoData.ChangeEmail
import com.example.nextclass.Data.UserInfoData.ChangePassword
import com.example.nextclass.Data.UserInfoData.ChangeUserData
import com.example.nextclass.Data.UserInfoData.PostUserData
import com.example.nextclass.Data.UserData
import com.example.nextclass.Data.VerifyCodeData
import com.example.nextclass.R
import com.example.nextclass.repository.UserInfoRepository
import com.example.nextclass.utils.CURRENT_PASSWORD_NOT_MATCH
import com.example.nextclass.utils.CutEntranceYear
import com.example.nextclass.utils.DUPLICATE_PARAMETER
import com.example.nextclass.utils.INVALID_VERIFICATION_CODE
import com.example.nextclass.utils.NO_EMAIL_FOR_VERIFICATION
import com.example.nextclass.utils.SUCCESS_CODE
import com.example.nextclass.utils.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository
): ViewModel(){


    private val _userProfile= mutableStateOf(UserData())
    val userProfile: State<UserData> = _userProfile

    private val _changeEmail= mutableStateOf(ChangeEmail())
    val changeEmail: State<ChangeEmail> = _changeEmail

    private val _emailChangeState= mutableStateOf(false)
    val emailChangeState: State<Boolean> = _emailChangeState

    private val _passwordChangeErrorState= mutableStateOf(false)
    val passwordChangeErrorState: State<Boolean> = _passwordChangeErrorState

    private val _passwordChangeErrorMessage= mutableStateOf<StringValue>(StringValue.Empty)
    val passwordChangeErrorMessage: State<StringValue> = _passwordChangeErrorMessage

    private val _userInfoChangeErrorState= mutableStateOf(false)
    val userInfoChangeErrorState: State<Boolean> = _userInfoChangeErrorState

    private val _userInfoChangeErrorMessage= mutableStateOf<StringValue>(StringValue.Empty)
    val userInfoChangeErrorMessage: State<StringValue> = _userInfoChangeErrorMessage

    private val _userInfoChangeState= mutableStateOf(false)
    val userInfoChangeState: State<Boolean> = _userInfoChangeState



    private val _changePasswordData=mutableStateOf(ChangePassword())
    val changePasswordData: State<ChangePassword> = _changePasswordData

    private val _newPasswordConfirm=mutableStateOf("")
    val newPasswordConfirm: State<String> = _newPasswordConfirm

    private val _oldPasswordVisibility=mutableStateOf(false)
    val oldPasswordVisibility: State<Boolean> = _oldPasswordVisibility

    private val _newPasswordVisibility=mutableStateOf(false)
    val newPasswordVisibility: State<Boolean> = _newPasswordVisibility

    private val _changeUserData=mutableStateOf(ChangeUserData())
    val changeUserData: State<ChangeUserData> = _changeUserData

    private val _gradeDropDownMenuState=mutableStateOf(false)
    val gradeDropDownMenuState: State<Boolean> = _gradeDropDownMenuState

    private val _loading=mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _passwordChangeResult=mutableStateOf(false)
    val passwordChangeResult: State<Boolean> = _passwordChangeResult

    private val _emailInputError= mutableStateOf(false)
    val emailInputError: State<Boolean> = _emailInputError

    private val _emailInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val emailInputErrorMessage: State<StringValue> = _emailInputErrorMessage

    private val _emailDuplicateCheck= mutableStateOf(false)
    val emailDuplicateCheck: State<Boolean> = _emailDuplicateCheck

    private val _emailDuplicateButtonState=mutableStateOf(false)
    val emailDuplicateButtonState: State<Boolean> = _emailDuplicateButtonState

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    private val _verifyCodeIssueState = mutableStateOf(false)
    val verifyCodeIssueState: State<Boolean> = _verifyCodeIssueState

    private val _verifyCode = mutableStateOf("")
    val verifyCode: State<String> = _verifyCode

    private val _verifyCodeInputError=mutableStateOf(false)
    val verifyCodeInputError: State<Boolean> = _verifyCodeInputError

    private val _verifyCodeInputErrorMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val verifyCodeInputErrorMessage: State<StringValue> = _verifyCodeInputErrorMessage

    private val _countDown=mutableStateOf(TimeUnit.MINUTES.toSeconds(1))
    val countDown: State<Long> = _countDown

    private val _countDownState=mutableStateOf(false)
    val countDownState: State<Boolean> = _countDownState

    private val _remainingChance=mutableStateOf(5)
    val remainingChance: State<Int> = _remainingChance

    private val _verifyCodeCheckResult=mutableStateOf(false)
    val verifyCodeCheckResult: State<Boolean> = _verifyCodeCheckResult

    private val _changeEmailState=mutableStateOf(false)
    val changeEmailState: State<Boolean> = _changeEmailState

    private val _changeEmailErrorState=mutableStateOf(false)
    val changeEmailErrorState: State<Boolean> = _changeEmailErrorState

    private val _changeEmailErrorMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val changeEmailErrorMessage: State<StringValue> = _changeEmailErrorMessage

    private val _communityNotificationState=mutableStateOf(true)
    val communityNotificationState: State<Boolean> = _communityNotificationState

    private val _scheduleNotificationState=mutableStateOf(true)
    val scheduleNotificationState: State<Boolean> = _scheduleNotificationState

    fun updateVerifyCode(code: String) {
        _verifyCode.value = code
    }

    fun toggleCommunityNotificationState(){
        _communityNotificationState.value=!_communityNotificationState.value
    }

    fun toggleScheduleNotificationState(){
        _scheduleNotificationState.value=!_scheduleNotificationState.value
    }

    fun submitVerifyCode(){

        val verifyCodeCheckBody=VerifyCodeData(
            email=changeEmail.value.email,
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
        Log.d( "_verifyCodeInputError.value", _verifyCodeInputError.value.toString())
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

    fun updateOldPassword(password:String){
        _changePasswordData.value=_changePasswordData.value.copy(existing_password = password)

    }

    fun updateNewPassword(password:String){
        _changePasswordData.value=_changePasswordData.value.copy(new_password = password)
    }

    fun updateNewPasswordConfirm(password:String){
        _newPasswordConfirm.value=password
    }

    fun updateNewName(name:String){
        _changeUserData.value=_changeUserData.value.copy(name = name)
    }

    fun updateNewGrade(memberGrade:String){

        _changeUserData.value=_changeUserData.value.copy(member_grade =memberGrade)
    }

    fun updateNewSchool(memberSchool:String){
        _changeUserData.value=_changeUserData.value.copy(member_school = memberSchool)
    }

    fun updateNewEmail(newEmail:String){
        _changeEmail.value=_changeEmail.value.copy(email = newEmail)
        Log.d("업데이트 이메일", _changeEmail.value.toString())
        _emailDuplicateCheck.value=false
        emailCheck(newEmail)
    }

    fun updateOldPasswordOfChangeEmail(oldPassword:String){
        _changeEmail.value=_changeEmail.value.copy(password = oldPassword)
    }

    fun resetChangeEmailData(){
        _changeEmail.value= ChangeEmail()
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

    fun emailDuplicateCheck(){

        userInfoRepository.emailDuplicateCheck(changeEmail.value.email){serverResponse ->
            if(serverResponse!=null){
                if(serverResponse.code ==SUCCESS_CODE){
                    _emailDuplicateCheck.value=true
                    _emailInputErrorMessage.value = StringValue.Empty
                    _emailInputError.value = false
                }else if(serverResponse.errorCode== DUPLICATE_PARAMETER){
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
    }

    fun getChangeEmailVerifyCode(){


        if(!emailChangeState.value && emailDuplicateCheck.value){

            _loading.value=true

            userInfoRepository.postChangeEmailData(changeEmail.value){ServerResponse->

                if (ServerResponse?.code==200) {
                    Log.d("이메일 변경 코드 발급 성공", ServerResponse.toString())
                    resetChance()
                    resetCountDown()
                    _verifyCodeIssueState.value=true
                }else if(ServerResponse?.errorCode==CURRENT_PASSWORD_NOT_MATCH){

                    _changeEmailErrorMessage.value = StringValue.StringResource(R.string.passwordNotMatch)
                    _changeEmailErrorState.value = true

                    _verifyCodeIssueState.value=false
                }
                _loading.value=false
            }

            //서버로 전송
        }else{
            _changeEmailErrorMessage.value = StringValue.StringResource(R.string.joinFailError)
            _changeEmailErrorState.value = true
        }
    }

    fun changeEmailComplete(){
        _loading.value=true


        userInfoRepository.postChangeEmailRequest(changeEmail.value){ServerResponse->
            if (ServerResponse?.code==200) {
                Log.d("이메일 변경 성공", ServerResponse.toString())
                _changeEmailState.value=true
            }
            _loading.value=false
        }
        _loading.value=false
    }

    fun toggleVerifyCodeIssueState(){
        _verifyCodeIssueState.value=false
    }

    fun toggleVerifyCodeCheckResult(){
        _verifyCodeCheckResult.value=false
    }

    fun toggleChangeEmailState(){
        _changeEmailState.value=false
    }
    fun togglePasswordVisibility() {
        _passwordVisibility.value = !_passwordVisibility.value
    }

    fun toggleGradeDropBox(){
        _gradeDropDownMenuState.value=!_gradeDropDownMenuState.value
    }

    fun toggleOldPasswordVisibility(){
        _oldPasswordVisibility.value=!_oldPasswordVisibility.value
    }

    fun toggleNewPasswordVisibility(){
        _newPasswordVisibility.value=!_newPasswordVisibility.value
    }

    private fun passwordCheck(): Boolean {
        return if (_changePasswordData.value.new_password != _newPasswordConfirm.value) {
            _passwordChangeErrorMessage.value = StringValue.StringResource(R.string.wrongPasswordConfirm)
            _passwordChangeErrorState.value = true
            false
        } else if (_changePasswordData.value.new_password.isEmpty()) {
            _passwordChangeErrorMessage.value = StringValue.StringResource(R.string.joinFailError)
            _passwordChangeErrorState.value = true
            false
        } else if (_changePasswordData.value.new_password.length !in 9..18) {
            _passwordChangeErrorMessage.value = StringValue.StringResource(R.string.wrongPasswordLimit)
            _passwordChangeErrorState.value = true
            false
        } else if (!_changePasswordData.value.new_password.matches(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>]).+$"))) {
            _passwordChangeErrorMessage.value = StringValue.StringResource(R.string.wrongPasswordRule)
            _passwordChangeErrorState.value = true
            false
        } else {
            _passwordChangeErrorMessage.value = StringValue.Empty
            _passwordChangeErrorState.value = false
            true
        }
    }

    private fun userInfoCheck(): Boolean {
        return if(_changeUserData.value.member_grade.isEmpty() || _changeUserData.value.name.isEmpty() || _changeUserData.value.member_school.isEmpty()){
            _userInfoChangeErrorMessage.value=StringValue.StringResource(R.string.joinFailError)
            _userInfoChangeErrorState.value=true
            false
        }else if(_changeUserData.value.name.length>=12){
            _userInfoChangeErrorMessage.value=StringValue.StringResource(R.string.nameLimit)
            _userInfoChangeErrorState.value=true
            false
        }else if(!_changeUserData.value.name.matches(Regex("^[가-힣]+$"))){
            _userInfoChangeErrorMessage.value=StringValue.StringResource(R.string.nameOnlyKorean)
            _userInfoChangeErrorState.value=true
            false
        }else if(_changeUserData.value.member_school.length>21){
            _userInfoChangeErrorMessage.value=StringValue.StringResource(R.string.schoolNameLimit)
            _userInfoChangeErrorState.value=true
            false
        }
        else{
            _userInfoChangeErrorMessage.value=StringValue.Empty
            _userInfoChangeErrorState.value=false
            return true
        }
    }

    fun postPasswordChangeData(){

        if(passwordCheck() && !_passwordChangeErrorState.value){
            _loading.value=true
            userInfoRepository.postChangePasswordData(_changePasswordData.value){ServerResponse->
                Log.d("serverResponse", ServerResponse.toString())
                if (ServerResponse != null) {
                    if(ServerResponse.code== SUCCESS_CODE){
                        _passwordChangeResult.value=true
                        _loading.value=false
                    }
                }
            }
            _loading.value=false
            //서버로 전송
        }
    }

    fun postUserInfoChangeData(){
        Log.d("userInfoCheck()",userInfoCheck().toString())
        if(userInfoCheck() && !_userInfoChangeErrorState.value){
            Log.d("문제없음",_changeUserData.value.toString())
            //서버로 전송
            _loading.value=true

            val postUserData= PostUserData(
                name=_changeUserData.value.name,
                member_grade = CutEntranceYear.deleteGradeEntranceYear(_changeUserData.value.member_grade),
                member_school = _changeUserData.value.member_school
            )

            userInfoRepository.postChangeUserInfoData(postUserData){ServerResponse->
                Log.d("serverResponse", ServerResponse.toString())
                if (ServerResponse != null) {
                    if(ServerResponse.code== SUCCESS_CODE){
                        _loading.value=false
                    }
                }
            }
            _loading.value=false
        }
    }

    //사용자의 정보를 가져와서 화면에 출력해야함
    fun getUserInfo(){
        _loading.value=true

        userInfoRepository.getUserInfo(){serverResponse ->
            Log.d("serverResponse", serverResponse.toString())
            if (serverResponse != null) {
                _userProfile.value= serverResponse.data!!
                setInitUserInfo(_userProfile.value)
            }

            _loading.value=false
        }
    }

    private fun setInitUserInfo(value: UserData) {
        _changeUserData.value = _changeUserData.value.copy(
            name = value.name,
            member_grade = CutEntranceYear.addGradeEntranceYear(value.member_grade),
            member_school = value.member_school
        )
    }


}