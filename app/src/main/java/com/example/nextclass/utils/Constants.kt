package com.example.nextclass.utils


//통신 결과
const val SUCCESS_CODE = 200
const val INVALID_JSON_TYPE="E00101"
const val REQUEST_BODY_EMPTY="E00102"
const val INVALID_PARAMETER_CONTAINS="E00103"
const val NOT_HAVE_PERMISSION_REQUEST="E00104"
const val INVALID_ACCESS_TOKEN="E00105"
const val EXPIRED_ACCESS_TOKEN="E00106"
const val INVALID_REFRESH_TOKEN="E00107"
const val EXPIRED_REFRESH_TOKEN="E00108"

const val INVALID_PARAMETER_NAME="E00201"
const val DUPLICATE_PARAMETER="E00202"
const val USER_NOT_EXIST="E00203"

//상수
const val MaxTextCount = 64



//주소
const val DUPLICATED_CHECK_ADDRESS="/duplicated_check"
const val REGISTER_ADDRESS="/register"
const val LOGIN_ADDRESS="/login"
const val POST_TIMETABLE_DATA="/timetable"
const val POST_MODIFY_TIMETABLE_DATA="/timetable_change"
const val GET_USER_INFO="/my_info"
const val CHANGE_PASSWORD="/change_password"
const val CHANGE_INFO="/change_info"