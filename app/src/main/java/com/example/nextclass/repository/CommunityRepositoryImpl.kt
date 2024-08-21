package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.CommunityData.CommentListData
import com.example.nextclass.Data.CommunityData.CommentWriteData
import com.example.nextclass.Data.CommunityData.CommunityCommentData
import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.Data.CommunityData.PostAndCommentSequence
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

    override fun postDelete(post_sequence: String, callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.postDelete(post_sequence)
            val result = try {

                Log.d("게시물 삭제 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("게시물 삭제 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("게시물 삭제 실패","게시물 삭제 실패")
                    null
                }

            } catch (e: Exception) {
                Log.d("게시물 삭제 실패", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }


    override fun getPostList(
        postListData: PostListData,
        callback: (ServerResponse<List<CommunityPostData>>?) -> Unit
    ) {
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

    override fun getCommentList(
        commentListData: CommentListData,
        callback: (ServerResponse<List<CommunityCommentData>>?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getCommentList(commentListData)
            val result = try {

                Log.d("댓글 가져오기 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("댓글 가져오기 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("댓글 가져오기 실패","댓글 가져오기 실패")
                    null
                }

            } catch (e: Exception) {
                Log.d("댓글 가져오기 실패", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    override fun commentSave(writeCommentData: CommentWriteData, callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.commentSave(writeCommentData)
            val result = try {

                Log.d("댓글 작성 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("댓글 작성 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("댓글 작성 실패","댓글 작성 실패")
                    null
                }

            } catch (e: Exception) {
                Log.d("댓글 작성 실패", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    override fun commentChange(callback: (ServerResponse<Any>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun commentDelete(sequence: PostAndCommentSequence, callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.commentDelete(sequence)
            val result = try {
                Log.d("commentSequence", sequence.toString())
                Log.d("댓글 삭제 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("댓글 삭제 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("댓글 삭제 실패","댓글 삭제 실패")
                    null
                }

            } catch (e: Exception) {
                Log.d("댓글 삭제 실패", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
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

    override fun vote(
        vote: PostAndCommentSequence,
        callback: (ServerResponse<Any>?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.vote(vote)
            val result = try {
                Log.d("vote", vote.toString())
                Log.d("추천 결과", response.toString())
                if (response.isSuccessful){
                    Log.d("추천 결과 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("추천 결과 실패","추천 결과 실패")
                    null
                }

            } catch (e: Exception) {
                Log.d("추천 결과 실패", e.toString())
                response.body()
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }


}
