package com.example.nextclass.Data.ScheduleData

import java.time.LocalDateTime
import java.util.Date

data class ScheduleData(
    val uuid:String?=null,
    val content:String="",
    val alarm_time:String= LocalDateTime.now().toString(),
    val goal_time:String=LocalDateTime.now().toString()
)
