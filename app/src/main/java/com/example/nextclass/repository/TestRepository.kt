package com.example.nextclass.repository

import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestRepository : UserInfoRepository {
    override fun idDuplicateCheck(id: String,callback: (ServerResponse?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val response = RetrofitBuilder.api.idDuplicateCheck(id)
                if (response.isSuccessful){
                    response.body()
                } else null
            } catch (e: Exception) {
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
                } else null
            } catch (e: Exception) {
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }
}