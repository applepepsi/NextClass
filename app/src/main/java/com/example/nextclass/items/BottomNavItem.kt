package com.example.nextclass.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.nextclass.R

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {

    object Home : BottomNavItem("home", R.drawable.home_icon,HOME)
    object Timetable : BottomNavItem("timeTable", R.drawable.time_table_icon,TIMETABLE)
    object Schedule : BottomNavItem("schedule", R.drawable.schedule_icon,SCHEDULE)
    object UserProfile : BottomNavItem("userProfile", R.drawable.user_profile_icon, USER_PROFILE )

    object Community : BottomNavItem("community", R.drawable.community_icon, COMMUNITY )

    companion object{
        const val HOME = "HOME"
        const val TIMETABLE = "TIMETABLE"
        const val SCHEDULE = "SCHEDULE"
        const val USER_PROFILE = "USER_PROFILE"
        const val COMMUNITY = "COMMUNITY"
    }
}



