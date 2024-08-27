package com.example.nextclass.Data.CommunityData

import java.time.LocalDateTime

data class SearchData(
    val post_sequence:String?=null,    //게시물의 고유번호
    val sort:String="모든 게시물", //all : 모든 데이터, my_school : 내학교, vote : 추천 10개 이상
    val size:Int=10,   //한번에 받을 게시물의 수
    val search_word:String=""
)
