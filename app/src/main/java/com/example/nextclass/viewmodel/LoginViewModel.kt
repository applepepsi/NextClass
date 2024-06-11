package com.example.nextclass.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource

import androidx.lifecycle.MutableLiveData
import com.example.nextclass.R
import com.example.nextclass.utils.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

):ViewModel(){


    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _id = mutableStateOf("")
    val id: State<String> = _id

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _passwordConfirm = mutableStateOf("")
    val passwordConfirm: State<String> = _passwordConfirm

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _schoolName = mutableStateOf("")
    val schoolName: State<String> = _schoolName

    private val _entranceYear = mutableStateOf("입학 년도")
    val entranceYear: State<String> = _entranceYear

    private val _emailCheck= mutableStateOf(false)
    val emailCheck: State<Boolean> = _emailCheck

    private val _idDuplicateCheck= mutableStateOf(false)
    val idDuplicateCheck: State<Boolean> = _idDuplicateCheck

    private val _idInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val idInputErrorMessage: State<StringValue> = _idInputErrorMessage

    private val _idInputError= mutableStateOf(false)
    val idInputError: State<Boolean> = _idInputError

    private val _passwordInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val passwordInputErrorMessage: State<StringValue> = _passwordInputErrorMessage

    private val _passwordInputError= mutableStateOf(false)
    val passwordInputError: State<Boolean> = _passwordInputError

    private val _passwordConfirmInputErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val passwordConfirmInputErrorMessage: State<StringValue> = _passwordConfirmInputErrorMessage

    private val _passwordConfirmInputError= mutableStateOf(false)
    val passwordConfirmInputError: State<Boolean> = _passwordConfirmInputError

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    private val _menuVisibility=mutableStateOf(false)
    val menuVisibility: State<Boolean> = _menuVisibility


    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updateId(newId: String) {
        _id.value = newId
        idCheck(newId)
    }

    private fun idCheck(newId: String){
        val errorMessage = when {
            newId.length !in 5..20 -> StringValue.StringResource(R.string.idSizeLimit)
            !newId.matches(Regex("^[a-zA-Z0-9]*$")) -> StringValue.StringResource(R.string.idOnlyString)
            else -> StringValue.Empty
        }
        _idInputErrorMessage.value = errorMessage
        _idInputError.value = errorMessage != StringValue.Empty
    }

    fun updatePassword(newPassword: String) {
        
        _password.value = newPassword
        passwordCheck(newPassword)
    }
    private fun passwordCheck(newPassword: String) {
        val errorMessage = when {
            newPassword.length !in 8..16 -> StringValue.StringResource(R.string.wrongPasswordLimit)
            !newPassword.matches(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).+$")) -> StringValue.StringResource(R.string.wrongPasswordRule)
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
            newPasswordConfirm !=_password.value -> StringValue.StringResource(R.string.wrongPasswordConfirm)
            else -> StringValue.Empty
        }

        _passwordConfirmInputErrorMessage.value = errorMessage
        _passwordConfirmInputError.value = errorMessage != StringValue.Empty
    }

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updateSchoolName(newSchoolName: String) {
        _schoolName.value = newSchoolName
    }

    fun updateEntranceYear(newEntranceYear: String) {
        _entranceYear.value = newEntranceYear
    }

    fun togglePasswordVisibility() {
        _passwordVisibility.value = !_passwordVisibility.value
    }

    fun emailCheck(){
        //이메일 체크 버튼을 누르면 현재 작성된 이메일을 서버로 전송해서 체크해야함
        _emailCheck.value=!_emailCheck.value
    }

    fun idDuplicateCheck(){
        //이메일 체크 버튼을 누르면 현재 작성된 이메일을 서버로 전송해서 체크해야함
        _idDuplicateCheck.value=!_idDuplicateCheck.value
    }

    fun toggleMenuVisibility() {
        _menuVisibility.value = !_menuVisibility.value
    }

    fun setLoginInput(){

        Log.d("checkLoginInput", "id : ${id.value}, password : ${password.value}")
    }

    fun joinComplete(){
        Log.d("checkLoginInput",
            "email : ${email.value}," +
                "id : ${id.value}," +
                "password : ${password.value}," +
                "passwordConfirm : ${passwordConfirm.value}," +
                "name : ${name.value},"+
                "schoolName : ${schoolName.value},"+
                "entranceYear : ${entranceYear.value},")

    }

}