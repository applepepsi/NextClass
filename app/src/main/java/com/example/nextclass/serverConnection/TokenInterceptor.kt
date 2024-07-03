package com.example.nextclass.serverConnection

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.nextclass.Data.TokenData
import com.example.nextclass.utils.DUPLICATED_CHECK_ADDRESS
import com.example.nextclass.utils.EXPIRED_ACCESS_TOKEN
import com.example.nextclass.utils.EXPIRED_REFRESH_TOKEN
import com.example.nextclass.utils.LOGIN_ADDRESS
import com.example.nextclass.utils.REGISTER_ADDRESS
import com.example.nextclass.utils.TokenManager
import com.example.nextclass.viewmodel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

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

        var response=chain.proceed(newRequest)

        if (isTokenExpired(response)) {
            // 토큰 갱신 로직을 실행
            response.close()

            //새로운 엑세스 키를 가져옴
            val newAccessToken = refreshAccessToken(chain, originalRequest)
            //새로운 엑세스 키를 받아왔다면
            if (newAccessToken != null) {
                // 처음 시도했던 통신을 이어가기 위해 새로운 Access Token을 사용하여 요청을 다시 보냄
                val lastRequest = originalRequest.newBuilder().apply {
                    header("Authorization", "Bearer $newAccessToken")
                }.build()
                response = chain.proceed(lastRequest)
            }else{
                //새로운 엑세스 키를 받아오지 못했다면

                // 임시로 리프레시요청을 보내보기로함
                val newAccessTokenRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTY1N2VjNy0zYzA3LTRlN2ItOTU0NC0zMDA4M2M2MjgxYWM6VVNFUiIsImlzcyI6IkRhZUhhbiIsImlhdCI6MTcxOTg0MjYyMSwiZXhwIjoxNzE5ODUzNDIxfQ.UUl12nnhbMUjqgC5MQc3axo3tLvTppkAmVD-vBEEYxPg7RFJ6cf3wlemG7Y7AF6X15HkTUdwafMUVmO7Ba4nXQ")
                    .header("refresh-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTY1N2VjNy0zYzA3LTRlN2ItOTU0NC0zMDA4M2M2MjgxYWM6VVNFUiIsImlzcyI6IkRhZUhhbiIsImlhdCI6MTcxOTY3NzMyMiwiZXhwIjoxNzEwODg2OTIyfQ.9tMOXIy1rkMcSGcjDtftshSFT4L6Kf9bJnjFTfdCFJTlGw7iqBriv2bWdwURz-2qqXfM_iOWGlm4MJZH1KN5pg")
                    .build()

                return chain.proceed(newAccessTokenRequest)
            }
        }

        Log.d("newRequest", newRequest.toString())

//        return response
        return response
    }
    private fun isTokenExpired(response: Response): Boolean {
        //서버의 응답에서 EXPIRED_ACCESS_TOKEN코드가 온다면 토큰 재발급 시작
        val responseBody = response.peekBody(Long.MAX_VALUE).string()
        Log.d("responseBody",responseBody)
        return responseBody.contains(EXPIRED_ACCESS_TOKEN)
    }

    private fun isRefreshTokenExpired(response: Response): Boolean {

        val responseBody = response.peekBody(Long.MAX_VALUE).string()
        Log.d("responseBody",responseBody)
        return responseBody.contains(EXPIRED_REFRESH_TOKEN)
    }

    private fun refreshAccessToken(chain: Interceptor.Chain, originalRequest: Request): String? {
        return runBlocking {
            try {
                // 리프레시 토큰 가져옴
                val refreshToken = TokenManager.getRefreshToken(context)

                // 토큰 갱신 요청 생성
                val newAccessTokenRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTY1N2VjNy0zYzA3LTRlN2ItOTU0NC0zMDA4M2M2MjgxYWM6VVNFUiIsImlzcyI6IkRhZUhhbiIsImlhdCI6MTcxOTg0MjYyMSwiZXhwIjoxNzE5ODUzNDIxfQ.UUl12nnhbMUjqgC5MQc3axo3tLvTppkAmVD-vBEEYxPg7RFJ6cf3wlemG7Y7AF6X15HkTUdwafMUVmO7Ba4nXQ")
                    .header("refresh-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxOTY1N2VjNy0zYzA3LTRlN2ItOTU0NC0zMDA4M2M2MjgxYWM6VVNFUiIsImlzcyI6IkRhZUhhbiIsImlhdCI6MTcxOTY3NzMyMiwiZXhwIjoxNzEwODg2OTIyfQ.9tMOXIy1rkMcSGcjDtftshSFT4L6Kf9bJnjFTfdCFJTlGw7iqBriv2bWdwURz-2qqXfM_iOWGlm4MJZH1KN5pg")
                    .build()

                // 토큰 갱신 요청
                val refreshTokenResponse = chain.proceed(newAccessTokenRequest)

                if(isRefreshTokenExpired(refreshTokenResponse)){
                    //만약 리프레쉬 토큰도 만료라면 로그인화면으로 이동
                    Log.d("리프레시 만료", EXPIRED_REFRESH_TOKEN)
                    return@runBlocking null
                }else{
                    if (refreshTokenResponse.isSuccessful) {
                        // 새로운 엑세스키 추출
                        val responseBody = refreshTokenResponse.peekBody(Long.MAX_VALUE).string()

                        val jsonObject = JSONObject(responseBody)
                        Log.e("jsonObject", jsonObject.toString())
                        val newAccessToken = jsonObject.getString("accessToken")

                        // 새로운 Access Token 저장
                        TokenManager.saveToken(context, TokenData(refreshToken!!, newAccessToken))

                        return@runBlocking newAccessToken

                    }
                }
            } catch (e: Exception) {
                Log.e("TokenInterceptor", "Failed to refresh token", e)
            }
            return@runBlocking null
        }
    }
}

