package com.example.nextclass.Data

import java.time.LocalDateTime

data class CommunityPostData(
    val postName:String="",
    val postDetail:String="",
    val postTime:LocalDateTime= LocalDateTime.now(),
    val commentCount:Int=0,
    val likeCount:Int=0,
)

data class CommunityCommentData(
    val commentDetail:String="",
    val commentTime:LocalDateTime= LocalDateTime.now(),
    val commentLikeCount:Int=0,
)
