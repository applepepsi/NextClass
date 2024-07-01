package com.example.nextclass.serverConnection

import android.content.Context
import android.util.Log
import com.example.nextclass.utils.DUPLICATED_CHECK_ADDRESS
import com.example.nextclass.utils.LOGIN_ADDRESS
import com.example.nextclass.utils.REGISTER_ADDRESS
import com.example.nextclass.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val context: Context,
    private val excludedPaths: List<String> = listOf(
        DUPLICATED_CHECK_ADDRESS, REGISTER_ADDRESS, LOGIN_ADDRESS
    )
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestPath = originalRequest.url().encodedPath()

        if (excludedPaths.contains(requestPath)) {
            return chain.proceed(originalRequest)
        }

        // 토큰을 가져옴
        val accessToken = TokenManager.getAccessToken(context)
        if (accessToken != null) {
            Log.d("newToken",accessToken)
        }
        // 새로운 요청 빌더 생성
        val newRequest = originalRequest.newBuilder().apply {
            accessToken?.let {
                header("Authorization", "Bearer $it")
            }
        }.build()

        // 새로운 요청으로 체인진행
        return chain.proceed(newRequest)
    }
}