package com.example.oneplusone.serverConnection


import com.example.nextclass.Data.DuplicateCheckRequest
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.ServerResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDateTime

interface API {


    //key value 형식으로 달라고 했음

    @POST("/duplicated_check")
    suspend fun idDuplicateCheck(@Body checkRequest: DuplicateCheckRequest): Response<ServerResponse>

    @POST("/duplicated_check")
    suspend fun emailDuplicateCheck(@Body checkRequest: DuplicateCheckRequest): Response<ServerResponse>


    @POST("/register")
    suspend fun postUserJoinInfo(@Body joinInfo: JoinRequest):Response<ServerResponse>

    @POST("/api/server/postJoinForm")
    suspend fun postUserLoginForm(@Query("loginInfo")loginInfo: String):Response<ServerResponse>


}