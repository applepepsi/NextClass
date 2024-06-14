package com.example.nextclass.utils

object CutEntranceYear {

    fun cutEntranceYear(entranceYear:String):Int{
        return when(entranceYear){
            "1학년"->1
            "2학년"->2
            "3학년"->3
            "졸업생"->0
            else->throw IllegalArgumentException("유효하지 않은 학년: $entranceYear")
        }
    }
}