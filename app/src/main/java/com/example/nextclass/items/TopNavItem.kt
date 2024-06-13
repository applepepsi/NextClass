package com.example.nextclass.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.ui.Modifier
import com.example.nextclass.R

sealed  class TopNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {

    object Login : TopNavItem("로그인", R.drawable.single_line, LOGIN)
    object Join : TopNavItem("회원 가입", R.drawable.single_line ,JOIN)



    companion object{
        const val LOGIN = "LOGIN"
        const val JOIN = "JOIN"

    }
}


