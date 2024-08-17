package com.example.nextclass.Data.CommunityData

import java.time.LocalDateTime

data class CommunityCommentData(
    val post_sequence:String?=null,
    val comment_sequence:String?=null,
    val content:String="",
    val is_secret:Boolean=false,
    val commentTime: String = LocalDateTime.now().toString(),
    val commentLikeCount:Int=0,
)
