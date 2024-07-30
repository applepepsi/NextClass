package com.example.nextclass.serverConnection

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle FCM Message
        Log.e(TAG, remoteMessage.from!!)

        // Check if message contains a data payload.
        if (remoteMessage.data.size > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.data)
            handleNow()
        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.e(
                TAG, "Message Notification Body: " + remoteMessage.notification!!
                    .body
            )
            val getMessage = remoteMessage.notification!!.body
            if (TextUtils.isEmpty(getMessage)) {
                Log.e(TAG, "ERR: Message data is empty...")
            } else {
                val mapMessage: MutableMap<String, String?> = HashMap()
                assert(getMessage != null)
                mapMessage["key"] = getMessage

                // Broadcast Data Sending Test
                val intent = Intent("alert_data")
                intent.putExtra("msg", getMessage)
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            }
        }
    }

    private fun handleNow() {
        Log.d(TAG, "Short lived task is done.")
    }

    /** 새로운 토큰이 생성되는 경우  */
    override fun onNewToken(refreshedToken: String) {
        super.onNewToken(refreshedToken)
        Log.e(
            TAG,
            "Refreshed token: $refreshedToken"
        )
        sendRegistrationToServer(refreshedToken)
    }

    private fun sendRegistrationToServer(token: String) {
        Log.e(
            TAG,
            "here ! sendRegistrationToServer! token is $token"
        )
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}