package com.example.nextclass.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.ClassData
import javax.inject.Inject

class TimeTableViewModel @Inject constructor(): ViewModel(){

    private var _insertClassDataDialogState= mutableStateOf(false)

    val insertClassDataDialogState: State<Boolean> = _insertClassDataDialogState

    private var _setShowClassDetailDialog= mutableStateOf(false)

    val setShowClassDetailDialog: State<Boolean> = _setShowClassDetailDialog

    private var _setShowClassDataModifyDialog= mutableStateOf(false)

    val setShowClassDataModifyDialog: State<Boolean> = _setShowClassDataModifyDialog

    private var _selectClassData= mutableStateOf<ClassData?>(null)

    val selectClassData: State<ClassData?> = _selectClassData

    private var _className= mutableStateOf("")
    val className: State<String> = _className

    private var _grade= mutableStateOf("")
    val grade: State<String> = _grade

    private var _teacherName= mutableStateOf("")
    val teacherName: State<String> = _teacherName

    private var _credit= mutableStateOf(1)
    val credit: State<Int> = _credit

    private var _dayOfWeek= mutableStateOf("월")
    val dayOfWeek: State<String> = _dayOfWeek

    private var _startClassTime= mutableStateOf(1)
    val startClassTime: State<Int> = _startClassTime

    private var _endClassTime= mutableStateOf(1)
    val endClassTime: State<Int> = _endClassTime

    private var _schoolName= mutableStateOf("")
    val schoolName: State<String> = _schoolName

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
    fun toggleInsertClassDataDialogState(){
        _insertClassDataDialogState.value=!_insertClassDataDialogState.value
    }

    fun toggleSetShowClassDetailDialogState(){
        _setShowClassDetailDialog.value=!_setShowClassDetailDialog.value
    }

    fun toggleSetShowClassDataModifyDialogState(){
        _setShowClassDataModifyDialog.value=!_setShowClassDataModifyDialog.value
    }

    fun setSelectClassData(classData: ClassData){
        _selectClassData.value=classData
    }

    fun updateClassName(className: String) {
        _className.value = className
    }

    fun updateGrade(grade: String) {
        _grade.value = grade
    }

    fun updateTeacherName(teacherName: String) {
        _teacherName.value = teacherName
    }

    fun updateCredit(credit: Int) {
        _credit.value = credit
    }

    fun updateDayOfWeek(dayOfWeek: String) {
        _dayOfWeek.value = dayOfWeek
    }

    fun updateStartClassTime(startClassTime: Int) {
        _startClassTime.value = startClassTime
    }

    fun updateEndClassTime(endClassTime: Int) {
        _endClassTime.value = endClassTime
    }

    fun updateSchoolName(schoolName: String) {
        _schoolName.value = schoolName
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

}