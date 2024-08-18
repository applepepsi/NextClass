package com.example.nextclass.Data.CommunityData

data class CommentListData(
    val post_sequence:String?=null,    //게시물의 고유번호
    val comment_sequence:Int?=null,
    val size:Int=10,   //한번에 받을 게시물의 수
)
