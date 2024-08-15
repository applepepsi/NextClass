package com.example.nextclass.repository

import com.example.nextclass.Data.TimeTableData.AllScore
import com.example.nextclass.Data.TimeTableData.ClassData
import com.example.nextclass.Data.TimeTableData.ClassUUid
import com.example.nextclass.Data.TimeTableData.PostClassScoreList
import com.example.nextclass.Data.ServerResponse

interface TimeTableRepository {

    fun postTimeTableData(classData: ClassData, callback: (ServerResponse<Any>?) -> Unit)

    fun postModifyTimeTableData(classData: ClassData, callback: (ServerResponse<Any>?) -> Unit)

    fun postDeleteTimeTableData(classUUid: ClassUUid, callback: (ServerResponse<Any>?) -> Unit)

    fun getCurrentTimeTableData(semester: String, callback: (ServerResponse<List<ClassData>>?) -> Unit)

    fun getScore(callback: (ServerResponse<AllScore>?) -> Unit)

    fun postUpdateScoreData(allScore: PostClassScoreList, callback: (ServerResponse<Any>?) -> Unit)

}