package com.seventhgroup.petcare.activity

import android.content.BroadcastReceiver
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.seventhgroup.petcare.R


class Notification : BroadcastReceiver(){
    val notificationID = 1
    val channelID = "channel1"
    val titleExtra = "Yay"
    val messageExtra = "Your pet food has been eaten"

    override fun onReceive(context: Context, intent: Intent)
    {
        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titleExtra)
            .setContentText(messageExtra)
            .build()

        val  manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)
    }



}