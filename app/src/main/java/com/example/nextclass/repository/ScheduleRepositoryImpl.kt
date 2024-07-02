package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.utils.EXPIRED_ACCESS_TOKEN
import com.example.nextclass.utils.TokenManager
import com.example.oneplusone.serverConnection.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val api: API
) : ScheduleRepository {


    override fun tokenCheck(callback: (String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.tokenTest()
            val result = try {

                Log.d("토큰체크 결과", response.toString())
                if (response.isSuccessful) {
//                    if(response.body()!!.errorCode== EXPIRED_ACCESS_TOKEN){
//
//                    }
                    Log.d("스케쥴 입력 결과", response.body().toString())
                    response.body()
                } else {
                    Log.d("에러", response.errorBody()?.string().toString())
                    null
                }
            } catch (e: Exception) {
                Log.d("서버 연결 실패", e.toString())
                null
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }
}

