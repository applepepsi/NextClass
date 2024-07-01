package com.example.nextclass.repository

import com.example.nextclass.Data.ServerResponse

interface ScheduleRepository {

    fun tokenCheck(callback: (ServerResponse?) -> Unit)
}