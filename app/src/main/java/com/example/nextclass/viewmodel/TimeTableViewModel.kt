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

    fun updateClassName(className: String) {
        _classData.value = _classData.value.copy(className = className)
    }

    fun updateGrade(grade: String) {
        _classData.value = _classData.value.copy(grade = grade)
    }

    fun updateTeacherName(teacherName: String) {
        _classData.value = _classData.value.copy(teacherName = teacherName)
    }

    fun updateCredit(credit: Int) {
        _classData.value = _classData.value.copy(credit = credit)
    }

    fun updateDayOfWeek(dayOfWeek: String) {
        _classData.value = _classData.value.copy(dayOfWeek = dayOfWeek)
    }

    fun updateStartClassTime(startClassTime: Int) {
        _classData.value = _classData.value.copy(startClassTime = startClassTime)
    }

    fun updateEndClassTime(endClassTime: Int) {
        _classData.value = _classData.value.copy(endClassTime = endClassTime)
    }

    fun updateSchoolName(schoolName: String) {
        _classData.value = _classData.value.copy(schoolName = schoolName)
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