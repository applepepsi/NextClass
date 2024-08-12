package com.example.nextclass.repository.testRepo

import android.util.Log
import com.example.nextclass.Data.ChangeEmail
import com.example.nextclass.Data.ChangePassword
import com.example.nextclass.Data.DuplicateCheckRequest
import com.example.nextclass.Data.FindIDOrPasswordData
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.LoginRequest
import com.example.nextclass.Data.PostUserData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData
import com.example.nextclass.Data.UserData
import com.example.nextclass.Data.VerifyCodeData
import com.example.nextclass.repository.UserInfoRepository
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
    override fun joinIdDuplicateCheck(id: String,callback: (ServerResponse<Any>?) -> Unit) {

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
    override fun emailDuplicateCheck(email: String,callback: (ServerResponse<Any>?) -> Unit){
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
    override fun postUserJoinInfo(userJoinInfo: JoinRequest, callback: (ServerResponse<Any>?) -> Unit){
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

    override fun postUserLoginInfo(userLoginInfo: LoginRequest, callback: (ServerResponse<TokenData>?) -> Unit){
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

    override fun getUserInfo(callback: (ServerResponse<UserData>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.getUserInfo()
                if (response.isSuccessful){
                    Log.d("사용자 정보 가져오기 실패", response.body().toString())
                    response.body()
                } else{
                    Log.d("사용자 정보 가져오기 실패","사용자 정보 가져오기 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("사용자 정보 가져오기 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postChangePasswordData(changePassword: ChangePassword, callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.postChangePasswordData(changePassword)
                if (response.isSuccessful){
                    Log.d("비밀번호 변경 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("비밀번호 변경 실패 실패","비밀번호 변경 실패 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("비밀번호 변경 실패 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postChangeEmailData(
        changeEmail: ChangeEmail,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun postChangeEmailRequest(
        changeEmail: ChangeEmail,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun postChangeUserInfoData(changeUserData: PostUserData, callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.postChangeUserInfoData(changeUserData)
                if (response.isSuccessful){
                    Log.d("사용자 정보 변경 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("사용자 정보 변경 성공","사용자 정보 변경 성공")
                    null
                }
            } catch (e: Exception) {
                Log.d("사용자 정보 변경 성공",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun getVerifyCode(email: VerifyCodeData, callback: (ServerResponse<Any>?) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.getVerifyCode(email)
                if (response.isSuccessful){
                    Log.d("코드 발급 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("코드 발급 실패","코드 발급 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("코드 발급 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun verifyCodeCheck(mailCheckBody: VerifyCodeData, callback: (ServerResponse<Any>?) -> Unit){
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.verifyCodeCheck(mailCheckBody)
                if (response.isSuccessful){
                    Log.d("코드 발급 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("코드 발급 실패","코드 발급 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("코드 발급 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postFindId(email: FindIDOrPasswordData, callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun postFindPassword(
        id: FindIDOrPasswordData,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(password: String, callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }
}