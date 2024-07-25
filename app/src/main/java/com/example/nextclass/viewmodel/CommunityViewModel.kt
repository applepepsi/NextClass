package com.example.nextclass.viewmodel

import android.util.Log
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

    private val _selectCommunityData = mutableStateOf(CommunityPostData())
    val selectCommunityData: State<CommunityPostData> = _selectCommunityData

    fun setSelectedCommunityData(communityPostData: CommunityPostData){
        _selectCommunityData.value=communityPostData
        Log.d("선택된 게시물", _selectCommunityData.value.toString())
    }




}