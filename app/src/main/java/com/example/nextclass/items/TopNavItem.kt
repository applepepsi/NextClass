package com.example.nextclass.items

import com.example.nextclass.R

sealed  class TopNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {

    object Login : TopNavItem("login", R.drawable.single_line, LOGIN)
    object Join : TopNavItem("timeTable", R.drawable.single_line ,JOIN)



    companion object{
        const val LOGIN = "LOGIN"
        const val JOIN = "JOIN"

    }
}
