package com.example.nextclass.utils

object CutEntranceYear {

    fun deleteGradeEntranceYear(entranceYear:String):Int{
        return when(entranceYear){
            "1학년"->1
            "2학년"->2
            "3학년"->3
            "졸업생"->0
            else->throw IllegalArgumentException("유효하지 않은 학년: $entranceYear")
        }
    }

    fun addGradeEntranceYear(entranceYear:Int):String{
        return when(entranceYear){
            1->"1학년"
            2->"2학년"
            3->"3학년"
            0->"졸업생"
            else->throw IllegalArgumentException("유효하지 않은 학년: $entranceYear")
        }
    }
}