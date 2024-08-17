package com.example.nextclass.repository.testRepo

import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.Data.CommunityData.PostListData
import com.example.nextclass.Data.CommunityData.PostWriteData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.repository.CommunityRepository
import com.example.oneplusone.serverConnection.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommunityTestRepository: CommunityRepository {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: API = retrofit.create(API::class.java)


    override fun postSave(writePostData: PostWriteData, callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun postChange(
        writePostData: PostWriteData,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun postDelete(callback1: String, callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getPostList(
        postListData: PostListData,
        callback: (ServerResponse<List<CommunityPostData>>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun commentSave(callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun commentChange(callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun postDetail(
        post_sequence: String,
        callback: (ServerResponse<CommunityPostData>?) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}