package com.example.nextclass.serverConnection

import android.content.Context
import android.util.Log
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData
import com.example.nextclass.utils.DUPLICATED_CHECK_ADDRESS
import com.example.nextclass.utils.EXPIRED_ACCESS_TOKEN
import com.example.nextclass.utils.LOGIN_ADDRESS
import com.example.nextclass.utils.REGISTER_ADDRESS
import com.example.nextclass.utils.TokenManager
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

class TokenInterceptor(
    private val context: Context,
    private val excludedPaths: List<String> = listOf(
        DUPLICATED_CHECK_ADDRESS, REGISTER_ADDRESS, LOGIN_ADDRESS
    )
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestPath = originalRequest.url.encodedPath

        if (excludedPaths.contains(requestPath)) {
            return chain.proceed(originalRequest)
        }


        // 토큰을 가져옴
        val accessToken = TokenManager.getAccessToken(context)


        if (accessToken != null) {
            Log.d("newToken",accessToken)
        }
        // 새로운 요청 빌더 생성
//        val newRequest = originalRequest.newBuilder().apply {
//            accessToken?.let {
//                header("Authorization", "Bearer $it")
//            }
//        }.build()

        val newRequest = originalRequest.newBuilder().apply {
            accessToken?.let {
                header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTY1N2VjNy0zYzA3LTRlN2ItOTU0NC0zMDA4M2M2MjgxYWM6VVNFUiIsImlzcyI6IkRhZUhhbiIsImlhdCI6MTcxOTg0MjYyMSwiZXhwIjoxNzE5ODUzNDIxfQ.UUl12nnhbMUjqgC5MQc3axo3tLvTppkAmVD-vBEEYxPg7RFJ6cf3wlemG7Y7AF6X15HkTUdwafMUVmO7Ba4nXQ")
            }
        }.build()

        val response=chain.proceed(newRequest)

        if (isTokenExpired(response)) {
            // 토큰 갱신 로직을 실행
            response.close()
            //토큰 갱신을 위한 리프레시키를 가져옴
            val refreshToken = runBlocking {
                Log.d("newToken2",accessToken!!)
                TokenManager.getRefreshToken(context)
            }
            //서버로 토큰 갱신을 위해 엑세스키와 리프레쉬 키합쳐서 헤더 생성
            val newAccessTokenRequest=originalRequest.newBuilder()
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTY1N2VjNy0zYzA3LTRlN2ItOTU0NC0zMDA4M2M2MjgxYWM6VVNFUiIsImlzcyI6IkRhZUhhbiIsImlhdCI6MTcxOTg0MjYyMSwiZXhwIjoxNzE5ODUzNDIxfQ.UUl12nnhbMUjqgC5MQc3axo3tLvTppkAmVD-vBEEYxPg7RFJ6cf3wlemG7Y7AF6X15HkTUdwafMUVmO7Ba4nXQ")
                .header("refresh-token",refreshToken!!)
                .build()

            //리프레쉬 키를 전송
            val refreshTokenResponse= chain.proceed(newAccessTokenRequest)

            //서버로부터 새로운 엑세스키를 받아옴
            val responseBody=refreshTokenResponse.body?.string()
            val jsonObject = responseBody?.let { JSONObject(it) }
            //새로 받은 엑세스 토큰을 저장
            if (jsonObject != null) {
                TokenManager.saveToken(context,TokenData(refreshToken,jsonObject.getString("accessToken")))
            }
            //새로 받은 엑세스키를 가져옴
            val newAccessToken=TokenManager.getAccessToken(context)

            //처음 시도했던 통신을 이어가기 위해 엑세스토큰 헤더 생성
            val lastRequest=originalRequest.newBuilder().apply {
                newAccessToken?.let{
                    header("Authorization", "Bearer $it")
                }
            }.build()
            //종료
            return chain.proceed(lastRequest)

        }

        Log.d("newRequest", newRequest.toString())

        return response
    }
    private fun isTokenExpired(response: Response): Boolean {
        val responseBody = response.peekBody(Long.MAX_VALUE).string()
        return responseBody.contains(EXPIRED_ACCESS_TOKEN)
    }



}

