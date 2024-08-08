package com.example.nextclass.repository

import android.util.Log
import com.example.nextclass.Data.AllScore
import com.example.nextclass.Data.ClassData
import com.example.nextclass.Data.ClassUUid
import com.example.nextclass.Data.PostSemester
import com.example.nextclass.Data.ServerResponse
import com.example.oneplusone.serverConnection.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeTableRepositoryImpl  @Inject constructor(
    private val api: API
) :TimeTableRepository{
    override fun postTimeTableData(classData: ClassData,callback: (ServerResponse<Any>?) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                Log.d("classData", classData.toString())
                val response = api.postTimeTableData(classData)
                Log.d("responseCheck", response.toString())
                if (response.isSuccessful){
                    Log.d("response.body()", response.body().toString())
                    response.body()
                } else {
                    Log.d("id중복체크 실패","id중복체크 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("id중복체크 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }


    override fun postModifyTimeTableData(classData: ClassData,callback: (ServerResponse<Any>?) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                Log.d("수정하려는 클래스 데이터", classData.toString())
                val response = api.postModifyTimeTableData(classData)
                Log.d("responseCheck", response.toString())
                if (response.isSuccessful){
                    Log.d("response.body()", response.body().toString())
                    response.body()
                } else {
                    Log.d("시간표 수정 실패","수정 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("연결 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postDeleteTimeTableData(classUUid: ClassUUid, callback: (ServerResponse<Any>?) -> Unit) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                Log.d("classData", classUUid.toString())
                val response = api.postDeleteTimeTableData(classUUid)
                Log.d("responseCheck", response.toString())
                if (response.isSuccessful){
                    Log.d("response.body()", response.body().toString())
                    response.body()
                } else {
                    Log.d("시간표 삭제 실패","삭제실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("연결 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }


    override fun getCurrentTimeTableData(
        semester: String,
        callback: (ServerResponse<List<ClassData>>?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                Log.d("classData", semester.toString())
                val response = api.getCurrentTimeTable(PostSemester(semester))
                Log.d("responseCheck", response.toString())
                if (response.isSuccessful){
                    Log.d("수업 정보 받아오기 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("수업 정보 받아오기 실패","id중복체크 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("수업 정보 받아오기 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun getScore(callback: (ServerResponse<AllScore>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                val response = api.getScore()
                Log.d("responseCheck", response.toString())
                if (response.isSuccessful){
                    Log.d("성적 받아오기 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("성적 받아오기 실패","성적 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("성적 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }

    override fun postUpdateScoreData(allScore: AllScore, callback: (ServerResponse<Any>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {

                Log.d("allScore", allScore.toString())
                val response = api.scoreUpdate(allScore)
                Log.d("responseCheck", response.toString())
                if (response.isSuccessful){
                    Log.d("수업 정보 받아오기 성공", response.body().toString())
                    response.body()
                } else {
                    Log.d("수업 정보 받아오기 실패","id중복체크 실패")
                    null
                }
            } catch (e: Exception) {
                Log.d("수업 정보 받아오기 실패",e.toString())
                null
            }
            withContext(Dispatchers.Main) {

                callback(result)
            }
        }
    }
}