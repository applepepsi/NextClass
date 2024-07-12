package com.example.nextclass.Data

data class ServerResponse<T>(
    val code: Int,
    val description: String,
    val data: T?=null,
    val errorCode: String? = null,
    val errorDescription: String? = null,
    val accessToken:String?=null,
)

data class UserData(
    val id: String="",
    val name: String="",
    val email: String="",
    val member_grade: Int=1,
    val member_school: String=""
)


data class TokenData(
    val accessToken: String="",
    val refreshToken: String=""
)