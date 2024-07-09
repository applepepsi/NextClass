package com.example.nextclass.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.ClassData
import com.example.nextclass.R
import com.example.nextclass.repository.TimeTableRepository
import com.example.nextclass.utils.ConvertDayOfWeek
import com.example.nextclass.utils.CutEntranceYear
import com.example.nextclass.utils.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
@HiltViewModel
class TimeTableViewModel @Inject constructor(
    private val timeTableRepository: TimeTableRepository
): ViewModel(){

    private var _insertClassDataDialogState= mutableStateOf(false)

    val insertClassDataDialogState: State<Boolean> = _insertClassDataDialogState

    private var _setShowClassDetailDialog= mutableStateOf(false)

    val setShowClassDetailDialog: State<Boolean> = _setShowClassDetailDialog

    private var _setShowClassDataModifyDialog= mutableStateOf(false)

    val setShowClassDataModifyDialog: State<Boolean> = _setShowClassDataModifyDialog

    private var _selectClassData= mutableStateOf(ClassData())

    val selectClassData: State<ClassData> = _selectClassData

    private var _classData = mutableStateOf(ClassData())

    val classData: State<ClassData> = _classData

    private var _toggleGradeDropDownMenu= mutableStateOf(false)
    val toggleGradeDropDownMenu: State<Boolean> = _toggleGradeDropDownMenu

    private var _toggleDayOfWeekDropDownMenu= mutableStateOf(false)
    val toggleDayOfWeekDropDownMenu: State<Boolean> = _toggleDayOfWeekDropDownMenu

    private var _toggleStartClassTimeDropDownMenu= mutableStateOf(false)
    val toggleStartClassTimeDropDownMenu: State<Boolean> = _toggleStartClassTimeDropDownMenu

    private var _toggleEndClassTimeDropDownMenu= mutableStateOf(false)
    val toggleEndClassTimeDropDownMenu: State<Boolean> = _toggleEndClassTimeDropDownMenu

    private var _toggleCreditDropDownMenu= mutableStateOf(false)
    val toggleCreditDropDownMenu: State<Boolean> = _toggleCreditDropDownMenu

    private val _addClassErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val addClassErrorMessage: State<StringValue> = _addClassErrorMessage

    private val _addClassError= mutableStateOf(false)
    val addClassError: State<Boolean> = _addClassError

    fun toggleInsertClassDataDialogState(){
        _insertClassDataDialogState.value=!_insertClassDataDialogState.value
        if(!_insertClassDataDialogState.value){
            resetClassData()
        }
    }

    private fun resetClassData() {
        _classData.value = ClassData()
    }

    fun toggleSetShowClassDetailDialogState(){
        _setShowClassDetailDialog.value=!_setShowClassDetailDialog.value
        if(_setShowClassDetailDialog.value){
            setModifyClassData()
        }
    }

    private fun setModifyClassData(){
        _classData.value=selectClassData.value!!
    }

    fun toggleSetShowClassDataModifyDialogState(){
        _setShowClassDataModifyDialog.value=!_setShowClassDataModifyDialog.value
        if(!_setShowClassDataModifyDialog.value){
            resetClassData()
        }
    }

    fun setSelectClassData(classData: ClassData){
        _selectClassData.value=classData
    }

    fun updateClassName(title: String) {
        _classData.value = _classData.value.copy(title = title)
    }

    fun updateGrade(class_grade: String) {
        _classData.value = _classData.value.copy(class_grade = CutEntranceYear.cutEntranceYear(class_grade))
    }

    fun updateTeacherName(teacher_name: String) {
        _classData.value = _classData.value.copy(teacher_name = teacher_name)
    }

    fun updateCredit(score: Int) {
        _classData.value = _classData.value.copy(score = score)
    }

    fun updateDayOfWeek(week: String) {
        _classData.value = _classData.value.copy(week = week)
    }

    fun updateStartClassTime(class_start_time: Int) {
        _classData.value = _classData.value.copy(class_start_time = class_start_time)
    }

    fun updateEndClassTime(class_end_time: Int) {
        _classData.value = _classData.value.copy(class_end_time = class_end_time)
    }

    fun updateSchoolName(school: String) {
        _classData.value = _classData.value.copy(school = school)
    }


    fun toggleGradeDropDownMenu() {
        _toggleGradeDropDownMenu.value = !_toggleGradeDropDownMenu.value
    }

    fun toggleDayOfWeekDropDownMenu() {
        _toggleDayOfWeekDropDownMenu.value = !_toggleDayOfWeekDropDownMenu.value
    }

    fun toggleStartClassTimeDropDownMenu() {
        _toggleStartClassTimeDropDownMenu.value = !_toggleStartClassTimeDropDownMenu.value
    }

    fun toggleEndClassTimeDropDownMenu() {
        _toggleEndClassTimeDropDownMenu.value = !_toggleEndClassTimeDropDownMenu.value
    }

    fun toggleCreditDropDownMenu() {
        _toggleCreditDropDownMenu.value = !_toggleCreditDropDownMenu.value
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postScheduleData(){

        if(scheduleDataCheck(classData.value)){

            _classData.value = _classData.value.copy(week=ConvertDayOfWeek.convertDayOfWeek(_classData.value.week))
            //전송
            _classData.value = _classData.value.copy(semester = getCurrentSemester())
            timeTableRepository.postTimeTableData(_classData.value){serverResponse ->
                Log.d("시간표 전송 성공", serverResponse.toString())
                _addClassErrorMessage.value = StringValue.Empty
                _addClassError.value = false
            }

        }else{
            //에러 출력
            _addClassErrorMessage.value = StringValue.StringResource(R.string.joinFailError)
            _addClassError.value = true
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postModifyScheduleData(){

        if(scheduleDataCheck(_classData.value)){

            _classData.value = _classData.value.copy(week=ConvertDayOfWeek.convertDayOfWeek(_classData.value.week))
            //전송
            _classData.value = _classData.value.copy(semester = getCurrentSemester())

            timeTableRepository.postModifyTimeTableData(_classData.value){serverResponse ->
                Log.d("시간표 수정 성공", serverResponse.toString())
                _addClassErrorMessage.value = StringValue.Empty
                _addClassError.value = false
            }

        }else{
            //에러 출력
            _addClassErrorMessage.value = StringValue.StringResource(R.string.joinFailError)
            _addClassError.value = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postDeleteScheduleData(){



            _selectClassData.value = _selectClassData.value.copy(week=ConvertDayOfWeek.convertDayOfWeek(_selectClassData.value.week))
            //전송

            timeTableRepository.postDeleteTimeTableData(_selectClassData.value){serverResponse ->
                Log.d("시간표 제거 성공", serverResponse.toString())

            }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentSemester(): String {
        val currentDate = LocalDate.now()
        val year = currentDate.year
        val month = currentDate.monthValue

        val (adjustedYear, semester) = when (month) {
            in 3..7 -> year to 1
            in 8..12 -> year to 2
            else -> (year - 1) to 2
        }
        return "$adjustedYear-$semester"
    }


    private fun scheduleDataCheck(value: ClassData):Boolean{
        val classData=_classData.value

        return classData.week.isNotEmpty()
                && classData.teacher_name.isNotEmpty()
                && classData.title.isNotEmpty()
                && classData.school.isNotEmpty()
    }
}