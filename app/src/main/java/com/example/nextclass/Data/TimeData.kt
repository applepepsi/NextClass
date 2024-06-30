package com.example.nextclass.Data

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class TimeData(
    val selectDate: LocalDate = LocalDate.now(),
    val selectTime:LocalTime= LocalTime.now(),

    )
