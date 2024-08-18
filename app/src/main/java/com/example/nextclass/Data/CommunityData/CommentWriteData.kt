package com.example.nextclass.Data.CommunityData

data class CommentWriteData(
    val post_sequence:String?="0",
    val content:String="",
    val is_secret:Boolean=false
)
