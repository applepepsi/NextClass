package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.ScheduleData.ScheduleData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.utils.EXPIRED_ACCESS_TOKEN
import com.example.nextclass.utils.TokenManager
import com.example.oneplusone.serverConnection.API
import com.google.gson.Gson
import com.google.gson.JsonParseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val api: API
) : ScheduleRepository {
    override fun saveSchedule(singleScheduleData: ScheduleData,callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {

            val result = try {
                val response = api.saveSchedule(singleScheduleData)
                Log.d("보내는 스케쥴", singleScheduleData.toString())
                Log.d("스케쥴 저장결과", response.toString())
                if (response.isSuccessful) {

                    Log.d("스케쥴 저장 성공", response.body().toString())

                    response.body()
                } else {

                    Log.d("스케쥴 저장 실패", "실패")
                }
                response.body()
            } catch (e: Exception) {
                Log.d("스케쥴 저장 실패", e.toString())
                null
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    override fun updateSchedule(
        singleScheduleData: ScheduleData,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val result = try {
                val response = api.updateSchedule(singleScheduleData)
                Log.d("보내는 스케쥴", singleScheduleData.toString())
                Log.d("스케쥴 변경 결과", response.toString())
                if (response.isSuccessful) {

                    Log.d("스케쥴 변경 성공", response.body().toString())

                    response.body()
                } else {

                    Log.d("스케쥴 변경 실패", "실패")
                    null
                }

            } catch (e: Exception) {
                Log.d("스케쥴 변경 실패", e.toString())
                null
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    override fun deleteSchedule(
        singleScheduleData: ScheduleData,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {

            val result = try {
                val response = api.deleteSchedule(singleScheduleData)
                Log.d("보내는 스케쥴", singleScheduleData.toString())
                Log.d("스케쥴 제거 결과", response.toString())
                if (response.isSuccessful) {

                    Log.d("스케쥴 제거 성공", response.body().toString())

                    response.body()
                } else {

                    Log.d("스케쥴 제거 실패", "실패")
                }
                response.body()
            } catch (e: Exception) {
                Log.d("스케쥴 제거 실패", e.toString())
                null
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    override fun getTodoList(callback: (ServerResponse<List<ScheduleData>>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {

            val result = try {
                val response = api.getTodoList()
                Log.d("투두리스트 가져오기 결과", response.toString())
                if (response.isSuccessful) {

                    Log.d("투두리스트 가져오기 성공", response.body().toString())

                    response.body()
                } else {
                    Log.d("투두리스트 가져오기 실패", "실패")
                    response.body()
                }

            } catch (e: Exception) {
                Log.d("투두리스트 가져오기 실패", e.toString())
                null
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }


    override fun tokenCheck(callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {

            val result = try {
                val response = api.tokenTest()
                Log.d("토큰체크 결과", response.toString())
                if (response.isSuccessful) {

                    Log.d("스케쥴 입력 결과", response.body().toString())

                    response.body()
                } else {

                    response.body()
                }

            } catch (e: Exception) {
                Log.d("알 수 없는 오류", e.toString())
                null
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

}

