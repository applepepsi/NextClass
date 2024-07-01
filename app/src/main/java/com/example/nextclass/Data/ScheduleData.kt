package com.example.nextclass.Data

import java.time.LocalDateTime
import java.util.Date

data class ScheduleData(
    val scheduleDetail:String="",
    val scheduleDate:LocalDateTime= LocalDateTime.now(),
)
