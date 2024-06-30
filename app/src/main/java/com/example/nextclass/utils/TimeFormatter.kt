package com.example.nextclass.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

object TimeFormatter {

    fun formatDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일")

        return date.format(formatter)
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

}