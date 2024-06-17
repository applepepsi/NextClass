package com.example.nextclass.Data

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.LocalTime

data class ClassData(
    val className: String,
    val teacherName:String,
    val dayOfWeek:Int,
    val startClassTime: Int,
    val endClassTime: Int,
    val credit:Int,
    val color: Color = Color.Gray,
)
