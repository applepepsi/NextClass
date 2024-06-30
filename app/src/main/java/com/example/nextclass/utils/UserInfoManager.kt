package com.example.nextclass.utils

import android.content.Context
import com.example.nextclass.Data.TokenData

object UserInfoManager {

    private const val PREFS_NAME = "autoLoginInfo"
    private const val USER_ID = "userId"
    private const val USER_PASSWORD = "userPassword"

    fun saveAutoLoginInfo(context: Context, userId: String, userPassword: String) {
        val autoLoginInfo = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = autoLoginInfo.edit()

        editor.putString(USER_ID, userId)
        editor.putString(USER_PASSWORD, userPassword)
        editor.apply()
    }

    fun clearUserInfo(context: Context) {
        val autoLoginInfo = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = autoLoginInfo.edit()
        editor.putString(USER_ID, null)
        editor.putString(USER_PASSWORD, null)
        editor.apply()
    }

    fun getUserInfo(context: Context): Pair<String?, String?> {
        val autoLoginInfo = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userId = autoLoginInfo.getString(USER_ID, null)
        val userPassword = autoLoginInfo.getString(USER_PASSWORD, null)

        return Pair(userId, userPassword)
    }

}