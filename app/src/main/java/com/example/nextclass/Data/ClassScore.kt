package com.example.nextclass.Data



data class AllScore(
    val average_grade:String="0",
    val credit_sum:Int=0,
    val require_credit:Int=174,
    val semester_list: List<SingleSemesterScore> = emptyList()
)

data class SingleSemesterScore(
    val semester:String="",
    val score:String="",
    val data_list:List<ClassScore> = emptyList()
)

data class ClassScore(
    val uuid:String?=null,
    val title:String="",
    val credit:Int=1,
    val grade:Int=1,
    val category: String="공통",
    val achievement:String="",
    val student_score : Double? = null,
    val average_score : Double? = null,
    val standard_deviation : Double? = null,
    val semester:String=""
)

data class PostClassScoreList(
    val data:List<ClassScore>
)




