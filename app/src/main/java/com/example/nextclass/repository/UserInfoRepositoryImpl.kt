package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.UserInfoData.ChangeEmail
import com.example.nextclass.Data.UserInfoData.ChangePassword
import com.example.nextclass.Data.SignupOrLoginData.DuplicateCheckRequest
import com.example.nextclass.Data.SignupOrLoginData.FindIDOrPasswordData
import com.example.nextclass.Data.SignupOrLoginData.JoinRequest
import com.example.nextclass.Data.SignupOrLoginData.LoginRequest
import com.example.nextclass.Data.UserInfoData.PostUserData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData
import com.example.nextclass.Data.UserData
import com.example.nextclass.Data.UserInfoData.NotificationConfig
import com.example.nextclass.Data.VerifyCodeData
import com.example.oneplusone.serverConnection.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UserInfoRepositoryImpl @Inject constructor(
    private val api: API
) :UserInfoRepository{

    override fun joinIdDuplicateCheck(id: String,callback: (ServerResponse<Any>?) -> Unit) {

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

    override fun emailDuplicateCheck(email: String,callback: (ServerResponse<Any>?) -> Unit){
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

    override fun postUserJoinInfo(userJoinInfo: JoinRequest, callback: (ServerResponse<Any>?) -> Unit){
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

    override fun postUserLoginInfo(userLoginInfo: LoginRequest, callback: (ServerResponse<TokenData>?) -> Unit){
        Log.d("userLoginInfo", userLoginInfo.toString())
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

    override fun getUserInfo(callback: (ServerResponse<UserData>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.getUserInfo()
                if (response.isSuccessful){
                    Log.d("사용자 정보 가져오기 성공", response.body().toString())
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
        Log.d("changeEmail2", changeEmail.toString())
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.postChangeEmailData(changeEmail)
                if (response.isSuccessful){
                    Log.d("이메일 변경 인증코드 발급 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("이메일 변경 인증코드 발급 실패","이메일 변경 인증코드 발급 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("이메일 변경 인증코드 발급 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postChangeEmailRequest(
        changeEmail: ChangeEmail,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.postChangeEmailRequest(changeEmail)
                if (response.isSuccessful){
                    Log.d("이메일 변경 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("이메일 변경 실패","이메일 변경 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("이메일 변경 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
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
                    Log.d("코드 인증 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("코드 인증 실패","코드 인증 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("코드 인증 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postFindId(email: FindIDOrPasswordData, callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.findId(email)
                if (response.isSuccessful){
                    Log.d("아이디 찾기 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("아이디 찾기실패","아이디 찾기 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("아이디 찾기 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postFindPassword(
        id: FindIDOrPasswordData,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.findPassword(id)
                if (response.isSuccessful){
                    Log.d("비밀번호 찾기 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("비밀번호 찾기 실패","비밀번호 찾기 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("비밀번호 찾기 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun deleteUser(password: String, callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.deleteUser(password)
                if (response.isSuccessful){
                    Log.d("탈퇴 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("탈퇴 실패","탈퇴 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("탈퇴 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun getNotificationState(callback: (ServerResponse<List<NotificationConfig>>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.getNotificationConfig()
                if (response.isSuccessful){
                    Log.d("설정 가져오기 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("설정 가져오기 실패","설정 가져오기 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("설정 가져오기 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun changeNotificationState(
        notificationConfig: NotificationConfig,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        Log.d("현재 설정", notificationConfig.toString())
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = api.changeNotificationConfig(notificationConfig)
                if (response.isSuccessful){
                    Log.d("설정 변경 성공", response.body().toString())
                    response.body()
                } else{
                    Log.d("설정 변경 실패","설정 변경 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("설정 변경 실패",e.toString())
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