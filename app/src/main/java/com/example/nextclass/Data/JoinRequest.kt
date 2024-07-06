package com.example.nextclass.Data

import kotlinx.serialization.Serializable


@Serializable
data class JoinRequest(
    val id: String="",
    val name: String="",
    val password: String="",
    val email: String="",
    val member_grade: Int=1,
    val member_school: String="",
)
