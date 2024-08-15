package com.example.nextclass.module


import com.example.nextclass.repository.CommunityRepository
import com.example.nextclass.repository.CommunityRepositoryImpl
import com.example.nextclass.repository.ScheduleRepository
import com.example.nextclass.repository.ScheduleRepositoryImpl
import com.example.nextclass.repository.TimeTableRepository
import com.example.nextclass.repository.TimeTableRepositoryImpl
import com.example.nextclass.repository.UserInfoRepository
import com.example.nextclass.repository.UserInfoRepositoryImpl
import com.example.oneplusone.serverConnection.API

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
    @Singleton
    fun provideUserInfoRepository(api: API): UserInfoRepository {
        return UserInfoRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideScheduleRepository(api: API): ScheduleRepository {
        return ScheduleRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTimeTableRepository(api: API): TimeTableRepository {
        return TimeTableRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCommunityRepository(api: API): CommunityRepository {
        return CommunityRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }

}