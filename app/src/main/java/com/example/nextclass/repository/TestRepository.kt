package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestRepository : UserInfoRepository {
    override fun joinIdDuplicateCheck(id: String,callback: (ServerResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = RetrofitBuilder.api.idDuplicateCheck(id)
                if (response.isSuccessful){
                    response.body()
                } else null
            } catch (e: Exception) {
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
                val response = RetrofitBuilder.api.emailDuplicateCheck(email)
                if (response.isSuccessful){
                    response.body()
                } else null
            } catch (e: Exception) {
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postUserJoinInfo(userJoinInfo: String,callback: (ServerResponse?) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = RetrofitBuilder.api.postUserJoinInfo(userJoinInfo)
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

    override fun postUserLoginInfo(userJoinInfo: String,callback: (ServerResponse?) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = RetrofitBuilder.api.postUserLoginForm(userJoinInfo)
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