package com.example.nextclass.repository

import android.util.Log
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


    override fun tokenCheck(callback: (ServerResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.tokenTest()
            val result = try {

                Log.d("토큰체크 결과", response.toString())
                if (response.isSuccessful) {

                    Log.d("스케쥴 입력 결과", response.body().toString())

                    response.body()
                } else {
                    val errorBody = response.errorBody()?.string()
                    val serverResponse = parseErrorBody(errorBody)
                    Log.d("에러", serverResponse.toString())
                    serverResponse?.description
                }
                response.body()
            } catch (e: Exception) {
                Log.d("알 수 없는 오류", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }
    private fun parseErrorBody(errorBody: String?): ServerResponse? {
        return try {

            errorBody?.let {
                Gson().fromJson(it, ServerResponse::class.java)
            }
        } catch (e: Exception) {
            Log.d("에러 파싱 실패", e.toString())
            null
        }
    }
}

