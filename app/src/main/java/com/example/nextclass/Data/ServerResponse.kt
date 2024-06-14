package com.example.nextclass.Data

data class ServerResponse(
    val code: Int,
    val description: String,
    val errorCode: String? = null,
    val errorDescription: String? = null
)
