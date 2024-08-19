package com.example.nextclass.Data.CommunityData

import java.time.LocalDateTime

data class CommunityCommentData(
    val post_sequence:String="0",
    val comment_sequence:String="0",
    val content:String="",
    val author:String="익명",
    val is_secret:Boolean=false,
    val is_owner:Boolean=true,
    val reg_date: String = LocalDateTime.now().toString(),
    val vote_count:Int=0,
    val is_vote:Boolean=false
)
