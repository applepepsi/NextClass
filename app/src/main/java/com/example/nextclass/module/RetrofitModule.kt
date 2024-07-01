package com.example.nextclass.module

import android.content.Context
import com.example.nextclass.BuildConfig
import com.example.nextclass.serverConnection.TokenInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_ADDRESS)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TokenInterceptor(context))
                    .build()
            )
            .addConverterFactory(
                GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            ))
            .build()
    }
}