package com.example.nextclass.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.ScheduleData
import com.example.nextclass.Data.TimeData
import com.example.nextclass.R
import com.example.nextclass.repository.ScheduleRepository
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.utils.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
): ViewModel(){

    private var _timeData= mutableStateOf(TimeData())

    val timeData: State<TimeData> = _timeData

    private var _scheduleData=mutableStateOf(ScheduleData())
    val scheduleData: State<ScheduleData> = _scheduleData

    private var _datePickerState=mutableStateOf(false)

    val datePickerState: State<Boolean> = _datePickerState

    private var _timePickerState=mutableStateOf(false)

    val timePickerState: State<Boolean> = _timePickerState

    private var _textCounter=mutableStateOf(0)

    val textCounter: State<Int> = _textCounter

    private val _scheduleErrorState=mutableStateOf(false)
    val scheduleErrorState: State<Boolean> = _scheduleErrorState

    private val _scheduleErrorMessage=mutableStateOf<StringValue>(StringValue.Empty)
    val scheduleErrorMessage: State<StringValue> = _scheduleErrorMessage

    fun updateScheduleDate(selectDate: LocalDate) {
        Log.d("selectDate", selectDate.toString())
        _timeData.value=_timeData.value.copy(selectDate=selectDate)
    }

    fun updateScheduleTime(time: LocalTime){
        Log.d("selectTime", time.toString())
        _timeData.value=_timeData.value.copy(selectTime = time)

    }

    private fun updateTextCount(scheduleDetail: String) {
        _textCounter.value=scheduleDetail.length
    }

    fun updateScheduleDetail(scheduleDetail:String){
        Log.d("scheduleDetail",scheduleDetail)
        _scheduleData.value=_scheduleData.value.copy(scheduleDetail=scheduleDetail)
        updateTextCount(scheduleDetail)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun combinedDateTime(): LocalDateTime {

        return LocalDateTime.of(timeData.value.selectDate, timeData.value.selectTime)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postScheduleDate(){
        _scheduleData.value=_scheduleData.value.copy(scheduleDate = combinedDateTime())

        if(checkScheduleData()){
            //서버로 전송
            scheduleRepository.tokenCheck {

            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkScheduleData():Boolean{
        if(_scheduleData.value.scheduleDate<=LocalDateTime.now()){
            //에러 처리
            _scheduleErrorState.value=true
            _scheduleErrorMessage.value=StringValue.StringResource(R.string.WrongDate)
            return false
        }
        else if(_scheduleData.value.scheduleDetail.isEmpty()){
            //에러 처리
            _scheduleErrorState.value=true
            _scheduleErrorMessage.value=StringValue.StringResource(R.string.WrongScheduleDetail)
            return false
        }
        _scheduleErrorState.value=false
        _scheduleErrorMessage.value=StringValue.Empty
        return true
    }

    fun toggleDatePickerState(){
        _datePickerState.value=!_datePickerState.value
    }

    fun toggleTimePickerState(){
        _timePickerState.value=!_timePickerState.value
    }



}