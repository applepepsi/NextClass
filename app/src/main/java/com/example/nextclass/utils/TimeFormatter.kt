package com.example.nextclass.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Locale

object TimeFormatter {

    fun formatDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일")

        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date: String): String {
        val dateTime = LocalDateTime.parse(date)

        val formatter = DateTimeFormatter.ofPattern("yyyy/M/d H시 m분")

        return dateTime.format(formatter)
    }

    fun formatTime(time: LocalTime): String {
        val outputFormatter = DateTimeFormatterBuilder()
            // 오전/오후
            .appendText(ChronoField.AMPM_OF_DAY)
            .appendLiteral(' ')
            // 12시간제
            .appendValue(ChronoField.HOUR_OF_AMPM)
            .appendLiteral("시 ")
            .appendValue(ChronoField.MINUTE_OF_HOUR)
            .appendLiteral("분")
            .toFormatter()

        return time.format(outputFormatter)
    }

    fun formatTimeAndSplit(timeString: String):Pair<String,String>{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val time = LocalDateTime.parse(timeString, formatter)

        // LocalDate와 LocalTime을 포맷팅
        val dateString = formatDate(time.toLocalDate())
        val timeStringFormatted = formatTime(time.toLocalTime())

        return Pair(dateString, timeStringFormatted)
    }

    fun formatDayAndYearMonthSplit(time:String): Pair<String, String> {
        val date = LocalDate.parse(time, DateTimeFormatter.ISO_DATE)
        val day = date.dayOfMonth.toString()
        val yearMonth = date.format(DateTimeFormatter.ofPattern("yyyy년 MM월"))

        return Pair(day,yearMonth)
    }

    fun addYearSemester(year:String,semester:String):String{
        return "$year-$semester"
    }




    @RequiresApi(Build.VERSION_CODES.O)
    fun timeAgo(stringDate: String): String {
        Log.d("stringDate",stringDate.toString())

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]")
        val dateTime = try {
            LocalDateTime.parse(stringDate, formatter)
        } catch (e: Exception) {
            return "오류"
        }
        val now = LocalDateTime.now()

        val years = ChronoUnit.YEARS.between(dateTime, now)
        val months = ChronoUnit.MONTHS.between(dateTime, now)
        val days = ChronoUnit.DAYS.between(dateTime, now)
        val hours = ChronoUnit.HOURS.between(dateTime, now)
        val minutes = ChronoUnit.MINUTES.between(dateTime, now)
        val seconds = ChronoUnit.SECONDS.between(dateTime, now)

        return when {
            seconds < 60 -> "방금 전"
            minutes < 60 -> if (minutes == 1L) "1분 전" else "${minutes}분 전"
            hours < 24 -> if (hours == 1L) "1시간 전" else "${hours}시간 전"
            days < 30 -> if (days == 1L) "1일 전" else "${days}일 전"
            months < 12 -> if (months == 1L) "한달 전" else "${months}달 전"
            else -> if (years == 1L) "1년 전" else "${years}년 전"
        }
    }

}