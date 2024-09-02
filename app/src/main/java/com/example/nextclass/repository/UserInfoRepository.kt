package com.example.nextclass.repository

import com.example.nextclass.Data.UserInfoData.ChangeEmail
import com.example.nextclass.Data.UserInfoData.ChangePassword
import com.example.nextclass.Data.SignupOrLoginData.FindIDOrPasswordData
import com.example.nextclass.Data.SignupOrLoginData.JoinRequest
import com.example.nextclass.Data.SignupOrLoginData.LoginRequest
import com.example.nextclass.Data.UserInfoData.PostUserData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData
import com.example.nextclass.Data.UserData
import com.example.nextclass.Data.UserInfoData.NotificationConfig
import com.example.nextclass.Data.VerifyCodeData

interface UserInfoRepository {


    fun joinIdDuplicateCheck(id: String,callback: (ServerResponse<Any>?) -> Unit)

    fun emailDuplicateCheck(email: String,callback: (ServerResponse<Any>?) -> Unit)

    fun postUserJoinInfo(userJoinInfo: JoinRequest, callback: (ServerResponse<Any>?)->Unit)

    fun postUserLoginInfo(userLoginInfo: LoginRequest, callback: (ServerResponse<TokenData>?) -> Unit)

    fun getUserInfo(callback: (ServerResponse<UserData>?) -> Unit)

    fun postChangePasswordData(changePassword: ChangePassword, callback: (ServerResponse<Any>?) -> Unit)

    fun postChangeEmailData(changeEmail: ChangeEmail, callback: (ServerResponse<Any>?) -> Unit)

    fun postChangeEmailRequest(changeEmail: ChangeEmail, callback: (ServerResponse<Any>?) -> Unit)

    fun postChangeUserInfoData(changeUserData: PostUserData, callback: (ServerResponse<Any>?) -> Unit)

    fun getVerifyCode(email: VerifyCodeData, callback: (ServerResponse<Any>?) -> Unit)

    fun verifyCodeCheck(mailCheckBody: VerifyCodeData, callback: (ServerResponse<Any>?) -> Unit)

    fun postFindId(email: FindIDOrPasswordData, callback: (ServerResponse<Any>?) -> Unit)

    fun postFindPassword(id: FindIDOrPasswordData, callback: (ServerResponse<Any>?) -> Unit)

    fun deleteUser(password:String, callback: (ServerResponse<Any>?) -> Unit)

    fun getNotificationState(callback: (ServerResponse<List<NotificationConfig>>?) -> Unit)

    fun changeNotificationState(notificationConfig: NotificationConfig,callback: (ServerResponse<Any>?) -> Unit)
}