package com.example.nextclass.Data.CommunityData

data class PostWriteData(
    val post_sequence:Int?=0,
    val subject:String="",
    val content:String="",
    val is_secret:Boolean=false
)
