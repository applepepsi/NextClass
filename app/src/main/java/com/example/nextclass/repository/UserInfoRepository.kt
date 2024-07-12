package com.example.nextclass.repository

import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.LoginRequest
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData
import com.example.nextclass.Data.UserData
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

}