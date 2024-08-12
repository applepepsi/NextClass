package com.example.oneplusone.serverConnection


import com.example.nextclass.Data.AllScore
import com.example.nextclass.Data.ChangeEmail
import com.example.nextclass.Data.ChangePassword
import com.example.nextclass.Data.ChangeUserData
import com.example.nextclass.Data.ClassData
import com.example.nextclass.Data.ClassScore
import com.example.nextclass.Data.ClassUUid
import com.example.nextclass.Data.DuplicateCheckRequest
import com.example.nextclass.Data.FindIDOrPasswordData
import com.example.nextclass.Data.JoinRequest
import com.example.nextclass.Data.LoginRequest
import com.example.nextclass.Data.PostClassScoreList
import com.example.nextclass.Data.PostSemester
import com.example.nextclass.Data.PostUserData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData
import com.example.nextclass.Data.UserData
import com.example.nextclass.Data.VerifyCodeData
import com.example.nextclass.utils.CHANGE_EMAIL
import com.example.nextclass.utils.CHANGE_INFO
import com.example.nextclass.utils.CHANGE_PASSWORD
import com.example.nextclass.utils.DELETE_ID
import com.example.nextclass.utils.DUPLICATED_CHECK_ADDRESS
import com.example.nextclass.utils.FIND_ID
import com.example.nextclass.utils.FIND_PASSWORD
import com.example.nextclass.utils.GET_SCORE
import com.example.nextclass.utils.GET_TIME_TABLE
import com.example.nextclass.utils.GET_USER_INFO
import com.example.nextclass.utils.GET_VERIFY_CODE
import com.example.nextclass.utils.LOGIN_ADDRESS
import com.example.nextclass.utils.MAIL_CHECK
import com.example.nextclass.utils.POST_DELETE_TIMETABLE_DATA
import com.example.nextclass.utils.POST_MODIFY_TIMETABLE_DATA
import com.example.nextclass.utils.POST_TIMETABLE_DATA
import com.example.nextclass.utils.REGISTER_ADDRESS
import com.example.nextclass.utils.SCORE_UPDATE
import com.example.nextclass.utils.SEND_CHANGE_MAIL
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDateTime

interface API {


    //key value 형식으로 달라고 했음

    @POST(DUPLICATED_CHECK_ADDRESS)
    suspend fun idDuplicateCheck(@Body checkRequest: DuplicateCheckRequest): Response<ServerResponse<Any>>

    @POST(DUPLICATED_CHECK_ADDRESS)
    suspend fun emailDuplicateCheck(@Body checkRequest: DuplicateCheckRequest): Response<ServerResponse<Any>>


    @POST(REGISTER_ADDRESS)
    suspend fun postUserJoinInfo(@Body joinInfo: JoinRequest):Response<ServerResponse<Any>>

    @POST(LOGIN_ADDRESS)
    suspend fun postUserLoginForm(@Body loginInfo: LoginRequest):Response<ServerResponse<TokenData>>

    @POST(POST_TIMETABLE_DATA)
    suspend fun postTimeTableData(@Body classData: ClassData):Response<ServerResponse<Any>>

    @POST(POST_MODIFY_TIMETABLE_DATA)
    suspend fun postModifyTimeTableData(@Body classData: ClassData):Response<ServerResponse<Any>>

    //주소 미정
    @POST(POST_DELETE_TIMETABLE_DATA)
    suspend fun postDeleteTimeTableData(@Body uuid: ClassUUid):Response<ServerResponse<Any>>

    @POST(GET_USER_INFO)
    suspend fun getUserInfo():Response<ServerResponse<UserData>>

    @POST(CHANGE_PASSWORD)
    suspend fun postChangePasswordData(@Body changePassword: ChangePassword):Response<ServerResponse<Any>>

    @POST(CHANGE_INFO)
    suspend fun postChangeUserInfoData(@Body userData: PostUserData):Response<ServerResponse<Any>>

    @POST(SEND_CHANGE_MAIL)
    suspend fun postChangeEmailData(@Body changeEmailData: ChangeEmail):Response<ServerResponse<Any>>

    @POST(CHANGE_EMAIL)
    suspend fun postChangeEmailRequest(@Body changeEmailData: ChangeEmail):Response<ServerResponse<Any>>

    @POST(DELETE_ID)
    suspend fun deleteUser(@Body password: String):Response<ServerResponse<Any>>


    @POST(GET_TIME_TABLE)
    suspend fun getCurrentTimeTable(@Body semester: PostSemester):Response<ServerResponse<List<ClassData>>>

    @POST(GET_VERIFY_CODE)
    suspend fun getVerifyCode(@Body email: VerifyCodeData):Response<ServerResponse<Any>>

    @POST(MAIL_CHECK)
    suspend fun verifyCodeCheck(@Body mailCheckBody: VerifyCodeData):Response<ServerResponse<Any>>

    @POST(FIND_ID)
    suspend fun findId(@Body email: FindIDOrPasswordData):Response<ServerResponse<Any>>

    @POST(FIND_PASSWORD)
    suspend fun findPassword(@Body id: FindIDOrPasswordData):Response<ServerResponse<Any>>

    @POST(GET_SCORE)
    suspend fun getScore():Response<ServerResponse<AllScore>>

    @POST(SCORE_UPDATE)
    suspend fun scoreUpdate(@Body scoreList: PostClassScoreList):Response<ServerResponse<Any>>

    @POST("/test")
    suspend fun tokenTest():Response<ServerResponse<Any>?>

    @POST("/test")
    suspend fun refreshTest():Response<ServerResponse<Any>>
}