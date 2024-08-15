package com.example.nextclass.Data.CommunityData

import java.time.LocalDateTime

data class CommunityCommentData(
    val commentDetail:String="",
    val commentTime: LocalDateTime = LocalDateTime.now(),
    val commentLikeCount:Int=0,
)
