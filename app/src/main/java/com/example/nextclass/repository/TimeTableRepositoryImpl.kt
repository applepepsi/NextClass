package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.ClassData
import com.example.nextclass.Data.DuplicateCheckRequest
import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeTableRepositoryImpl  @Inject constructor(
    private val api: API
) :TimeTableRepository{
    override fun postTimeTableData(classData: ClassData,callback: (ServerResponse?) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                Log.d("classData", classData.toString())
                val response = api.postTimeTableData(classData)
                Log.d("responseCheck", response.toString())
                if (response.isSuccessful){
                    Log.d("response.body()", response.body().toString())
                    response.body()
                } else {
                    Log.d("id중복체크 실패","id중복체크 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("id중복체크 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }


    override fun postModifyTimeTableData(classData: ClassData,callback: (ServerResponse?) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                Log.d("classData", classData.toString())
                val response = api.postModifyTimeTableData(classData)
                Log.d("responseCheck", response.toString())
                if (response.isSuccessful){
                    Log.d("response.body()", response.body().toString())
                    response.body()
                } else {
                    Log.d("id중복체크 실패","id중복체크 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("id중복체크 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postDeleteTimeTableData(classData: ClassData,callback: (ServerResponse?) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                Log.d("classData", classData.toString())
                val response = api.postDeleteTimeTableData(classData)
                Log.d("responseCheck", response.toString())
                if (response.isSuccessful){
                    Log.d("response.body()", response.body().toString())
                    response.body()
                } else {
                    Log.d("id중복체크 실패","id중복체크 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("id중복체크 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

}