package com.example.nextclass.repository.testRepo

import com.example.nextclass.Data.AllScore
import com.example.nextclass.Data.ClassData
import com.example.nextclass.Data.ClassScore
import com.example.nextclass.Data.ClassUUid
import com.example.nextclass.Data.PostClassScoreList
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.repository.TimeTableRepository
import com.example.oneplusone.serverConnection.API
import retrofit2.Retrofit

class TimeTableTestRepo:TimeTableRepository {


    override fun postTimeTableData(classData: ClassData, callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun postModifyTimeTableData(
        classData: ClassData,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun postDeleteTimeTableData(
        classUUid: ClassUUid,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getCurrentTimeTableData(
        semester: String,
        callback: (ServerResponse<List<ClassData>>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getScore(callback: (ServerResponse<AllScore>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun postUpdateScoreData(
        allScore: PostClassScoreList,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }




}