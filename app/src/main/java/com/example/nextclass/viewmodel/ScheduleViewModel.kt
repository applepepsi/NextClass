package com.example.nextclass.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.ScheduleData
import com.example.nextclass.Data.ServerResponse
import com.example.nextclass.Data.TimeData
import com.example.nextclass.R
import com.example.nextclass.repository.ScheduleRepository
import com.example.nextclass.repository.TestRepository
import com.example.nextclass.utils.EXPIRED_REFRESH_TOKEN
import com.example.nextclass.utils.SortList
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

    private var _scheduleDataList=mutableStateOf(listOf<ScheduleData>())
    val scheduleDataList: State<List<ScheduleData>> = _scheduleDataList

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

    private val _tokenCheckResult=mutableStateOf(false)
    val tokenCheckResult: State<Boolean> = _tokenCheckResult

    private var _toggleLazyType= mutableStateOf(false)
    val toggleLazyType: State<Boolean> = _toggleLazyType

    private var _toggleShowSortBottomSheet= mutableStateOf(false)
    val toggleShowSortBottomSheet: State<Boolean> = _toggleShowSortBottomSheet

    private var _sortType= mutableStateOf("")
    val sortType: State<String> = _sortType
//
//    private var _selectScheduleData= mutableStateOf(ScheduleData())
//    val selectScheduleData: State<ScheduleData> = _selectScheduleData

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
        _scheduleData.value=_scheduleData.value.copy(content =scheduleDetail)
        updateTextCount(scheduleDetail)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun combinedDateTime(): LocalDateTime {

        return LocalDateTime.of(timeData.value.selectDate, timeData.value.selectTime)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postScheduleData(){
        _scheduleData.value=_scheduleData.value.copy(alarm_time = combinedDateTime())

        Log.d("스케쥴 시간", _scheduleData.value.toString())
        if(checkScheduleData()){
            //서버로 전송
            scheduleRepository.tokenCheck { ServerResponse->
                if (ServerResponse != null) {
                    if(ServerResponse.errorCode== EXPIRED_REFRESH_TOKEN){
                        _tokenCheckResult.value=true
                    }
                }
                resetScheduleData()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postModifyScheduleData(){
        _scheduleData.value=_scheduleData.value.copy(alarm_time = combinedDateTime())

        Log.d("스케쥴 시간", _scheduleData.value.toString())
        if(checkScheduleData()){
            //서버로 전송
            scheduleRepository.tokenCheck { ServerResponse->
                if (ServerResponse != null) {
                    if(ServerResponse.errorCode== EXPIRED_REFRESH_TOKEN){
                        _tokenCheckResult.value=true
                    }
                }
                resetScheduleData()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkScheduleData():Boolean{
        if(_scheduleData.value.alarm_time<=LocalDateTime.now()){
            //에러 처리
            _scheduleErrorState.value=true
            _scheduleErrorMessage.value=StringValue.StringResource(R.string.WrongDate)
            return false
        }
        else if(_scheduleData.value.content.isEmpty()){
            //에러 처리
            _scheduleErrorState.value=true
            _scheduleErrorMessage.value=StringValue.StringResource(R.string.WrongScheduleDetail)
            return false
        }
        _scheduleErrorState.value=false
        _scheduleErrorMessage.value=StringValue.Empty
        return true
    }

    fun resetScheduleData(){
        _scheduleData.value=ScheduleData()
    }



    fun toggleDatePickerState(){
        _datePickerState.value=!_datePickerState.value
    }

    fun toggleTimePickerState(){
        _timePickerState.value=!_timePickerState.value
    }

    fun changeLazyViewType(){
        _toggleLazyType.value=!_toggleLazyType.value
    }

    fun toggleSortBottomSheet(){
       _toggleShowSortBottomSheet.value=!_toggleShowSortBottomSheet.value
    }


    //todo 서버 켜지면 값 잘 가져오는지 확인
    fun setSortType(inputSortType:String){

        val beforeSortList=_scheduleDataList.value

        _scheduleDataList.value = when(inputSortType){
            "작성 시간 오름 차순"->SortList.sortByAscendingCreationTime(beforeSortList)
            "작성 시간 내림 차순"->SortList.sortByDescendingCreationTime(beforeSortList)
            "남은 시간 오름 차순"->SortList.sortByAscendingAlarmTime(beforeSortList)
            "남은 시간 내림 차순"->SortList.sortByDescendingAlarmTime(beforeSortList)
            else -> { beforeSortList }
        }

        Log.d("inputSortType",inputSortType)

    }

    fun getScheduleData(scheduleDataList:List<ScheduleData>){
        _scheduleDataList.value=scheduleDataList
    }

    fun setScheduleData(scheduleData:ScheduleData){

        Log.d("scheduleData", scheduleData.toString())
        _scheduleData.value=scheduleData
    }


}