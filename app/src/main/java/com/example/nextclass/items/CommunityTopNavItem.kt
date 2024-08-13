package com.example.nextclass.items

import com.example.nextclass.R

sealed  class CommunityTopNavItem(
    val title: String, val screenRoute: String
) {

    object AllSchool : CommunityTopNavItem("모든 학교", ALL_SCHOOL)
    object MySchool : CommunityTopNavItem("내 학교",MY_SCHOOL)
    object BestPost : CommunityTopNavItem("인기",BEST_POST)

    companion object{
        const val ALL_SCHOOL="ALL_SCHOOL"
        const val MY_SCHOOL="MY_SCHOOL"
        const val BEST_POST="BEST_POST"
    }
}
