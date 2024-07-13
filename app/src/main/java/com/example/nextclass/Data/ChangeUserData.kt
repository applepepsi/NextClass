package com.example.nextclass.Data

data class ChangeUserData(
    val name:String="",
    val member_grade:String="",
    val member_school:String=""
)

data class PostUserData(
    val name:String="",
    val member_grade:Int=1,
    val member_school:String=""
)