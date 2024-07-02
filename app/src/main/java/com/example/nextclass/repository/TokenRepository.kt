package com.example.nextclass.repository

import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData

interface TokenRepository {

    fun getNewAccessToken(tokenData: TokenData,callback: (ServerResponse?) -> Unit)
}