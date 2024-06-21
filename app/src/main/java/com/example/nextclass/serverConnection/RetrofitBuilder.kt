package com.example.oneplusone.serverConnection

import com.example.nextclass.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    val serverAddress= BuildConfig.SERVER_ADDRESS

    private val gson : Gson = GsonBuilder()
        .setLenient()
        .create()

    var api: API = Retrofit.Builder()
        .baseUrl(serverAddress)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(API::class.java)


}