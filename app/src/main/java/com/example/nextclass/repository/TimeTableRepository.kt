package com.example.nextclass.repository

import com.example.nextclass.Data.ClassData
import com.example.nextclass.Data.ServerResponse

interface TimeTableRepository {

    fun postTimeTableData(classData: ClassData, callback: (ServerResponse?) -> Unit)

    fun postModifyTimeTableData(classData: ClassData, callback: (ServerResponse?) -> Unit)

    fun postDeleteTimeTableData(classData: ClassData, callback: (ServerResponse?) -> Unit)
}