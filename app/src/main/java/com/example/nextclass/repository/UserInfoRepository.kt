package com.example.nextclass.repository

import com.example.nextclass.Data.ChangePassword
import com.example.nextclass.Data.ChangeUserData
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.LoginRequest
import com.example.nextclass.Data.PostUserData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData
import com.example.nextclass.Data.UserData
import com.example.nextclass.Data.VerifyCodeData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoRepository {


    fun joinIdDuplicateCheck(id: String,callback: (ServerResponse<Any>?) -> Unit)

    fun emailDuplicateCheck(email: String,callback: (ServerResponse<Any>?) -> Unit)

    fun postUserJoinInfo(userJoinInfo: JoinRequest, callback: (ServerResponse<Any>?)->Unit)

    fun postUserLoginInfo(userLoginInfo: LoginRequest,callback: (ServerResponse<TokenData>?) -> Unit)

    fun getUserInfo(callback: (ServerResponse<UserData>?) -> Unit)

    fun postChangePasswordData(changePassword: ChangePassword,callback: (ServerResponse<Any>?) -> Unit)


    fun postChangeUserInfoData(changeUserData: PostUserData,callback: (ServerResponse<Any>?) -> Unit)


    //서버 응답 아직 모름 수정해야함
    fun getVerifyCode(email: VerifyCodeData, callback: (ServerResponse<Any>?) -> Unit)

    fun verifyCodeCheck(mailCheckBody: VerifyCodeData, callback: (ServerResponse<Any>?) -> Unit)
}