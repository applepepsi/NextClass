package com.example.nextclass.module

import android.content.Context
import com.example.nextclass.BuildConfig
import com.example.nextclass.repository.ScheduleRepository
import com.example.nextclass.repository.ScheduleRepositoryImpl
import com.example.nextclass.repository.TokenRepository
import com.example.nextclass.repository.TokenRepositoryImpl
import com.example.nextclass.serverConnection.TokenAuthenticator
import com.example.nextclass.serverConnection.TokenInterceptor
import com.example.oneplusone.serverConnection.API
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenInterceptor: TokenInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .authenticator(tokenAuthenticator)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_ADDRESS)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideTokenAuthenticator(@ApplicationContext context: Context): TokenAuthenticator {
        return TokenAuthenticator(context)
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(@ApplicationContext context: Context): TokenInterceptor {
        return TokenInterceptor(context)
    }

//    @Provides
//    @Singleton
//    fun provideTokenAuthenticator(
//        @ApplicationContext context: Context,
//        tokenRepository: TokenRepository
//    ): TokenAuthenticator {
//        return TokenAuthenticator(context, tokenRepository)
//    }
}