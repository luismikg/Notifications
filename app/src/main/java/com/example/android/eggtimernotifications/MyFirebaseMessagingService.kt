package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService :FirebaseMessagingService() {

    private val tag = "TOKEN"
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(tag, "from: ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.d(tag, "Messge data payload: : ${remoteMessage.data}")
        }

        remoteMessage?.notification?.let {
            Log.d(tag, "Message notification Body: : ${it.body}")
            sendNotification(it.body!!)
        }
    }

    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)
    }

    override fun onNewToken(token: String?) {
        Log.d(tag, "Refreshed token: $token")
        //sendRegistrationToServer(token)
    }
}