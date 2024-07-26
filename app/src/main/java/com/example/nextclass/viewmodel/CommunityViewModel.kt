package com.example.nextclass.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.CommunityCommentData
import com.example.nextclass.Data.CommunityPostData
import com.example.nextclass.repository.UserInfoRepository
import com.example.nextclass.utils.StringValue
import javax.inject.Inject

class CommunityViewModel @Inject constructor(

): ViewModel(){


    private val _communityData = mutableStateOf(CommunityPostData())
    val communityData: State<CommunityPostData> = _communityData

    private val _communityDataList = mutableStateOf(listOf<CommunityPostData>())
    val communityDataList: State<List<CommunityPostData>> = _communityDataList

    private val _selectCommunityData = mutableStateOf(CommunityPostData())
    val selectCommunityData: State<CommunityPostData> = _selectCommunityData

    private val _communityCommentDataList = mutableStateOf(listOf<CommunityCommentData>())
    val communityCommentDataList: State<List<CommunityCommentData>> = _communityCommentDataList

    private val _selectCommunityCommentData = mutableStateOf(CommunityCommentData())
    val selectCommunityCommentData: State<CommunityCommentData> = _selectCommunityCommentData

    private val _postSchoolTypeState = mutableStateOf(false)
    val postSchoolTypeState: State<Boolean> = _postSchoolTypeState

    private val _postSortTypeBottomSheetState = mutableStateOf(false)
    val postSortTypeBottomSheetState: State<Boolean> = _postSortTypeBottomSheetState

    private val _postErrorState = mutableStateOf(false)
    val postErrorState: State<Boolean> = _postErrorState

    private val _postErrorMessage= mutableStateOf<StringValue>(StringValue.Empty)
    val postErrorMessage: State<StringValue> = _postErrorMessage

    fun setSelectedCommunityData(communityPostData: CommunityPostData){
        _selectCommunityData.value=communityPostData
        Log.d("선택된 게시물", _selectCommunityData.value.toString())
    }

    fun setSelectCommentData(communityCommentData: CommunityCommentData){
        _selectCommunityCommentData.value=communityCommentData
    }

    fun modifyComment(singleCommentData: CommunityCommentData) {
        Log.d("수정하려는 댓글", singleCommentData.toString())
    }

    fun deleteComment(singleCommentData: CommunityCommentData) {
        Log.d("삭제하려는 댓글", singleCommentData.toString())
    }

    fun likeComment(singleCommentData: CommunityCommentData) {
        Log.d("좋아요를 누르려는 댓글", singleCommentData.toString())
    }

    fun deletePost() {
        _selectCommunityData.value
    }

    fun modifyPost() {
        _selectCommunityData.value
    }

    fun likePost() {
        _selectCommunityData.value
    }

    fun togglePostSchoolType(){
        _postSchoolTypeState.value=!_postSchoolTypeState.value
    }

    fun togglePostSortTypeBottomSheetState() {
        _postSortTypeBottomSheetState.value=!_postSortTypeBottomSheetState.value
    }

    fun insertPostData() {
        TODO("Not yet implemented")
    }

    fun modifyPostData() {
        TODO("Not yet implemented")
    }


}