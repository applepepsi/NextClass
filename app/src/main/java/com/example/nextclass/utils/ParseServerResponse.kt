package com.example.nextclass.utils

import com.example.nextclass.Data.ServerResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object ParseServerResponse {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun parseResponse(jsonString: String): ServerResponse {
        return json.decodeFromString(jsonString)
    }
}