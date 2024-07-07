package com.example.nextclass.viewmodel

import android.graphics.Paint.Join
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.ChangePassword
import com.example.nextclass.Data.ChangeUserData
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.R
import com.example.nextclass.repository.UserInfoRepository
import com.example.nextclass.utils.CutEntranceYear
import com.example.nextclass.utils.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository
): ViewModel(){


    private val _userProfile= mutableStateOf(JoinRequest())
    val userProfile: State<JoinRequest> = _userProfile


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

    private val _verifyCodeInputError=mutableStateOf(false)
    val verifyCodeInputError: State<Boolean> = _verifyCodeInputError

    private val _verifyCodeInputErrorMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val verifyCodeInputErrorMessage: State<StringValue> = _verifyCodeInputErrorMessage

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

    fun updateVerifyCode(code: String) {

    }

    fun updateOldPassword(password:String){
        _changePasswordData.value=_changePasswordData.value.copy(existingPassword = password)

    }

    fun updateNewPassword(password:String){
        _changePasswordData.value=_changePasswordData.value.copy(newPassword = password)
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
        return if (_changePasswordData.value.newPassword != _newPasswordConfirm.value) {
            _passwordChangeErrorMessage.value = StringValue.StringResource(R.string.wrongPasswordConfirm)
            _passwordChangeErrorState.value = true
            false
        } else if (_changePasswordData.value.newPassword.isEmpty()) {
            _passwordChangeErrorMessage.value = StringValue.StringResource(R.string.joinFailError)
            _passwordChangeErrorState.value = true
            false
        } else if (_changePasswordData.value.newPassword.length !in 9..18) {
            _passwordChangeErrorMessage.value = StringValue.StringResource(R.string.wrongPasswordLimit)
            _passwordChangeErrorState.value = true
            false
        } else if (!_changePasswordData.value.newPassword.matches(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>]).+$"))) {
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
            //서버로 전송
        }
    }

    fun postUserInfoChangeData(){
        if(userInfoCheck() && !_userInfoChangeErrorState.value){
            //서버로 전송
        }
    }

    fun submitVerifyCode() {
        TODO("Not yet implemented")
    }

}