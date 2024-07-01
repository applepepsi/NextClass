package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    retrofit: Retrofit
):ScheduleRepository {

    private val api: API = retrofit.create(API::class.java)

    override fun tokenCheck(callback: (ServerResponse?) -> Unit) {
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