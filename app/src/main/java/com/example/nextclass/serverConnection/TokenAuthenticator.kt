package com.example.nextclass.serverConnection

import android.content.Context
import android.util.Log
import com.example.nextclass.BuildConfig
import com.example.nextclass.Data.TokenData
import com.example.nextclass.repository.TokenRepository
import com.example.nextclass.utils.EXPIRED_ACCESS_TOKEN
import com.example.nextclass.utils.TokenManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

class TokenAuthenticator @Inject constructor(
    private val context: Context,

)
:Authenticator{

    override fun authenticate(route: Route?, response: Response): Request? {

        if(response.isSuccessful){
            // response.body?.string()를 한번쓰면 다시 읽을수없다 peekBody로 데이터를 저장하고 새로운 객체로 만듬
            val responseBody = response.peekBody(Long.MAX_VALUE).string()
            Log.d("TokenResponseBody2",responseBody)
            if (responseBody.contains(EXPIRED_ACCESS_TOKEN)) {



                Log.d("TokenResponseBody2",responseBody)
//                return if (tokenRefreshSuccess) {
//                    val newToken = runBlocking { TokenManager.getAccessToken(context) }
//
//                    // UnAuthorized 예외가 발생한 요청을 복제하여 다시 요청합니다.
//                    response.request.newBuilder().apply {
//                        removeHeader("Authorization")
//                        addHeader("Authorization", "Bearer $newToken")
//                    }.build()
//                } else {
//                    // RefreshToken도 만료되어 로그인이 다시 필요한 상황입니다.
//                    // ex. 로그인 화면으로 이동
//
//                    null
//                }
            }
        }
        return null
    }

}


