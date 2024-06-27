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
    companion object{
        const val MIN_HEIGHT_PERCENT=0.3
        const val MAX_HEIGHT_PERCENT=0.9

    }
}