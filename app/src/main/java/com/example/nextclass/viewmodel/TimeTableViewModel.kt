package com.example.nextclass.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TimeTableViewModel @Inject constructor(): ViewModel(){
    private var _layoutHeight= mutableStateOf(0)

    val layoutHeight: State<Int> = _layoutHeight

    fun updateLayoutHeight(
        initialHeight: Int,
        initialTouchY: Float,
        currentTouchY: Float,
        screenHeight: Int
    ){
        val heightChange = (currentTouchY - initialTouchY).toInt()
        var newHeight = initialHeight - heightChange

        newHeight = newHeight.coerceAtLeast((screenHeight * MIN_HEIGHT_PERCENT).toInt())
        newHeight = newHeight.coerceAtMost((screenHeight * MAX_HEIGHT_PERCENT).toInt())

        _layoutHeight.value = newHeight
    }

    companion object{
        const val MIN_HEIGHT_PERCENT=0.3
        const val MAX_HEIGHT_PERCENT=0.9

    }
}