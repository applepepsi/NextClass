package com.example.nextclass.repository

import com.example.nextclass.Data.AllScore
import com.example.nextclass.Data.ClassData
import com.example.nextclass.Data.ClassScore
import com.example.nextclass.Data.ClassUUid
import com.example.nextclass.Data.PostClassScoreList
import com.example.nextclass.Data.ServerResponse

interface TimeTableRepository {

    fun postTimeTableData(classData: ClassData, callback: (ServerResponse<Any>?) -> Unit)

    fun postModifyTimeTableData(classData: ClassData, callback: (ServerResponse<Any>?) -> Unit)

    fun postDeleteTimeTableData(classUUid: ClassUUid, callback: (ServerResponse<Any>?) -> Unit)

    fun getCurrentTimeTableData(semester: String, callback: (ServerResponse<List<ClassData>>?) -> Unit)

    fun getScore(callback: (ServerResponse<AllScore>?) -> Unit)

    fun postUpdateScoreData(allScore: PostClassScoreList, callback: (ServerResponse<Any>?) -> Unit)

}