package com.example.nextclass.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.CommunityPostData
import com.example.nextclass.repository.UserInfoRepository
import javax.inject.Inject

class CommunityViewModel @Inject constructor(

): ViewModel(){

    private val _communityData = mutableStateOf(CommunityPostData())
    val communityData: State<CommunityPostData> = _communityData

    private val _communityDataList = mutableStateOf(listOf<CommunityPostData>())
    val communityDataList: State<List<CommunityPostData>> = _communityDataList



}