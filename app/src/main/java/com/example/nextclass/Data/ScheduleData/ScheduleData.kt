package com.example.nextclass.Data.ScheduleData

import java.time.LocalDateTime
import java.util.Date

data class ScheduleData(
    val content:String="",
    val alarm_time:LocalDateTime= LocalDateTime.now(),
    val create_time:LocalDateTime=LocalDateTime.now()
)
