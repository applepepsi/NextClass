package com.example.nextclass.utils

import com.example.nextclass.Data.ScheduleData

object SortScheduleList {

    fun sortByAscendingCreationTime(scheduleDataList: List<ScheduleData>): List<ScheduleData> {
        return scheduleDataList.sortedBy {
            it.create_time
        }
    }

    fun sortByDescendingCreationTime(scheduleDataList: List<ScheduleData>): List<ScheduleData> {
        return scheduleDataList.sortedByDescending {
            it.create_time
        }
    }

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


