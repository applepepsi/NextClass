package com.example.nextclass.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.CommunityData.CommunityCommentData
import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.Data.CommunityData.PostListData
import com.example.nextclass.Data.CommunityData.PostWriteData
import com.example.nextclass.R
import com.example.nextclass.repository.CommunityRepository
import com.example.nextclass.utils.SUCCESS_CODE
import com.example.nextclass.utils.StringValue
import javax.inject.Inject

class CommunityViewModel @Inject constructor(
    private val communityRepository: CommunityRepository
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

    private val _toggleMyPostTypeState = mutableStateOf(false)
    val toggleMyPostTypeState: State<Boolean> = _toggleMyPostTypeState

    private val _myPostFilter = mutableStateOf("내 게시물")
    val myPostFilter: State<String> = _myPostFilter

    private val _postWriteData = mutableStateOf(PostWriteData())
    val postWriteData: State<PostWriteData> = _postWriteData

    private val _loading=mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _postResultState=mutableStateOf(false)
    val postResultState: State<Boolean> = _postResultState

    private val _postListType=mutableStateOf(PostListData())
    val postListType: State<PostListData> = _postListType

    fun setSelectedCommunityData(communityPostData: CommunityPostData){
        _selectCommunityData.value=communityPostData
        Log.d("선택된 게시물", _selectCommunityData.value.toString())
    }

    fun updateSubject(subject: String){
        if(subject.length>=100){
            _postErrorMessage.value=StringValue.StringResource(R.string.SubjectLengthOver)
            _postErrorState.value=true
        }else{
            _postWriteData.value = _postWriteData.value.copy(subject=subject)
            _postErrorMessage.value=StringValue.Empty
            _postErrorState.value=false
        }
    }

    fun updateContent(content:String){
        _postWriteData.value = _postWriteData.value.copy(content=content)
    }

    fun togglePostSecretState(){
        val currentState = _postWriteData.value

        _postWriteData.value = currentState.copy(is_secret = !currentState.is_secret)
    }

    fun resetWritePostData(){
        _postWriteData.value=PostWriteData()
    }

    fun postWritePostData(){
        _loading.value=true
        communityRepository.postSave(_postWriteData.value){ postSaveResult->
            if(postSaveResult !=null) {
                if (postSaveResult.code == SUCCESS_CODE) {

                    _postResultState.value=true
                }else{

                }
            }
            _loading.value=false
        }
        _loading.value=false
    }

    fun postModifyPostData(){
        _loading.value=true
        communityRepository.postChange(_postWriteData.value){ postSaveResult->
            if(postSaveResult !=null) {
                if (postSaveResult.code == SUCCESS_CODE) {

                    _postResultState.value=true
                }else{

                }
            }
        }
        _loading.value=false
    }

    fun getPostList(sort: String, post_sequence: Int) {
        _loading.value=true

        communityRepository.getPostList(PostListData(post_sequence=post_sequence,sort=sort,)){ getPostListResult->
            if(getPostListResult !=null) {
                if (getPostListResult.code == SUCCESS_CODE) {

                    _communityDataList.value=getPostListResult.data!!
                }else{
                    //토스트 메세지
                }
            }

        }
        _loading.value=false
    }

    fun setSelectCommentData(communityCommentData: CommunityCommentData){
        _selectCommunityCommentData.value=communityCommentData
    }
    fun resetPostResultState(){
        _postResultState.value=false
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

    fun toggleMyPostTypeType(){

        _toggleMyPostTypeState.value=!_toggleMyPostTypeState.value
        Log.d("_toggleMyPostTypeState", _toggleMyPostTypeState.value.toString())
    }

    fun updateMyPostTypeDropDownText(text:String){
        _myPostFilter.value=text
    }

//    fun setPostType(type: String) {
//        _postList.value=_postList.value.copy(sort = type)
//    }

}