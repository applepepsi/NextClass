package com.example.nextclass.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

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

    private val _entranceYear = mutableStateOf("")
    val entranceYear: State<String> = _entranceYear



    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updateId(newId: String) {
        _id.value = newId
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updatePasswordConfirm(newPasswordConfirm: String) {
        _passwordConfirm.value = newPasswordConfirm
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



    fun setLoginInput(){

        Log.d("checkLoginInput", "id : ${id.value}, password : ${password.value}")

    }

}