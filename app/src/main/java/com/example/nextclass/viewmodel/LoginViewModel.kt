package com.example.nextclass.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.R
import com.example.nextclass.repository.UserInfoRepository
import com.example.nextclass.utils.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository
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

    private val _entranceYear = mutableStateOf("")
    val entranceYear: State<String> = _entranceYear

    private val _passwordVisibility = mutableStateOf(false)
    val passwordVisibility: State<Boolean> = _passwordVisibility

    private val _menuVisibility=mutableStateOf(false)
    val menuVisibility: State<Boolean> = _menuVisibility

    //회원가입에서 입력값 유효성 검사에 사용됨
    private val _emailDuplicateCheck= mutableStateOf(false)
    val emailDuplicateCheck: State<Boolean> = _emailDuplicateCheck

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

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        emailCheck(newEmail)
        _emailDuplicateCheck.value=false
    }

    private fun emailCheck(newEmail: String){

        val pattern: Pattern = Patterns.EMAIL_ADDRESS

        val errorMessage = when {
            !pattern.matcher(newEmail).matches() -> StringValue.StringResource(R.string.wrongEmailType)
            !_emailDuplicateCheck.value -> StringValue.StringResource(R.string.needEmailDuplicateCheck)
            else -> StringValue.Empty
        }
        _emailInputErrorMessage.value = errorMessage
        _emailInputError.value = errorMessage != StringValue.Empty
    }

    fun updateId(newId: String) {
        _id.value = newId
        idCheck(newId)
        //아이디 인증을 하고 텍스트를 바꾸면 인증을 다시하도록
        _idDuplicateCheck.value=false
    }

    private fun idCheck(newId: String){
        val errorMessage = when {
            newId.length !in 5..20 -> StringValue.StringResource(R.string.idSizeLimit)
            !newId.matches(Regex("^[a-zA-Z0-9]*$")) -> StringValue.StringResource(R.string.idOnlyString)
            !_idDuplicateCheck.value -> StringValue.StringResource(R.string.needIdDuplicateCheck)
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
        nameCheck(newName)
    }

    private fun nameCheck(newName: String) {

        val errorMessage = when {
            newName.length >= 11 -> StringValue.StringResource(R.string.nameLimit)
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

    fun emailDuplicateCheck(){

        userInfoRepository.emailDuplicateCheck(email.value){serverResponse ->
            if(serverResponse!=null){
                if(serverResponse.code !=200){
                    _emailDuplicateCheck.value=true
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

    fun idDuplicateCheck(){

        userInfoRepository.idDuplicateCheck(id.value){serverResponse ->
            if(serverResponse!=null){
                if(serverResponse.code !=200){
                    _idDuplicateCheck.value=true
                }
                else{
                    _idInputErrorMessage.value = StringValue.StringResource(R.string.idDuplicate)
                    _idInputError.value = true
                }
            }else{
                _idInputErrorMessage.value = StringValue.StringResource(R.string.duplicateFail)
                _idInputError.value = true
            }
        }
        //아이디 체크 버튼을 누르면 현재 작성된 이메일을 서버로 전송해서 체크해야함

    }

    fun toggleMenuVisibility() {

        _menuVisibility.value = !_menuVisibility.value
    }

    fun setLoginInput(){

        Log.d("checkLoginInput", "id : ${id.value}, password : ${password.value}")
    }

    fun joinComplete(){

        if(joinEmptyAndErrorCheck() && duplicateCheck() )
        {
            Log.d("가입 성공",
                "email : ${email.value}," +
                        "id : ${id.value}," +
                        "password : ${password.value}," +
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

    private fun joinEmptyAndErrorCheck(): Boolean {
        return listOf(
            _email to _emailInputError,
            _id to _idInputError,
            _password to _passwordInputError,
            _passwordConfirm to _passwordConfirmInputError,
            _name to _nameInputError,
            _schoolName to _schoolNameInputError,
            _entranceYear to _entranceYearInputError
        ).all { (userInput, error) -> userInput.value.isNotEmpty() && !error.value }
    }

    private fun duplicateCheck(): Boolean {

        return _idDuplicateCheck.value && _emailDuplicateCheck.value
    }

}