package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.Data.CommunityData.PostListData
import com.example.nextclass.Data.CommunityData.PostWriteData
import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommunityRepositoryImpl @Inject constructor(
    private val api: API
) : CommunityRepository {

    override fun postSave(writePostData: PostWriteData, callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.postSave(writePostData)
            val result = try {

                Log.d("게시물 전송 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("게시물 전송 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("게시물 전송 실패","게시물 전송 실패")
                    null
                }
                response.body()
            } catch (e: Exception) {
                Log.d("게시물 전송 실패", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    override fun postChange(
        writePostData: PostWriteData,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.postChange(writePostData)
            val result = try {

                Log.d("게시물 수정 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("게시물 수정 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("게시물 수정 실패","게시물 수정 실패")
                    null
                }

            } catch (e: Exception) {
                Log.d("게시물 수정 실패", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    override fun postDelete(callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getPostList(postListData: PostListData,callback: (ServerResponse<List<CommunityPostData>>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getPostList(postListData)
            val result = try {

                Log.d("게시물 가져오기 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("게시물 가져오기 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("게시물 가져오기 실패","게시물 가져오기 실패")
                    null
                }

            } catch (e: Exception) {
                Log.d("게시물 가져오기 실패", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    override fun commentSave(callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun commentChange(callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun postDetail(post_sequence: String, callback: (ServerResponse<CommunityPostData>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.postDetail(post_sequence)
            val result = try {

                Log.d("게시물 세부 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("게시물 세부 가져오기 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("게시물 세부 가져오기 실패","게시물 세부 가져오기 실패")
                    null
                }

            } catch (e: Exception) {
                Log.d("게시물 세부가져오기 실패", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }


}
