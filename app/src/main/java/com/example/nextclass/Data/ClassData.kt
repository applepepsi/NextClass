package com.example.nextclass.Data

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.LocalTime

data class ClassData(
    var week: String = "월",
    var class_grade: Int = 1,
    var class_start_time: Int = 1,
    var class_end_time: Int = 1,
    var teacher_name: String = "",
    var score: Int = 1,
    var title: String = "",
    var school: String = "",
    var semester : String="2025-1"
)

