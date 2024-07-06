package com.example.nextclass.viewmodel

import android.graphics.Paint.Join
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.repository.UserInfoRepository
import com.example.nextclass.utils.StringValue
import javax.inject.Inject

class UserInfoViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository
): ViewModel(){


    private val _userProfile= mutableStateOf(JoinRequest())
    val userProfile: State<JoinRequest> = _userProfile


    private val _emailChangeState= mutableStateOf(false)
    val emailChangeState: State<Boolean> = _emailChangeState

    private val _passwordChangeState= mutableStateOf(false)
    val passwordChangeState: State<Boolean> = _passwordChangeState

    private val _userInfoChangeState= mutableStateOf(false)
    val userInfoChangeState: State<Boolean> = _userInfoChangeState

    private val _verifyCodeInputError=mutableStateOf(false)
    val verifyCodeInputError: State<Boolean> = _verifyCodeInputError

    private val _verifyCodeInputErrorMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val verifyCodeInputErrorMessage: State<StringValue> = _verifyCodeInputErrorMessage


    fun updateVerifyCode(code: String) {

    }

    fun submitVerifyCode() {
        TODO("Not yet implemented")
    }

}