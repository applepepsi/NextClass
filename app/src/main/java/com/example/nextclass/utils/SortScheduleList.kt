package com.example.nextclass.utils

import com.example.nextclass.Data.ScheduleData.ScheduleData

object SortScheduleList {



    fun sortByAscendingAlarmTime(scheduleDataList: List<ScheduleData>): List<ScheduleData> {
        return scheduleDataList.sortedBy {
            it.alarm_time
        }
    }


    fun sortByDescendingAlarmTime(scheduleDataList: List<ScheduleData>): List<ScheduleData> {
        return scheduleDataList.sortedByDescending {
            it.alarm_time
        }
    }
}


