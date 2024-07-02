package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class ScheduleTestRepository:ScheduleRepository {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/")
        .build()
    private val api: API = retrofit.create(API::class.java)

    override fun tokenCheck(callback: (String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                val response = api.tokenTest()
                Log.d("토큰체크 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("결과", response.body().toString())
                    response.body()
                } else {
                    Log.d("서벼연결실패","서벼연결실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("서버연결 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }
}