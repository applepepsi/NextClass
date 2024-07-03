package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.DuplicateCheckRequest
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.LoginRequest
import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class TestRepository : UserInfoRepository {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/")
        .build()

    private val api: API = retrofit.create(API::class.java)
    override fun joinIdDuplicateCheck(id: String,callback: (ServerResponse?) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val idRequest = DuplicateCheckRequest(id)

                val response = api.idDuplicateCheck(idRequest)
                Log.d("responseCheck", idRequest.toString())
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
    override fun emailDuplicateCheck(email: String,callback: (ServerResponse?) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val checkRequest = DuplicateCheckRequest("email")
                val response = api.emailDuplicateCheck(checkRequest)
                if (response.isSuccessful){
                    response.body()
                } else{
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
    override fun postUserJoinInfo(userJoinInfo: JoinRequest, callback: (ServerResponse?) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.postUserJoinInfo(userJoinInfo)
                if (response.isSuccessful){
                    response.body()
                } else{
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

    override fun postUserLoginInfo(userLoginInfo: LoginRequest, callback: (ServerResponse?) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.postUserLoginForm(userLoginInfo)
                if (response.isSuccessful){
                    response.body()
                } else{
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