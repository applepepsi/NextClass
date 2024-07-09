package com.example.nextclass.utils

object ConvertDayOfWeek {

    fun convertDayOfWeek(week:String):String{
        return when(week){
            "월"->"mon"
            "화"->"tue"
            "수"->"wed"
            "목"->"thu"
            "금"->"fri"
            else->throw IllegalArgumentException("유효하지 않은 요일: $week")
        }
    }
}