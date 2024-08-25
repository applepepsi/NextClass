package com.example.nextclass.utils

import android.util.Log
import java.time.LocalDate

object ConvertDayOfWeek {

    fun convertDayOfWeek(week:String):String{
        return when(week){
            "월"->"mon"
            "화"->"tue"
            "수"->"wen"
            "목"->"thu"
            "금"->"fri"
            else->week
        }
    }

    fun convertDayOfWeekKorea(week:String):String{
        return when(week){
            "mon" ->"월"
            "tue"->"화"
            "wen" ->"수"
            "thu"->"목"
            "fri"->"금"
            else->week
        }
    }

    fun getCurrentDay():String{
        val currentDate = LocalDate.now()
        val dayOfWeek = currentDate.dayOfWeek.value
        Log.d("오늘의 숫자 요일", dayOfWeek.toString())
        return  when (dayOfWeek) {
            1 -> "mon"
            2 -> "tue"
            3 -> "wen"
            4 -> "thu"
            5 -> "fri"
            6 -> "sat"
            7 -> "sun"
            else -> "mon"
        }
    }
}