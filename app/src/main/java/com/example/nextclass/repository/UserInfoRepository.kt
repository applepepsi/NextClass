package com.example.nextclass.repository

import com.example.nextclass.Data.ServerResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoRepository {


    fun joinIdDuplicateCheck(id: String,callback: (ServerResponse?) -> Unit)

    fun emailDuplicateCheck(email: String,callback: (ServerResponse?) -> Unit)

    fun postUserJoinInfo(userJoinInfo:String,callback: (ServerResponse?)->Unit)

    fun postUserLoginInfo(userJoinInfo: String,callback: (ServerResponse?) -> Unit)
}