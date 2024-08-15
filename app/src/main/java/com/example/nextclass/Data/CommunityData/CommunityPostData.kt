package com.example.nextclass.Data.CommunityData

import java.time.LocalDateTime

data class CommunityPostData(
    val post_sequence:Int=0,
    val subject:String="",
    val content:String="",
    val author:String="",
    val vote_count:Int=0,
    val comment_count:Int=0,
    val reg_date:String= LocalDateTime.now().toString(),
)


