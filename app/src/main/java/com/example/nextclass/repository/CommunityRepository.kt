package com.example.nextclass.repository

import com.example.nextclass.Data.CommunityData.CommentListData
import com.example.nextclass.Data.CommunityData.CommentWriteData
import com.example.nextclass.Data.CommunityData.CommunityCommentData
import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.Data.CommunityData.PostAndCommentSequence
import com.example.nextclass.Data.CommunityData.PostListData
import com.example.nextclass.Data.CommunityData.PostWriteData
import com.example.nextclass.Data.CommunityData.SearchData
import com.example.nextclass.Data.ServerResponse

interface CommunityRepository {

    fun postSave(writePostData:PostWriteData,callback: (ServerResponse<Any>?) -> Unit)

    fun postChange(writePostData:PostWriteData,callback: (ServerResponse<Any>?) -> Unit)

    fun postDelete(post_sequence: String, callback: (ServerResponse<Any>?) -> Unit)

    fun getPostList(postListData: PostListData, callback: (ServerResponse<List<CommunityPostData>>?) -> Unit)

    fun getCommentList(commentListData: CommentListData, callback: (ServerResponse<List<CommunityCommentData>>?) -> Unit)

    fun communitySearch(searchData: SearchData, callback: (ServerResponse<List<CommunityPostData>>?) -> Unit)

    fun commentSave(writeCommentData:CommentWriteData,callback: (ServerResponse<Any>?) -> Unit)

    fun commentChange(callback: (ServerResponse<Any>?) -> Unit)

    fun commentDelete(sequence: PostAndCommentSequence,callback: (ServerResponse<Any>?) -> Unit)

    fun postDetail(post_sequence:String,callback: (ServerResponse<CommunityPostData>?) -> Unit)

    fun vote(vote:PostAndCommentSequence, callback: (ServerResponse<Any>?) -> Unit)



}