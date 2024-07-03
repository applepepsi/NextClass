package com.example.oneplusone.serverConnection


import com.example.nextclass.Data.DuplicateCheckRequest
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.LoginRequest
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.utils.DUPLICATED_CHECK_ADDRESS
import com.example.nextclass.utils.LOGIN_ADDRESS
import com.example.nextclass.utils.REGISTER_ADDRESS
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

    @POST(DUPLICATED_CHECK_ADDRESS)
    suspend fun idDuplicateCheck(@Body checkRequest: DuplicateCheckRequest): Response<ServerResponse>

    @POST(DUPLICATED_CHECK_ADDRESS)
    suspend fun emailDuplicateCheck(@Body checkRequest: DuplicateCheckRequest): Response<ServerResponse>


    @POST(REGISTER_ADDRESS)
    suspend fun postUserJoinInfo(@Body joinInfo: JoinRequest):Response<ServerResponse>

    @POST(LOGIN_ADDRESS)
    suspend fun postUserLoginForm(@Body loginInfo: LoginRequest):Response<ServerResponse>


    @POST("/test")
    suspend fun tokenTest():Response<ServerResponse?>

    @POST("/test")
    suspend fun refreshTest():Response<ServerResponse>
}