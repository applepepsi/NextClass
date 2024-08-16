package com.example.nextclass.repository

import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.Data.CommunityData.PostListData
import com.example.nextclass.Data.CommunityData.PostWriteData
import com.example.nextclass.Data.ServerResponse

interface CommunityRepository {

    fun postSave(writePostData:PostWriteData,callback: (ServerResponse<Any>?) -> Unit)

    fun postChange(writePostData:PostWriteData,callback: (ServerResponse<Any>?) -> Unit)

    fun postDelete(callback: (ServerResponse<Any>?) -> Unit)

    fun getPostList(postListData: PostListData, callback: (ServerResponse<List<CommunityPostData>>?) -> Unit)

    fun commentSave(callback: (ServerResponse<Any>?) -> Unit)

    fun commentChange(callback: (ServerResponse<Any>?) -> Unit)

    fun postDetail(post_sequence:String,callback: (ServerResponse<CommunityPostData>?) -> Unit)

}