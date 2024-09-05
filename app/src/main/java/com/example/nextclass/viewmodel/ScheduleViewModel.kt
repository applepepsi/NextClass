package com.example.nextclass.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.ScheduleData.ScheduleData
import com.example.nextclass.Data.TimeData
import com.example.nextclass.R
import com.example.nextclass.repository.ScheduleRepository
import com.example.nextclass.utils.EXPIRED_REFRESH_TOKEN
import com.example.nextclass.utils.SUCCESS_CODE
import com.example.nextclass.utils.SortScheduleList
import com.example.nextclass.utils.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject
import java.time.format.DateTimeFormatter
@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
): ViewModel(){

    private var _scheduleDataList=mutableStateOf(listOf<ScheduleData>())
    val scheduleDataList: State<List<ScheduleData>> = _scheduleDataList

    private var _groupByDateScheduleData = mutableStateOf<Map<LocalDate, List<ScheduleData>>>(emptyMap())
    val groupByDateScheduleData: State<Map<LocalDate, List<ScheduleData>>> = _groupByDateScheduleData

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

    private val _saveScheduleState= mutableStateOf(false)
    val saveScheduleState: State<Boolean> = _saveScheduleState

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
        _scheduleData.value=_scheduleData.value.copy(alarm_time = combinedDateTime().toString())

        Log.d("스케쥴 시간", _scheduleData.value.toString())
        if(checkScheduleData()){
            //서버로 전송
            scheduleRepository.saveSchedule(_scheduleData.value) { SaveScheduleResult->
                if (SaveScheduleResult != null) {
                    if(SaveScheduleResult.code== SUCCESS_CODE){
                        Log.d("스케쥴 저장 성공", SaveScheduleResult.data.toString())
                        toggleSaveScheduleResult()
                    }
                }
                resetScheduleData()
            }
        }
    }

    fun toggleSaveScheduleResult(){
        _saveScheduleState.value=!_saveScheduleState.value
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postModifyScheduleData(){
        _scheduleData.value=_scheduleData.value.copy(alarm_time = combinedDateTime().toString())

        Log.d("스케쥴 시간", _scheduleData.value.toString())
        if(checkScheduleData()){
            //서버로 전송
            scheduleRepository.updateSchedule(_scheduleData.value) { UpdateScheduleResult->
                if (UpdateScheduleResult != null) {
                    if(UpdateScheduleResult.code== SUCCESS_CODE){
                        Log.d("스케쥴 수정 성공", UpdateScheduleResult.data.toString())
                        toggleSaveScheduleResult()
                    }
                }
                resetScheduleData()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setSelectScheduleData(selectScheduleData: ScheduleData){


        val alarmTimeString = selectScheduleData.alarm_time
        val alarmTime = LocalDateTime.parse(alarmTimeString)

        _timeData.value = _timeData.value.copy(
            selectDate = alarmTime.toLocalDate(),
            selectTime = alarmTime.toLocalTime())

        updateScheduleDetail(selectScheduleData.content)

        _scheduleData.value=_scheduleData.value.copy(uuid = selectScheduleData.uuid)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun deleteScheduleData(){


        Log.d("스케쥴 시간", _scheduleData.value.toString())

            //서버로 전송
            scheduleRepository.deleteSchedule(_scheduleData.value) { DeleteScheduleResult->
                if (DeleteScheduleResult != null) {
                    if(DeleteScheduleResult.code== SUCCESS_CODE){
                        Log.d("스케쥴 제거 성공", DeleteScheduleResult.data.toString())

                        getScheduleData(splitToday = false)

                    }
                }
                resetScheduleData()
            }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkScheduleData():Boolean{
        if(_scheduleData.value.alarm_time<=LocalDateTime.now().toString()){
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
        _scheduleData.value= ScheduleData()
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
//            "작성 시간 오름 차순"->SortScheduleList.sortByAscendingCreationTime(beforeSortList)
//            "작성 시간 내림 차순"-> SortScheduleList.sortByDescendingCreationTime(beforeSortList)
            "남은 시간 오름 차순"->SortScheduleList.sortByAscendingAlarmTime(beforeSortList)
            "남은 시간 내림 차순"->SortScheduleList.sortByDescendingAlarmTime(beforeSortList)
            else -> { beforeSortList }
        }

        Log.d("inputSortType",inputSortType)

    }

    fun setSortTypeScheduleDataList(inputSortType:String){

//        val beforeSortList=_scheduleDataList.value

        val sortedScheduleDataList = when (inputSortType) {
            "남은 시간 오름 차순"->_groupByDateScheduleData.value.toSortedMap(compareByDescending { it })
            "남은 시간 내림 차순"->_groupByDateScheduleData.value.toSortedMap()
            else -> {_groupByDateScheduleData.value.toSortedMap(compareByDescending { it })}
        }

        _groupByDateScheduleData.value= emptyMap()
        _groupByDateScheduleData.value = sortedScheduleDataList

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getScheduleData(splitToday:Boolean=false){

        scheduleRepository.getTodoList { getTodoListResult->
            if (getTodoListResult != null) {
                if(getTodoListResult.code== SUCCESS_CODE){
                    Log.d("스케쥴 가져오기 성공", getTodoListResult.data.toString())
                    _scheduleDataList.value=getTodoListResult.data!!
                    groupedScheduleData(splitToday)
                }
            }

        }
    }

    fun resetScheduleDataList(){
        _scheduleDataList.value= emptyList()
    }


//    fun setScheduleData(scheduleData: ScheduleData){
//
//        Log.d("scheduleData", scheduleData.toString())
//        _scheduleData.value=scheduleData
//    }

    //날짜별로 그룹화
    @RequiresApi(Build.VERSION_CODES.O)
    fun groupedScheduleData(splitToday:Boolean=false) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val filterScheduleData = _scheduleDataList.value
        val today = if (splitToday) LocalDate.now() else null

        _groupByDateScheduleData.value = filterScheduleData
            .filter { !splitToday || LocalDateTime.parse(it.alarm_time, formatter).toLocalDate() == today }
            .groupBy { LocalDateTime.parse(it.alarm_time, formatter).toLocalDate() }
            .toSortedMap(compareByDescending { it })

        Log.d("홈화면 스케쥴", _groupByDateScheduleData.value.toString())
    }



}