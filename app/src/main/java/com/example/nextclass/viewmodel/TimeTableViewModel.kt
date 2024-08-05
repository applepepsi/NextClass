package com.example.nextclass.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.AllScore
import com.example.nextclass.Data.ClassData
import com.example.nextclass.Data.ClassScore
import com.example.nextclass.Data.ClassUUid
import com.example.nextclass.Data.SingleSemesterScore
import com.example.nextclass.R
import com.example.nextclass.repository.TimeTableRepository
import com.example.nextclass.utils.CLASS_ALREADY_EXISTS_IN_TIMETABLE
import com.example.nextclass.utils.CutEntranceYear
import com.example.nextclass.utils.GetSemester.getCurrentSemester
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

    private val _loading=mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _timeTableDataList=mutableStateOf(listOf<ClassData>())
    val timeTableDataList: State<List<ClassData>> = _timeTableDataList

    private val _timeTableToastMessage = mutableStateOf<String?>(null)
    val timeTableToastMessage: State<String?> = _timeTableToastMessage

    private val _timeTableScore = mutableStateOf<AllScore>(AllScore(
        average_grade = "1.60",
        credit_sum = 10,
        require_credit = 174,
        semesterList = listOf(
            SingleSemesterScore(
                semester="2024-1",
                score="1.60",
                dataList = listOf(
                    ClassScore(
                        title="과목1",
                        credit = 1,
                        grade = 2,
                        category = "공통",
                        achievement = "A",
                        semester="2024-1",
                    ),
                    ClassScore(
                        title="과목1",
                        credit = 1,
                        grade = 2,
                        category = "선택",
                        achievement = "A",
                        student_score = 92.0,
                        average_socre = 50.0,
                        standard_deviation = 25.0,
                        semester="2024-1",
                    ),
                    ClassScore(
                        title="과목1",
                        credit = 1,
                        grade = 2,
                        category = "선택",
                        achievement = "A",
                        student_score = 92.0,
                        average_socre = 50.0,
                        standard_deviation = 25.0,
                        semester="2024-1",
                    ),
                    ClassScore(
                        title="과목1",
                        credit = 1,
                        grade = 2,
                        category = "선택",
                        achievement = "A",
                        student_score = 92.0,
                        average_socre = 50.0,
                        standard_deviation = 25.0,
                        semester="2024-1",
                    )
                )
            ),
            SingleSemesterScore(
                semester="2024-1",
                score="1.70",
                dataList = listOf(
                    ClassScore(
                        title="과목2",
                        credit = 1,
                        grade = 2,
                        category = "공통",
                        achievement = "A",
                        semester="2024-1",
                    ),
                    ClassScore(
                        title="과목3",
                        credit = 1,
                        grade = 2,
                        category = "선택",
                        achievement = "A",
                        student_score = 92.0,
                        average_socre = 50.0,
                        standard_deviation = 25.0,
                        semester="2024-1",
                    ),
                    ClassScore(
                        title="과목1",
                        credit = 1,
                        grade = 2,
                        category = "선택",
                        achievement = "A",
                        student_score = 92.0,
                        average_socre = 50.0,
                        standard_deviation = 25.0,
                        semester="2024-1",
                    ),
                    ClassScore(
                        title="과목5",
                        credit = 1,
                        grade = 2,
                        category = "선택",
                        achievement = "A",
                        student_score = 92.0,
                        average_socre = 50.0,
                        standard_deviation = 25.0,
                        semester="2024-1",
                    )
                )
            ),
            SingleSemesterScore(
                semester="2024-1",
                score="1.70",
                dataList = listOf(
                    ClassScore(
                        title="과목2",
                        credit = 1,
                        grade = 2,
                        category = "공통",
                        achievement = "A",
                        semester="2024-1",
                    ),
                    ClassScore(
                        title="과목3",
                        credit = 1,
                        grade = 2,
                        category = "선택",
                        achievement = "A",
                        student_score = 92.0,
                        average_socre = 50.0,
                        standard_deviation = 25.0,
                        semester="2024-1",
                    ),
                    ClassScore(
                        title="과목1",
                        credit = 1,
                        grade = 2,
                        category = "선택",
                        achievement = "A",
                        student_score = 92.0,
                        average_socre = 50.0,
                        standard_deviation = 25.0,
                        semester="2024-1",
                    ),
                    ClassScore(
                        title="과목5",
                        credit = 1,
                        grade = 2,
                        category = "선택",
                        achievement = "A",
                        student_score = 92.0,
                        average_socre = 50.0,
                        standard_deviation = 25.0,
                        semester="2024-1",
                    )
                )
            ),
        )
    ))
    val timeTableScore: State<AllScore> = _timeTableScore

    private val _singleSemesterScore = mutableStateOf<SingleSemesterScore>(SingleSemesterScore(
        semester="2024-1",
        score="1.70",
        dataList = listOf(
            ClassScore(
                title="과목2",
                credit = 1,
                grade = 2,
                category = "공통",
                achievement = "A",
                semester="2024-1",
            ),
            ClassScore(
                title="과목3",
                credit = 1,
                grade = 2,
                category = "선택",
                achievement = "A",
                student_score = 92.0,
                average_socre = 50.0,
                standard_deviation = 25.0,
                semester="2024-1",
            ),
            ClassScore(
                title="과목1",
                credit = 1,
                grade = 2,
                category = "선택",
                achievement = "A",
                student_score = 92.0,
                average_socre = 50.0,
                standard_deviation = 25.0,
                semester="2024-1",
            ),
            ClassScore(
                title="과목5",
                credit = 1,
                grade = 2,
                category = "선택",
                achievement = "A",
                student_score = 92.0,
                average_socre = 50.0,
                standard_deviation = 25.0,
                semester="2024-1",
            )
        )
    ),)
    val singleSemesterScore: State<SingleSemesterScore> = _singleSemesterScore


    fun toggleInsertClassDataDialogState(){
        resetClassData()
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
        _classData.value= selectClassData.value
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
        _classData.value = _classData.value.copy(class_grade = CutEntranceYear.deleteGradeEntranceYear(class_grade))
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
    fun postClassData(){

        if(scheduleDataCheck()){

//            _classData.value = _classData.value.copy(week=_classData.value.week)
            //전송
            _classData.value = _classData.value.copy(semester = getCurrentSemester())
            Log.d("시간표", _classData.value.toString())
            timeTableRepository.postTimeTableData(_classData.value){serverResponse ->
                Log.d("시간표 전송 성공", serverResponse.toString())

                when(serverResponse?.errorCode){
                    CLASS_ALREADY_EXISTS_IN_TIMETABLE->{
                        _addClassErrorMessage.value = StringValue.StringResource(R.string.DuplicatedClassTime)
                        _addClassError.value = true
                    }
                    else->{
                        _addClassErrorMessage.value = StringValue.Empty
                        _addClassError.value = false
                        getTimeTable()
                        toggleInsertClassDataDialogState()
                    }
                }
//                _addClassErrorMessage.value = StringValue.Empty
//                _addClassError.value = false


            }
        }else{
            //에러 출력
            _addClassErrorMessage.value = StringValue.StringResource(R.string.joinFailError)
            _addClassError.value = true
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postModifyClassData(){

        if(scheduleDataCheck()){

//            _classData.value = _classData.value.copy(week=_classData.value.week)
            //전송
//            _classData.value = _classData.value.copy(semester = getCurrentSemester())


            timeTableRepository.postModifyTimeTableData(_classData.value){serverResponse ->

//                if(serverResponse?.code==200){
//                    Log.d("시간표 수정 성공", serverResponse.toString())
//
//                    _addClassErrorMessage.value = StringValue.Empty
//                    _addClassError.value = false
//
//                    _timeTableDataList.value=replaceModifyClassData()
//                    toggleSetShowClassDataModifyDialogState()
//                    Log.d("_timeTableDataList", _timeTableDataList.value.toString())
//                }else{
//                    //서버에서 받은 에러 출력
//                }
                when(serverResponse?.errorCode){
                    CLASS_ALREADY_EXISTS_IN_TIMETABLE->{
                        _addClassErrorMessage.value = StringValue.StringResource(R.string.DuplicatedClassTime)
                        _addClassError.value = true
                    }
                    else->{
                        _addClassErrorMessage.value = StringValue.Empty
                        _addClassError.value = false

                        _timeTableDataList.value=replaceModifyClassData()
                        toggleSetShowClassDataModifyDialogState()
                    }
                }

            }

        }else{
            //에러 출력
            _addClassErrorMessage.value = StringValue.StringResource(R.string.joinFailError)
            _addClassError.value = true
        }
    }

    private fun replaceModifyClassData(): List<ClassData> {
        return _timeTableDataList.value.map {
            if(it.uuid==_classData.value.uuid){
                _classData.value
            }else{
                it
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postDeleteScheduleData(){


//            _selectClassData.value = _selectClassData.value.copy(week=_selectClassData.value.week)
            val classUUid=ClassUUid(_selectClassData.value.uuid!!)
            //전송
            timeTableRepository.postDeleteTimeTableData(classUUid){serverResponse ->
                if(serverResponse?.code==200){

                    deleteScheduleDataByList()
                    toggleSetShowClassDetailDialogState()
                    Log.d("시간표 제거 성공", serverResponse.toString())
                }else{
                    _timeTableToastMessage.value = serverResponse!!.errorDescription
                }
            }
    }
    fun clearToastMessage() {
        _timeTableToastMessage.value = null
    }

    private fun deleteScheduleDataByList(){
        //서버에서 삭제에 성공했다는 답을 받으면 나도 실제로 데이터리스트에서 값을 제거
        _timeTableDataList.value=_timeTableDataList.value.filter {
            it.uuid != _selectClassData.value.uuid
        }
        Log.d("시간표 제거 성공", "리스트에서 제거 성공")
    }




    private fun scheduleDataCheck():Boolean{
        val classData=_classData.value

        return when {
            startTimeAfterEndTimeCheck(classData) -> {
                _timeTableToastMessage.value = "수업 시작 시간은 수업 종료 시간 보다 클 수 없습니다."
                false
            }
            fieldEmptyCheck(classData) -> {
                _timeTableToastMessage.value = "모든 항목을 기입해 주세요."
                false
            }
            else -> true
        }
    }
    private fun startTimeAfterEndTimeCheck(classData: ClassData): Boolean {
        return classData.class_start_time > classData.class_end_time
    }

    private fun fieldEmptyCheck(classData: ClassData): Boolean {
        return classData.week.isEmpty()
                || classData.teacher_name.isEmpty()
                || classData.title.isEmpty()
                || classData.school.isEmpty()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeTable(){
        _loading.value=true
        val currentSemester=getCurrentSemester()
        timeTableRepository.getCurrentTimeTableData(currentSemester){serverResponse ->
            if(serverResponse?.data != null){
                Log.d("시간표 가져오기 성공", serverResponse.toString())
                _timeTableDataList.value=serverResponse.data
            }

            _loading.value=false
        }
        _loading.value=false
    }

    fun getTimeTableScore(){
        _timeTableScore.value= AllScore(
            average_grade = "1.60",
            credit_sum = 10,
            require_credit = 174,
            semesterList = listOf(
                SingleSemesterScore(
                    semester="2024-05-01",
                    score="1.60",
                    dataList = listOf(
                        ClassScore(
                            title="과목1",
                            credit = 1,
                            grade = 2,
                            category = "공통",
                            achievement = "A",
                            semester = "2025-04-05"
                        ),
                        ClassScore(
                            title="과목1",
                            credit = 1,
                            grade = 2,
                            category = "선택",
                            achievement = "A",
                            student_score = 92.0,
                            average_socre = 50.0,
                            standard_deviation = 25.0,
                            semester = "2025-04-05"
                        ),
                        ClassScore(
                            title="과목1",
                            credit = 1,
                            grade = 2,
                            category = "선택",
                            achievement = "A",
                            student_score = 92.0,
                            average_socre = 50.0,
                            standard_deviation = 25.0,
                            semester = "2025-04-05"
                        ),
                        ClassScore(
                            title="과목1",
                            credit = 1,
                            grade = 2,
                            category = "선택",
                            achievement = "A",
                            student_score = 92.0,
                            average_socre = 50.0,
                            standard_deviation = 25.0,
                            semester = "2025-04-05"
                        )
                    )
                ),

            )
        )
    }
}