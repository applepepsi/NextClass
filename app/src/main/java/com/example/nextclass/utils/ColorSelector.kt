package com.example.nextclass.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.nextclass.Data.TimeTableData.ClassData

object ColorSelector {

    val allColors= listOf(
        "FFFF6A5F", "FFB0E0E6", "FFFF6347", "FF98FB98",
        "FFAFEEEE", "FFFFC0CB", "FFF0E68C", "FFFFD700",
        "FFADD8E6", "FFFFA07A", "FFE6E6FA", "FFDDA0DD",
        "FFF5DEB3", "FFFAF0E6", "FFFFE4E1", "FFE0FFFF",
        "FFF5F5F5", "FFB0C4DE", "FFFA8072", "FFE0B0FF",
        "FFD8BFD8", "FFB22222", "FFDEB887", "FFCD5C5C",
        "FFD3D3D3", "FF20B2AA", "FF7B68EE", "FF4682B4",
        "FF87CEEB", "FF00FA9A", "FF8FBC8B", "FF9400D3",
        "FFADFF2F", "FF7FFF00", "FF8B4513", "FFFAFAD2",
        "FF9ACD32", "FFC0C0C0", "FFFFF0F5", "FFFAEBD7"
    )


    fun colorSelector(classDataList: List<ClassData>): String {

        val usedColors = classDataList.map { it.color }

        val unusedColor = allColors.first { color ->
            color !in usedColors
        }

        return unusedColor
    }


}