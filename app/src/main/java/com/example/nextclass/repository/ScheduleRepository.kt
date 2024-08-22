package com.example.nextclass.repository

import com.example.nextclass.Data.ScheduleData.ScheduleData
import com.example.nextclass.Data.ServerResponse

interface ScheduleRepository {

    fun saveSchedule(singleScheduleData: ScheduleData,callback: (ServerResponse<Any>?)->Unit)

    fun getTodoList(callback: (ServerResponse<List<ScheduleData>>?)->Unit)

    fun tokenCheck(callback: (ServerResponse<Any>?) -> Unit)
}