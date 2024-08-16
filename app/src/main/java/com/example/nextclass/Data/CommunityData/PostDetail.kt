package com.example.nextclass.Data.CommunityData

data class PostDetail(
    val subject:String="",
    val content:String="",
    val author:String="",
    val owner:Boolean=false,
    val post_sequence:Int=0,
)
