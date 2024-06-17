package com.example.nextclass.timeTableData

import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density

class TimeTableParentData(
    val duration: Float,
    val offset: Float
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = this@TimeTableParentData
}