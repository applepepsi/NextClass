package com.example.nextclass.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

object GetSemester {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentSemester(): String {
        val currentDate = LocalDate.now()
        val year = currentDate.year
        val month = currentDate.monthValue

        val (adjustedYear, semester) = when (month) {
            in 3..7 -> year to 1
            in 8..12 -> year to 2
            else -> (year - 1) to 2
        }
        return "$adjustedYear-$semester"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertCurrentSemester():String{
        val currentDate = LocalDate.now()
        val year = currentDate.year
        val month = currentDate.monthValue

        val (adjustedYear, semester) = when (month) {
            in 3..7 -> year to 1
            in 8..12 -> year to 2
            else -> (year - 1) to 2
        }
        return "${adjustedYear}년 ${semester}학기"
    }

    fun convertSemester(semester:String):String{
        return if(semester!=""){
            val parts = semester.split("-")

            val year = parts[0]
            val term = parts[1]

            "${year}년 ${term}학기"
        }else{
            semester
        }

    }

}