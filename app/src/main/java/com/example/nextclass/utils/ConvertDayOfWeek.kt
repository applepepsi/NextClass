package com.example.nextclass.utils

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
}