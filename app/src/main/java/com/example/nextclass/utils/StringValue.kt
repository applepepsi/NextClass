package com.example.nextclass.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


//context를 사용하지 않고 뷰모델에서 리소스를 사용하기 위해 사용
sealed class StringValue {

    data class DynamicString(val value: String) : StringValue()

    object Empty : StringValue()

    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : StringValue()

    fun asString(context: Context?): String {
        return when (this) {
            is Empty -> ""
            is DynamicString -> value
            is StringResource -> context?.getString(resId, *args).orEmpty()
        }
    }
}