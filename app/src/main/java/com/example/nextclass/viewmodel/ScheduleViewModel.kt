package com.example.nextclass.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.TimeData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(): ViewModel(){

    private var _timeData= mutableStateOf(TimeData())

    val timeData: State<TimeData> = _timeData


    private var _datePickerState=mutableStateOf(false)

    val datePickerState: State<Boolean> = _datePickerState

    private var _timePickerState=mutableStateOf(false)

    val timePickerState: State<Boolean> = _timePickerState



    fun updateScheduleDate(selectDate: LocalDate) {
        Log.d("selectDate", selectDate.toString())
        _timeData.value=_timeData.value.copy(selectDate=selectDate)
    }

    fun updateScheduleTime(time: LocalTime){
        Log.d("selectTime", time.toString())
        _timeData.value=_timeData.value.copy(selectTime = time)

    }

    fun combinedDateTime(): LocalDateTime {


        return LocalDateTime.of(timeData.value.selectDate, timeData.value.selectTime)
    }

    fun toggleDatePickerState(){
        _datePickerState.value=!_datePickerState.value
    }

    fun toggleTimePickerState(){
        _timePickerState.value=!_timePickerState.value
    }



}