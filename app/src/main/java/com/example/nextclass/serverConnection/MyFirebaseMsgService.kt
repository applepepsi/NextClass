package com.example.nextclass.serverConnection

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.nextclass.MainActivity
import com.example.nextclass.R
import com.example.nextclass.utils.GlobalNavigator
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMsgService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle FCM Message
        Log.e(TAG, remoteMessage.from!!)

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.e(TAG, "Message data payload: ${remoteMessage.data}")
            val messageBody=remoteMessage.data["body"]
            val messageTitle=remoteMessage.data["title"]
            val postSequence=remoteMessage.data["sequence"]

            if (postSequence != null) {
                Log.d("postSequence",postSequence)
            }

            showNotification(messageBody,messageTitle,postSequence)
//            handleNow()
        }

        // Check if message contains a notification payload.
//        remoteMessage.notification?.let { notification ->
//            Log.e(TAG, "알림 전체: ${notification.title}")
//            Log.e(TAG, "Message Notification Body: ${notification.body}")
//            showNotification(notification.body,notification.title,"")
//        }
    }

    private fun showNotification(messageBody: String?, messageTitle: String?, postSequence: String?) {


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "NextClass"

        //방법1 인텐트로 보낸다
        val notificationIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("POST_SEQUENCE",postSequence)
        }




        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        Log.e(TAG, "Message Body: $messageBody, $messageTitle")
        // Android O 이상에서는 NotificationChannel이 필요함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        // 알림 생성
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.schedule_icon)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        Log.e(TAG, notificationBuilder.build().toString())
        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun handleNow() {
        Log.d(TAG, "Short lived task is done.")
    }

    /** 새로운 토큰이 생성되는 경우  */
    override fun onNewToken(refreshedToken: String) {
        super.onNewToken(refreshedToken)
        Log.e(TAG, "Refreshed token: $refreshedToken")
        sendRegistrationToServer(refreshedToken)
    }

    private fun sendRegistrationToServer(token: String) {
        Log.e(TAG, "here ! sendRegistrationToServer! token is $token")
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}