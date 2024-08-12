package com.example.nextclass.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.nextclass.Data.AllScore
import com.example.nextclass.Data.ClassData
import com.example.nextclass.Data.ClassScore
import com.example.nextclass.Data.ClassUUid
import com.example.nextclass.Data.PostClassScoreList
import com.example.nextclass.Data.SingleSemesterScore
import com.example.nextclass.R
import com.example.nextclass.repository.TimeTableRepository
import com.example.nextclass.utils.CLASS_ALREADY_EXISTS_IN_TIMETABLE
import com.example.nextclass.utils.ConvertDayOfWeek
import com.example.nextclass.utils.CutEntranceYear
import com.example.nextclass.utils.GetSemester.getCurrentSemester
import com.example.nextclass.utils.SUCCESS_CODE
import com.example.nextclass.utils.StringValue
import com.example.nextclass.utils.TimeFormatter
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.google.gson.Gson

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

    private var _toggleCategoryDropDownMenu= mutableStateOf(false)
    val toggleCategoryDropDownMenu: State<Boolean> = _toggleCategoryDropDownMenu

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

    private val _timeTableScore = mutableStateOf<AllScore>(AllScore())
    val timeTableScore: State<AllScore> = _timeTableScore

    private val _singleSemesterScore = mutableStateOf(SingleSemesterScore())

    val singleSemesterScore: State<SingleSemesterScore> = _singleSemesterScore

    private val _scoreCreditDropDownMenuState = mutableStateListOf(false)
    val scoreCreditDropDownMenuState: SnapshotStateList<Boolean> = _scoreCreditDropDownMenuState

    private val _scoreGradeDropDownMenuState = mutableStateListOf(false)
    val scoreGradeDropDownMenuState: SnapshotStateList<Boolean> = _scoreGradeDropDownMenuState

    private val _scoreCategoryDropDownMenuState = mutableStateListOf(false)
    val scoreCategoryDropDownMenuState: SnapshotStateList<Boolean> = _scoreCategoryDropDownMenuState

    private val _scoreAchievementDropDownMenuState = mutableStateListOf(false)
    val scoreAchievementDropDownMenuState: SnapshotStateList<Boolean> = _scoreAchievementDropDownMenuState


    private val _selectSemester=mutableStateOf("")
    val selectSemester: State<String> = _selectSemester

    private val _addSemesterErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val addSemesterErrorMessage: State<StringValue> = _addSemesterErrorMessage

    private val _addSemesterErrorState = mutableStateOf(false)
    val addSemesterErrorState: State<Boolean> = _addSemesterErrorState

    private val _addScorePopupState = mutableStateOf(false)
    val addScorePopupState: State<Boolean> = _addScorePopupState

    private val _scoreModifyResultToastMessage = mutableStateOf<String?>(null)
    val scoreModifyResultToastMessage: State<String?> = _scoreModifyResultToastMessage

    private val _modifyScoreErrorMessage = mutableStateOf<StringValue>(StringValue.Empty)
    val modifyScoreErrorMessage: State<StringValue> = _modifyScoreErrorMessage

    private val _modifyScoreErrorState = mutableStateOf(false)
    val modifyScoreErrorState: State<Boolean> = _modifyScoreErrorState

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

    fun updateCategory(category: String){
        _classData.value=_classData.value.copy(category=category)
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

    fun toggleCategoryDropDownMenu() {
        _toggleCategoryDropDownMenu.value = !_toggleCategoryDropDownMenu.value
    }

    fun toggleCreditDropDownMenu() {
        _toggleCreditDropDownMenu.value = !_toggleCreditDropDownMenu.value
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postClassData(){

        if(scheduleDataCheck()){

//            _classData.value = _classData.value.copy(week=_classData.value.week)
            //전송
            val postClassData = _classData.value.copy(semester = getCurrentSemester(), week = ConvertDayOfWeek.convertDayOfWeek(_classData.value.week))

            Log.d("시간표", postClassData.toString())
            timeTableRepository.postTimeTableData(postClassData){serverResponse ->
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
            val postClassData = _classData.value.copy(week = ConvertDayOfWeek.convertDayOfWeek(_classData.value.week))


            timeTableRepository.postModifyTimeTableData(postClassData){serverResponse ->

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
        _loading.value = true

        timeTableRepository.getScore { serverResponse ->
            // 로딩 상태를 종료하는 부분을 마지막에 한 번만 호출
            _loading.value = false

            // 서버 응답이 null이 아닌 경우 처리
            serverResponse?.let {
                if (it.code == SUCCESS_CODE) {
                    Log.d("가져온 성적", it.toString())

                    _timeTableScore.value = it.data?.copy(average_grade = if (it.data.average_grade == "NaN") "0" else it.data.average_grade)!!
                }
            }
        }
    }

    fun setSelectScoreList(semester: SingleSemesterScore) {
        Log.d("선택된 학기", semester.toString())
        initializeScoreDropDownStates(semester.data_list.size)
        _singleSemesterScore.value=semester
    }

    fun resetSelectScoreList(){
        _singleSemesterScore.value= SingleSemesterScore()
    }

    fun addScoreRow(){
        val emptyScoreList = ClassScore(student_score = null, average_score = null, standard_deviation = null, semester = _singleSemesterScore.value.semester, achievement = "A")
        Log.d("행추가", emptyScoreList.toString())
        Log.d("행추가", _singleSemesterScore.value.toString())
        _singleSemesterScore.value = _singleSemesterScore.value.copy(
            data_list = _singleSemesterScore.value.data_list + emptyScoreList
        )
        Log.d("행추가 결과", _singleSemesterScore.value.toString())
    }

    fun deleteScoreRow(rowIndex:Int){

        val currentSemesterScore=_singleSemesterScore.value.data_list.toMutableList()

        if (rowIndex in currentSemesterScore.indices) {
            currentSemesterScore.removeAt(rowIndex)
        }
        _singleSemesterScore.value = _singleSemesterScore.value.copy(data_list = currentSemesterScore)
    }

    fun deleteSemesterScore(semesterIndex:Int){
        val currentAllSemesterScore=_timeTableScore.value.semester_list.toMutableList()

        if (semesterIndex in currentAllSemesterScore.indices) {
            currentAllSemesterScore.removeAt(semesterIndex)

            val newTimeTableSCore= _timeTableScore.value.copy(semester_list = currentAllSemesterScore)
            postModifyScore(newTimeTableSCore)
        }
    }

    fun toggleScoreCreditDropDownMenuState(index: Int) {
        _scoreCreditDropDownMenuState[index] = !_scoreCreditDropDownMenuState[index]
    }
    fun toggleScoreGradeDownMenuState(index: Int) {
        _scoreGradeDropDownMenuState[index]=!_scoreGradeDropDownMenuState[index]
    }

    fun toggleScoreCategoryDropDownState(index: Int) {
        _scoreCategoryDropDownMenuState[index]=!_scoreCategoryDropDownMenuState[index]
    }

    fun toggleScoreAchievementDropDownState(index: Int) {
        _scoreAchievementDropDownMenuState[index]=!_scoreAchievementDropDownMenuState[index]
    }

    fun updateScoreTitle(index:Int,title:String){
        val updatedDataList = _singleSemesterScore.value.data_list.mapIndexed { scoreIndex, classScore ->
            if (index == scoreIndex) classScore.copy(title = title) else classScore
        }
        _singleSemesterScore.value = _singleSemesterScore.value.copy(data_list = updatedDataList)
    }

    fun updateScoreCredit(index:Int,credit:Int){
        val updatedDataList = _singleSemesterScore.value.data_list.mapIndexed { scoreIndex, classScore ->
            if (index == scoreIndex) classScore.copy(credit = credit) else classScore
        }
        _singleSemesterScore.value = _singleSemesterScore.value.copy(data_list = updatedDataList)
    }

    fun updateScoreGrade(index:Int,grade:Int){
        val updatedDataList = _singleSemesterScore.value.data_list.mapIndexed { scoreIndex, classScore ->
            if (index == scoreIndex) classScore.copy(grade = grade) else classScore
        }
        _singleSemesterScore.value = _singleSemesterScore.value.copy(data_list = updatedDataList)
    }

    fun updateScoreCategory(index:Int,category:String){
        val updatedDataList = _singleSemesterScore.value.data_list.mapIndexed { scoreIndex, classScore ->
            if (index == scoreIndex && category=="공통") {
                classScore.copy(category = category, student_score = null, average_score = null, standard_deviation = null)
            }else if(index==scoreIndex && category=="선택"){
                classScore.copy(category = category, student_score = 0.0, average_score = 0.0, standard_deviation = 0.0)
            } else classScore
        }

        _singleSemesterScore.value = _singleSemesterScore.value.copy(data_list = updatedDataList)
    }

    fun updateScoreAchievement(index:Int,achievement:String){
        val updatedDataList = _singleSemesterScore.value.data_list.mapIndexed { scoreIndex, classScore ->
            if (index == scoreIndex) classScore.copy(achievement = achievement) else classScore
        }
        _singleSemesterScore.value = _singleSemesterScore.value.copy(data_list = updatedDataList)
    }

    fun updateStudentScore(index:Int,studentScore:String){
        val updatedDataList = _singleSemesterScore.value.data_list.mapIndexed { scoreIndex, classScore ->
            if (index == scoreIndex) {
                val score = studentScore.toDoubleOrNull()
                if (score != null) {

                    _modifyScoreErrorState.value = false
                    _modifyScoreErrorMessage.value = StringValue.Empty
                    classScore.copy(student_score = score)
                } else {

                    _modifyScoreErrorState.value = true
                    _modifyScoreErrorMessage.value = StringValue.StringResource(R.string.InvalidDoubleNumber)
                    classScore
                }
            } else classScore
        }
        _singleSemesterScore.value = _singleSemesterScore.value.copy(data_list = updatedDataList)
    }


    fun updateStudentAverageScore(index:Int,averageScore:String){
        val updatedDataList = _singleSemesterScore.value.data_list.mapIndexed { scoreIndex, classScore ->
            if (index == scoreIndex) {
                val score=averageScore.toDoubleOrNull()
                if(score!=null){
                    _modifyScoreErrorState.value = false
                    _modifyScoreErrorMessage.value = StringValue.Empty
                    classScore.copy(average_score = averageScore.toDouble())
                }else{
                    _modifyScoreErrorState.value = true
                    _modifyScoreErrorMessage.value=StringValue.StringResource(R.string.InvalidDoubleNumber)
                    classScore
                }
            } else classScore
        }
        _singleSemesterScore.value = _singleSemesterScore.value.copy(data_list = updatedDataList)
    }

    fun updateStandardDeviation(index:Int,standardDeviation:String){
        val updatedDataList = _singleSemesterScore.value.data_list.mapIndexed { scoreIndex, classScore ->

            if (index == scoreIndex) {
                val score=standardDeviation.toDoubleOrNull()
                if(score!=null && score > 0){
                    _modifyScoreErrorState.value = false
                    _modifyScoreErrorMessage.value = StringValue.Empty
                    classScore.copy(standard_deviation = standardDeviation.toDouble())
                }else{
                    _modifyScoreErrorState.value = true
                    _modifyScoreErrorMessage.value=StringValue.StringResource(R.string.InvalidStandardDeviation)
                    classScore
                }
            } else classScore
        }
        _singleSemesterScore.value = _singleSemesterScore.value.copy(data_list = updatedDataList)
    }

    fun modifyScore(){

        //여기서 임시 변경 데이터를 만들어 서버로 전송하여 결과값에 따라 실제로 값을 바꿀지 말지 결정해야함
        val currentScore = _timeTableScore.value
        val updatedSemesterScore = currentScore.semester_list.map { semesterScore ->
            if (semesterScore.semester == _singleSemesterScore.value.semester) {
                semesterScore.copy(data_list = _singleSemesterScore.value.data_list)
            } else {
                semesterScore
            }
        }

        Log.d("updatedSemesterScore", updatedSemesterScore.toString())
        if (updatedSemesterScore != currentScore.semester_list) {
            postModifyScore(currentScore.copy(semester_list = updatedSemesterScore))
        }
    }

    private fun postModifyScore(newTimeTableSCore:AllScore) {
        _loading.value=true
        val scoreList = newTimeTableSCore.semester_list.flatMap { singleSemesterScore ->
            singleSemesterScore.data_list
        }

        Log.d("scoreList", scoreList.toString())
        //서버로 전송
        timeTableRepository.postUpdateScoreData(PostClassScoreList(scoreList)){serverResponse ->
            if(serverResponse!=null){

                if(serverResponse.code == SUCCESS_CODE){
                    resetScoreModifyToastMessage()
                    getTimeTableScore()

                }else{
                    _scoreModifyResultToastMessage.value=serverResponse.errorDescription
                }
            }
            _loading.value=false
        }

    }

    fun resetScoreModifyToastMessage(){
        _scoreModifyResultToastMessage.value=null
    }


    fun initializeScoreDropDownStates(count: Int) {
        _scoreCreditDropDownMenuState.clear()
        _scoreGradeDropDownMenuState.clear()
        _scoreCategoryDropDownMenuState.clear()
        _scoreAchievementDropDownMenuState.clear()

        repeat(count) {
            _scoreCreditDropDownMenuState.add(false)
            _scoreGradeDropDownMenuState.add(false)
            _scoreCategoryDropDownMenuState.add(false)
            _scoreAchievementDropDownMenuState.add(false)
        }
    }


    fun toggleAddSemesterPopupState(){
        _addScorePopupState.value=!_addScorePopupState.value
    }

    fun postNewScoreTable(year: String, semester: String) {

        val selectSemesterData=TimeFormatter.addYearSemester(year, semester)
        inputSemesterCheck(selectSemesterData)

        if(!inputSemesterCheck(selectSemesterData)){

            //에러 출력
            _addSemesterErrorMessage.value=StringValue.StringResource(R.string.DuplicatedSemester)
            _addSemesterErrorState.value=true
        }else{
            _addSemesterErrorMessage.value=StringValue.Empty
            _addSemesterErrorState.value=false
            _selectSemester.value=selectSemesterData
            addNewSemester()
//            toggleAddSemesterPopupState()

        }

    }

    private fun inputSemesterCheck(selectSemesterData:String):Boolean{
        return !_timeTableScore.value.semester_list.any { singleSemesterScore ->
            singleSemesterScore.semester == selectSemesterData
        }
    }

    private fun addNewSemester() {
        val emptyScore=listOf(ClassScore(student_score = null, average_score = null, standard_deviation = null, semester = _selectSemester.value, achievement = "A"))
        val newSemester = SingleSemesterScore(semester = _selectSemester.value, data_list = emptyScore)

        val newSemesterList = _timeTableScore.value.semester_list + newSemester
        val newTimeTableSCore=_timeTableScore.value.copy(semester_list = newSemesterList)

        Log.d("newTimeTableSCore", newTimeTableSCore.toString())
        postModifyScore(newTimeTableSCore)
    }

}