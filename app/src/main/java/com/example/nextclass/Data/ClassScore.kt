package com.example.nextclass.Data



data class AllScore(
    val average_grade:String="0",
    val credit_sum:Int=0,
    val require_credit:Int=174,
    val semesterList: List<SingleSemesterScore> = emptyList()
)

data class SingleSemesterScore(
    val semester:String="",
    val score:String="",
    val dataList:List<ClassScore> = emptyList()
)

data class ClassScore(
    val uuid:String?=null,
    val title:String="",
    val credit:Int=1,
    val grade:Int=1,
    val category: String="",
    val achievement:String="",
    val student_score: Double? =null,
    val average_socre:Double?=null,
    val standard_deviation:Double?=null,
    val semester:String=""
)




