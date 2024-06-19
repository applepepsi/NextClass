package com.example.nextclass.items

import com.example.nextclass.R

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {

    object Home : BottomNavItem("home", R.drawable.home_icon,HOME)
    object Timetable : BottomNavItem("timeTable", R.drawable.favorite_icon,TIMETABLE)
    object Schedule : BottomNavItem("schedule", R.drawable.map_icon,SCHEDULE)



    companion object{
        const val HOME = "HOME"
        const val TIMETABLE = "TIMETABLE"
        const val SCHEDULE = "SCHEDULE"
    }
}



