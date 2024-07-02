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
import javax.inject.Inject


class UserInfoRepositoryImpl @Inject constructor(
    private val api: API
) :UserInfoRepository{

    override fun joinIdDuplicateCheck(id: String,callback: (ServerResponse?) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val idRequest = DuplicateCheckRequest(id=id)

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
                val emailRequest = DuplicateCheckRequest(email=email)
                Log.d("emailRequest", emailRequest.toString())
                val response = api.emailDuplicateCheck(emailRequest)
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
                val response = api.postUserJoinInfo(userJoinInfo)
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

    override fun postUserLoginInfo(userLoginInfo: LoginRequest,callback: (ServerResponse?) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.postUserLoginForm(userLoginInfo)
                if (response.isSuccessful){
                    Log.d("로그인.body()", response.body().toString())
                    response.body()
                } else{
                    Log.d("로그인 실패","id중복체크 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("로그인 실패",e.toString())
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