package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(

) :UserInfoRepository{


    override fun joinIdDuplicateCheck(id: String,callback: (ServerResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = RetrofitBuilder.api.idDuplicateCheck(id)
                if (response.isSuccessful){
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
                val response = RetrofitBuilder.api.emailDuplicateCheck(email)
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


//성공시
//code : 200,
//description : "SUCCESS"

//실패시
//{
//    "code":400
//    "description: "FAIL"
//    "errorCode" : E00101,
//    "errorDescription" : "유효하지 않은 Json 형식입니다."
//}