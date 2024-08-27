package com.example.nextclass.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object RecentSearchWordManager {

    fun loadRecentSearchList(context: Context): MutableList<String> {
        val sharedPreferences = context.getSharedPreferences("RecentSearchText", Context.MODE_PRIVATE)
        return getRecentSearchList(sharedPreferences)
    }

    fun saveSearchText(context: Context, searchText: String): MutableList<String> {
        val sharedPreferences = context.getSharedPreferences("RecentSearchText", Context.MODE_PRIVATE)
        val recentSearchList = getRecentSearchList(sharedPreferences)
        Log.d("searchText",searchText)
        if (!recentSearchList.contains(searchText)) {
            recentSearchList.add(0, searchText)
            if (recentSearchList.size > 20) {
                recentSearchList.removeAt(recentSearchList.size - 1)
            }
            saveRecentSearchList(sharedPreferences, recentSearchList)
        }

        return recentSearchList
    }

    fun deleteRecentSearchText(context: Context, searchText: String): MutableList<String> {
        val sharedPreferences = context.getSharedPreferences("RecentSearchText", Context.MODE_PRIVATE)
        val recentSearchList = getRecentSearchList(sharedPreferences)

        if (recentSearchList.remove(searchText)) {
            saveRecentSearchList(sharedPreferences, recentSearchList)
        }

        return recentSearchList
    }

    private fun getRecentSearchList(sharedPreferences: SharedPreferences): MutableList<String> {
        val savedString = sharedPreferences.getString("recentSearchText", "")
        return savedString?.split(",")?.toMutableList() ?: mutableListOf()
    }

    private fun saveRecentSearchList(sharedPreferences: SharedPreferences, recentSearchList: List<String>) {
        val editor = sharedPreferences.edit()
        val newSavedString = recentSearchList.joinToString(",")
        editor.putString("recentSearchText", newSavedString)
        editor.apply()
    }
}