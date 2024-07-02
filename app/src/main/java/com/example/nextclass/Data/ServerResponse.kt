package com.example.nextclass.Data

data class ServerResponse(
    val code: Int,
    val description: String,
    val data: TokenData? = null,
    val errorCode: String? = null,
    val errorDescription: String? = null,
    val accessToken:String?=null,
)


data class TokenData(
    val accessToken: String="",
    val refreshToken: String=""
)