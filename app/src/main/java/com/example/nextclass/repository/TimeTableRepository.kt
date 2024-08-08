package com.example.nextclass.repository

import com.example.nextclass.Data.AllScore
import com.example.nextclass.Data.ClassData
import com.example.nextclass.Data.ClassUUid
import com.example.nextclass.Data.ServerResponse

interface TimeTableRepository {

    fun postTimeTableData(classData: ClassData, callback: (ServerResponse<Any>?) -> Unit)

    fun postModifyTimeTableData(classData: ClassData, callback: (ServerResponse<Any>?) -> Unit)

    fun postDeleteTimeTableData(classUUid: ClassUUid, callback: (ServerResponse<Any>?) -> Unit)

    fun getCurrentTimeTableData(semester: String, callback: (ServerResponse<List<ClassData>>?) -> Unit)

    fun getScore(callback: (ServerResponse<AllScore>?) -> Unit)

    fun postUpdateScoreData(allScore: AllScore, callback: (ServerResponse<Any>?) -> Unit)

}