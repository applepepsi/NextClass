package com.example.nextclass.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.CommunityData.CommentListData
import com.example.nextclass.Data.CommunityData.CommentWriteData
import com.example.nextclass.Data.CommunityData.CommunityCommentData
import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.Data.CommunityData.PostAndCommentSequence
import com.example.nextclass.Data.CommunityData.PostListData
import com.example.nextclass.Data.CommunityData.PostWriteData
import com.example.nextclass.R
import com.example.nextclass.repository.CommunityRepository
import com.example.nextclass.utils.SUCCESS_CODE
import com.example.nextclass.utils.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
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

    private val _getPostDetailResultState=mutableStateOf(false)
    val getPostDetailResultState: State<Boolean> = _getPostDetailResultState

    private val _postDeleteResultState=mutableStateOf(false)
    val postDeleteResultState: State<Boolean> = _postDeleteResultState

    private val _getPostLoadingState=mutableStateOf(false)
    val getPostLoadingState: State<Boolean> = _getPostLoadingState

    private val _commentWriteData=mutableStateOf(CommentWriteData())
    val commentWriteData: State<CommentWriteData> = _commentWriteData

    private val _morePostLoadState=mutableStateOf(false)
    val morePostLoadState: State<Boolean> = _morePostLoadState

    private val _moreCommentLoadState=mutableStateOf(false)
    val moreCommentLoadState: State<Boolean> = _moreCommentLoadState

    private val _searchBarState=mutableStateOf(false)
    val searchBarState: State<Boolean> = _searchBarState

    private val _postLoading=mutableStateOf(false)
    val postLoading: State<Boolean> = _postLoading


    private val _commentLoading=mutableStateOf(false)
    val commentLoading: State<Boolean> = _commentLoading

    private val _currentRoute=mutableStateOf("")
    val currentRoute: State<String> = _currentRoute

    fun setSelectedCommunityData(selectPostSequence: String,postReLoad:Boolean=false){


        _loading.value=true
        communityRepository.postDetail(selectPostSequence){ postDetailResult->
            if(postDetailResult !=null) {
                if (postDetailResult.code == SUCCESS_CODE) {
                    _selectCommunityData.value= postDetailResult.data!!
                    Log.d("선택된 게시물", _selectCommunityData.value.toString())
                    if (!postReLoad) {
                        togglePostDetailState()
                    }
                }else{

                }
            }
            _loading.value=false
        }
        _loading.value=false
    }


    fun setPostModifyData(){
        val anonymityState= _selectCommunityData.value.author=="익명"

        _postWriteData.value=_postWriteData.value.copy(
            post_sequence = _selectCommunityData.value.post_sequence,
            subject = _selectCommunityData.value.subject,
            content = _selectCommunityData.value.content,
            is_secret = anonymityState)
    }

    fun togglePostDetailState(){
        _getPostDetailResultState.value=!_getPostDetailResultState.value
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

                    togglePostResultState()
                }else{

                }
            }
            _loading.value=false
        }
        _loading.value=false
    }

    fun updatePostDetail(){

        _postWriteData.value
//        _selectCommunityData.value=_selectCommunityData.value.copy(
//            post_sequence = _selectCommunityData.value.post_sequence,
//            subject = _selectCommunityData.value.subject,
//            content = _selectCommunityData.value.content,
//
//        )
    }

    fun toggleSearchBarState(){
        _searchBarState.value=!_searchBarState.value
    }

    fun togglePostResultState(){
        _postResultState.value=!_postResultState.value
    }

    fun postModifyPostData(){
        _loading.value=true
        communityRepository.postChange(_postWriteData.value){ postSaveResult->
            if(postSaveResult !=null) {
                if (postSaveResult.code == SUCCESS_CODE) {
                    Log.d("수정 성공","")

                    //todo 게시물을 수정한 후 다시 게시물을 로드해야함 테스트해봐야함
                    setSelectedCommunityData(_postWriteData.value.post_sequence!!, postReLoad = true)
                    togglePostResultState()

                    //수정에 성공했다면 댓글도 업데이트함
                    resetCommentList()
                    getCommentList(
                        post_sequence = _selectCommunityData.value.post_sequence,
                        comment_sequence = null
                    )
                }else{

                }
            }
        }
        _loading.value=false
    }

    fun getPostList(sort: String, post_sequence: Int?,size:Int=10) {
        Log.d("로딩", _loading.value.toString())
        if(_postLoading.value) return
        _postLoading.value=true

        val postListData=PostListData(post_sequence=post_sequence.toString(),sort=sort,size=size)
        Log.d("postListData", postListData.toString())

        communityRepository.getPostList(postListData){ getPostListResult->
            if(getPostListResult !=null) {
                if (getPostListResult.code == SUCCESS_CODE) {
                    Log.d("새로운 게시물 로드됨", getPostListResult.data.toString())
                    if(getPostListResult.data!!.isEmpty()){
                        toggleMorePostLoadState()
                    }else{
                        _communityDataList.value += getPostListResult.data
                    }
                }else{
                    //토스트 메세지
                }
            }else{
                //게시물이 더 없으면 게시물을 가져오는것을 멈춤
                toggleMorePostLoadState()
            }
            _postLoading.value = false
        }
    }

    fun toggleMorePostLoadState(){
        _morePostLoadState.value=!_morePostLoadState.value
    }



    fun loadMorePostsCheck(lastVisibleItemIndex: Int,sortType:String) {

        if (_communityDataList.value.isEmpty()) {
            return
        }

        //개선 방안 찾아보기로
        if (lastVisibleItemIndex >= _communityDataList.value.size - 1) {
            val lastPostSequence = _communityDataList.value.minByOrNull { it.post_sequence.toInt() }?.post_sequence?.toInt()

            getPostList(sort = sortType, post_sequence = lastPostSequence)

        }
    }

    fun resetPostList(){
        _communityDataList.value= emptyList()
    }

    fun setSelectCommentData(communityCommentData: CommunityCommentData){
        _selectCommunityCommentData.value=communityCommentData
    }
    fun resetPostResultState(){
        _postResultState.value=false
    }

    fun modifyComment(singleCommentData: CommunityCommentData) {
        Log.d("수정하려는 댓글", singleCommentData.toString())
        _postWriteData.value
    }

    fun deleteComment(singleCommentData: CommunityCommentData) {
        Log.d("삭제하려는 댓글", singleCommentData.toString())

        val sequence=PostAndCommentSequence(post_sequence = _selectCommunityData.value.post_sequence,comment_sequence = singleCommentData.comment_sequence)

        communityRepository.commentDelete(sequence){ deleteCommentResult->
            if(deleteCommentResult !=null) {
                if (deleteCommentResult.code == SUCCESS_CODE) {

                    resetCommentList()
                    getCommentList(
                        post_sequence = _selectCommunityData.value.post_sequence,
                        comment_sequence = null
                    )
                    Log.d("댓글 삭제 성공", _communityDataList.value.toString())

                }else{
                    //토스트 메세지
                }
            }

        }
    }

    fun likeComment(singleCommentData: CommunityCommentData) {
        Log.d("좋아요를 누르려는 댓글", singleCommentData.toString())

        val postSequence=PostAndCommentSequence(post_sequence = _selectCommunityData.value.post_sequence, comment_sequence = singleCommentData.comment_sequence)
        communityRepository.vote(postSequence){ postVoteResult->
            if(postVoteResult !=null) {

                if (postVoteResult.code == SUCCESS_CODE) {
                    val currentVoteState = singleCommentData.is_vote
                    val currentVoteCount = singleCommentData.vote_count

                    // 새로운 투표 상태와 카운트 계산
                    val newVoteState = !currentVoteState
                    val newVoteCount = if (newVoteState) {
                        currentVoteCount + 1
                    } else {
                        currentVoteCount - 1
                    }

                    // 댓글 리스트 업데이트
                    _communityCommentDataList.value = _communityCommentDataList.value.map { singleComment ->
                        if (singleComment.comment_sequence == singleCommentData.comment_sequence) {
                            singleComment.copy(
                                is_vote = newVoteState,
                                vote_count = newVoteCount
                            )
                        } else {
                            singleComment
                        }
                    }
                    Log.d("추천됨", postVoteResult.data.toString())

                }else{
                    //토스트 메세지
                }
            }else{

            }
            _loading.value = false
        }
    }

    fun deletePost() {
        _selectCommunityData.value

        communityRepository.postDelete(_selectCommunityData.value.post_sequence){ deletePostResult->
            if(deletePostResult !=null) {
                if (deletePostResult.code == SUCCESS_CODE) {
                    togglePostDeleteState()
                    Log.d("삭제 성공", _communityDataList.value.toString())
                }else{
                    //토스트 메세지
                }
            }

        }
    }

    fun togglePostDeleteState(){
        _postDeleteResultState.value=!postDeleteResultState.value
    }

    fun modifyPost() {
        _selectCommunityData.value
    }

    fun likePost() {
        val postSequence=PostAndCommentSequence(post_sequence = _selectCommunityData.value.post_sequence, comment_sequence = null)
        communityRepository.vote(postSequence){ postVoteResult->
            if(postVoteResult !=null) {
                if (postVoteResult.code == SUCCESS_CODE) {

                    Log.d("추천됨", postVoteResult.data.toString())
                    val currentVoteState=_selectCommunityData.value.is_vote
                    val currentVoteCount=_selectCommunityData.value.vote_count

                    val newVoteCount = if (!currentVoteState) {
                        currentVoteCount + 1
                    } else {
                        currentVoteCount - 1
                    }
                    _selectCommunityData.value=_selectCommunityData.value.copy(is_vote = !currentVoteState, vote_count = newVoteCount)
                }else{
                    //토스트 메세지
                }
            }else{

            }
            _loading.value = false
        }
    }

    fun togglePostSchoolType(){
        _postSchoolTypeState.value=!_postSchoolTypeState.value
    }

    fun togglePostSortTypeBottomSheetState() {
        _postSortTypeBottomSheetState.value=!_postSortTypeBottomSheetState.value
    }


    fun toggleMyPostType(){

        _toggleMyPostTypeState.value=!_toggleMyPostTypeState.value
        Log.d("_toggleMyPostTypeState", _toggleMyPostTypeState.value.toString())
    }

    fun updateMyPostTypeDropDownText(text:String){
        _myPostFilter.value=text
    }

    fun updateCommentContent(content:String){
        _commentWriteData.value=_commentWriteData.value.copy(content = content)
    }

    fun toggleCommentSecretState(){
        val currentState=_commentWriteData.value.is_secret
        _commentWriteData.value=_commentWriteData.value.copy(is_secret = !currentState)
    }

    fun resetCommentList(){
        _communityCommentDataList.value= emptyList()
    }

    fun getCommentList(post_sequence: String, comment_sequence: Int?){
        if(_commentLoading.value) return
        _commentLoading.value=true

        val commentListData=CommentListData(post_sequence=post_sequence,comment_sequence=comment_sequence)

        communityRepository.getCommentList(commentListData){ getPostListResult->
            if(getPostListResult !=null) {
                if (getPostListResult.code == SUCCESS_CODE) {
                    if(getPostListResult.data!!.isEmpty()){
                        toggleMoreCommentLoadState()
                    }else{
                        _communityCommentDataList.value += getPostListResult.data
                    }
                    Log.d("새로운 댓글 로드됨", getPostListResult.data.toString())


                }else{
                    //토스트 메세지
                }
            }else{
                toggleMoreCommentLoadState()
            }
            _commentLoading.value = false
        }
    }

    fun toggleMoreCommentLoadState(){
        _moreCommentLoadState.value=!_moreCommentLoadState.value
    }

    fun loadMoreCommentCheck(lastVisibleItemIndex: Int) {
        //개선 방안 찾아보기로
        if (lastVisibleItemIndex >= _communityCommentDataList.value.size - 1) {
            val lastCommentSequence = _communityCommentDataList.value.minByOrNull { it.comment_sequence.toInt() }?.comment_sequence?.toInt()
            getCommentList(
                post_sequence = _selectCommunityData.value.post_sequence,
                comment_sequence = lastCommentSequence)
        }
    }

    fun postCommentData(post_sequence:String){

        _commentWriteData.value=_commentWriteData.value.copy(post_sequence=post_sequence)

        communityRepository.commentSave(_commentWriteData.value){ saveCommentResult->
            if(saveCommentResult !=null) {
                if (saveCommentResult.code == SUCCESS_CODE) {

                    Log.d("댓글 작성 성공", _communityDataList.value.toString())

                    postDetailReload()
                }else{
                    //토스트 메세지
                }
            }

        }
    }



    fun resetWriteCommentData(){
        _commentWriteData.value= CommentWriteData()
    }

    fun postDetailReload(){
        resetWriteCommentData()
        resetCommentList()
        getCommentList(
            post_sequence = selectCommunityData.value.post_sequence,
            comment_sequence = null
        )
        setSelectedCommunityData(_selectCommunityData.value.post_sequence,postReLoad = true)

    }

    fun setCurrentRoute(screenRoute: String) {
        _currentRoute.value=screenRoute
    }

//    fun setPostType(type: String) {
//        _postList.value=_postList.value.copy(sort = type)
//    }

}