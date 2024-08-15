package com.example.nextclass.Data.CommunityData

data class PostListData(
    val post_sequence:Int=0,
    val sort:String="all", //all : 모든 데이터, my_school : 내학교, vote : 추천 10개 이상
    val size:Int=0,
)
