package com.example.nextclass.Data

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.LocalTime

data class ClassData(
    var uuid:String?=null,
    var week: String = "월",
    var class_start_time: Int = 1,
    var class_end_time: Int = 1,
    var semester : String="2025-1",
    var title: String = "",
    var class_grade: Int = 1,
    var teacher_name: String = "",
    var score: Int = 1,
    var school: String = "",

)

data class ClassUUid(
    var uuid:String="",
    )

data class PostSemester(
    val semester: String
)

