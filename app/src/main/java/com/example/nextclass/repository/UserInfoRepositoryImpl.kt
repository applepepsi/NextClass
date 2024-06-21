package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.DuplicateCheckRequest
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.RetrofitBuilder
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject


class UserInfoRepositoryImpl @Inject constructor(

) :UserInfoRepository{


    override fun joinIdDuplicateCheck(id: String,callback: (ServerResponse?) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val idRequest = DuplicateCheckRequest(id=id)

                val response = RetrofitBuilder.api.idDuplicateCheck(idRequest)
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
                val emailRequest = DuplicateCheckRequest(email=email)
                Log.d("emailRequest", emailRequest.toString())
                val response = RetrofitBuilder.api.emailDuplicateCheck(emailRequest)
                if (response.isSuccessful){
                    Log.d("response.body()", response.body().toString())
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
                Log.d("userJoinInfo", userJoinInfo.toString())
                val response = RetrofitBuilder.api.postUserJoinInfo(userJoinInfo)
                if (response.isSuccessful){
                    Log.d("JoinResponse.body()", response.body().toString())
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