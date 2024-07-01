package com.example.nextclass.module


import com.example.nextclass.repository.ScheduleRepository
import com.example.nextclass.repository.ScheduleRepositoryImpl
import com.example.nextclass.repository.UserInfoRepository
import com.example.nextclass.repository.UserInfoRepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUserInfoRepository(retrofit: Retrofit): UserInfoRepository = UserInfoRepositoryImpl(retrofit)

    @Provides
    fun provideScheduleRepository(retrofit: Retrofit): ScheduleRepository = ScheduleRepositoryImpl(retrofit)

}