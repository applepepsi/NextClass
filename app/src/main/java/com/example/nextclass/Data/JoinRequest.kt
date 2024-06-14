package com.example.nextclass.Data

import kotlinx.serialization.Serializable


@Serializable
data class JoinRequest(
    val email: String,
    val joinId: String,
    val joinPassword: String,
    val passwordConfirm: String,
    val name: String,
    val schoolName: String,
    val entranceYear: Int
)
