package com.example.nextclass.Data

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.LocalTime

data class ClassData(
    var className: String = "",
    var grade: String = "1학년",
    var teacherName: String = "",
    var credit: Int = 1,
    var dayOfWeek: String = "월",
    var startClassTime: Int = 1,
    var endClassTime: Int = 1,
    var schoolName: String = ""
)
