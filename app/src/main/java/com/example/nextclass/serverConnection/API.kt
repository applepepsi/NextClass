package com.example.oneplusone.serverConnection


import com.example.nextclass.Data.CommunityData.CommentListData
import com.example.nextclass.Data.CommunityData.CommentWriteData
import com.example.nextclass.Data.CommunityData.CommunityCommentData
import com.example.nextclass.Data.CommunityData.CommunityPostData
import com.example.nextclass.Data.CommunityData.PostAndCommentSequence
import com.example.nextclass.Data.CommunityData.PostListData
import com.example.nextclass.Data.CommunityData.PostWriteData
import com.example.nextclass.Data.ScheduleData.ScheduleData
import com.example.nextclass.Data.TimeTableData.AllScore
import com.example.nextclass.Data.UserInfoData.ChangeEmail
import com.example.nextclass.Data.UserInfoData.ChangePassword
import com.example.nextclass.Data.TimeTableData.ClassData
import com.example.nextclass.Data.TimeTableData.ClassUUid
import com.example.nextclass.Data.SignupOrLoginData.DuplicateCheckRequest
import com.example.nextclass.Data.SignupOrLoginData.FindIDOrPasswordData
import com.example.nextclass.Data.SignupOrLoginData.JoinRequest
import com.example.nextclass.Data.SignupOrLoginData.LoginRequest
import com.example.nextclass.Data.TimeTableData.PostClassScoreList
import com.example.nextclass.Data.TimeTableData.PostSemester
import com.example.nextclass.Data.UserInfoData.PostUserData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TokenData
import com.example.nextclass.Data.UserData
import com.example.nextclass.Data.VerifyCodeData
import com.example.nextclass.utils.CHANGE_EMAIL
import com.example.nextclass.utils.CHANGE_INFO
import com.example.nextclass.utils.CHANGE_PASSWORD
import com.example.nextclass.utils.COMMENT_CHANGE
import com.example.nextclass.utils.COMMENT_DELETE
import com.example.nextclass.utils.COMMENT_SAVE
import com.example.nextclass.utils.DELETE_ID
import com.example.nextclass.utils.DUPLICATED_CHECK_ADDRESS
import com.example.nextclass.utils.FIND_ID
import com.example.nextclass.utils.FIND_PASSWORD
import com.example.nextclass.utils.GET_COMMENT
import com.example.nextclass.utils.GET_POST
import com.example.nextclass.utils.GET_SCORE
import com.example.nextclass.utils.GET_TIME_TABLE
import com.example.nextclass.utils.GET_TO_DO_LIST
import com.example.nextclass.utils.GET_USER_INFO
import com.example.nextclass.utils.GET_VERIFY_CODE
import com.example.nextclass.utils.LOGIN_ADDRESS
import com.example.nextclass.utils.MAIL_CHECK
import com.example.nextclass.utils.POST_CHANGE
import com.example.nextclass.utils.POST_DELETE
import com.example.nextclass.utils.POST_DELETE_TIMETABLE_DATA
import com.example.nextclass.utils.POST_DETAIL
import com.example.nextclass.utils.POST_MODIFY_TIMETABLE_DATA
import com.example.nextclass.utils.POST_SAVE
import com.example.nextclass.utils.POST_TIMETABLE_DATA
import com.example.nextclass.utils.REGISTER_ADDRESS
import com.example.nextclass.utils.SAVE_SCHEDULE
import com.example.nextclass.utils.SCORE_UPDATE
import com.example.nextclass.utils.SEND_CHANGE_MAIL
import com.example.nextclass.utils.VOTE
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {


    //key value 형식으로 달라고 했음

    //회원가입
    @POST(DUPLICATED_CHECK_ADDRESS)
    suspend fun idDuplicateCheck(@Body checkRequest: DuplicateCheckRequest): Response<ServerResponse<Any>>

    @POST(DUPLICATED_CHECK_ADDRESS)
    suspend fun emailDuplicateCheck(@Body checkRequest: DuplicateCheckRequest): Response<ServerResponse<Any>>


    @POST(REGISTER_ADDRESS)
    suspend fun postUserJoinInfo(@Body joinInfo: JoinRequest):Response<ServerResponse<Any>>

    @POST(LOGIN_ADDRESS)
    suspend fun postUserLoginForm(@Body loginInfo: LoginRequest):Response<ServerResponse<TokenData>>



    //사용자 정보
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

    @POST(MAIL_CHECK)
    suspend fun verifyCodeCheck(@Body mailCheckBody: VerifyCodeData):Response<ServerResponse<Any>>

    @POST(FIND_ID)
    suspend fun findId(@Body email: FindIDOrPasswordData):Response<ServerResponse<Any>>

    @POST(FIND_PASSWORD)
    suspend fun findPassword(@Body id: FindIDOrPasswordData):Response<ServerResponse<Any>>

    //시간표
    @POST(POST_TIMETABLE_DATA)
    suspend fun postTimeTableData(@Body classData: ClassData):Response<ServerResponse<Any>>

    @POST(POST_MODIFY_TIMETABLE_DATA)
    suspend fun postModifyTimeTableData(@Body classData: ClassData):Response<ServerResponse<Any>>

    @POST(POST_DELETE_TIMETABLE_DATA)
    suspend fun postDeleteTimeTableData(@Body uuid: ClassUUid):Response<ServerResponse<Any>>

    @POST(GET_TIME_TABLE)
    suspend fun getCurrentTimeTable(@Body semester: PostSemester):Response<ServerResponse<List<ClassData>>>

    //공통 코드입력
    @POST(GET_VERIFY_CODE)
    suspend fun getVerifyCode(@Body email: VerifyCodeData):Response<ServerResponse<Any>>

    @POST(GET_SCORE)
    suspend fun getScore():Response<ServerResponse<AllScore>>

    @POST(SCORE_UPDATE)
    suspend fun scoreUpdate(@Body scoreList: PostClassScoreList):Response<ServerResponse<Any>>


    //커뮤니티
    @POST(POST_SAVE)
    suspend fun postSave(@Body postWriteData: PostWriteData):Response<ServerResponse<Any>>

    @POST(POST_CHANGE)
    suspend fun postChange(@Body postWriteData: PostWriteData):Response<ServerResponse<Any>>

    @POST(POST_DELETE)
    suspend fun postDelete(@Body post_sequence: String):Response<ServerResponse<Any>>

    @POST(GET_POST)
    suspend fun getPostList(@Body postListData: PostListData):Response<ServerResponse<List<CommunityPostData>>>

    @POST(GET_COMMENT)
    suspend fun getCommentList(@Body commentListData: CommentListData):Response<ServerResponse<List<CommunityCommentData>>>

    @POST(COMMENT_SAVE)
    suspend fun commentSave(@Body writeCommentData: CommentWriteData):Response<ServerResponse<Any>>

    @POST(COMMENT_CHANGE)
    suspend fun commentChange(@Body scoreList: PostClassScoreList):Response<ServerResponse<Any>>

    @POST(COMMENT_DELETE)
    suspend fun commentDelete(@Body sequence: PostAndCommentSequence):Response<ServerResponse<Any>>

    @GET(POST_DETAIL)
    suspend fun postDetail(@Path("post_sequence") post_sequence:String):Response<ServerResponse<CommunityPostData>>

    @POST(VOTE)
    suspend fun vote(@Body vote:PostAndCommentSequence):Response<ServerResponse<Any>>

    //투두리스트
    @POST(SAVE_SCHEDULE)
    suspend fun saveSchedule(@Body singleSchedule:ScheduleData):Response<ServerResponse<Any>>

    @POST(GET_TO_DO_LIST)
    suspend fun getTodoList():Response<ServerResponse<List<ScheduleData>>>


    //테스트
    @POST("/test")
    suspend fun tokenTest():Response<ServerResponse<Any>?>

    @POST("/test")
    suspend fun refreshTest():Response<ServerResponse<Any>>
}