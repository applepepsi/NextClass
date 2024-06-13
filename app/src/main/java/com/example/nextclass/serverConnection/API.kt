package com.example.oneplusone.serverConnection


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

    @GET("/api/server/idDuplicateCheck")
    suspend fun idDuplicateCheck(@Query("id")id: String): Response<ServerResponse>

    @GET("/api/server/emailDuplicateCheck")
    suspend fun emailDuplicateCheck(@Query("email")email: String): Response<ServerResponse>


    @POST("/api/server/postJoinForm")
    suspend fun postJoinForm(@Query("joinForm")joinForm: String):Response<ServerResponse>

    @POST("/api/server/postJoinForm")
    suspend fun postLoginForm(@Query("loginForm")loginForm: String):Response<ServerResponse>


}