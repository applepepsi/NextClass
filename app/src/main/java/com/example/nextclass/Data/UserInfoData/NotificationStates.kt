package com.example.nextclass.Data.UserInfoData

data class NotificationStates(
    val community: NotificationConfig = NotificationConfig(category = "comment_notification"),
    val schedule: NotificationConfig = NotificationConfig(category = "to_do_list_notification")
)
