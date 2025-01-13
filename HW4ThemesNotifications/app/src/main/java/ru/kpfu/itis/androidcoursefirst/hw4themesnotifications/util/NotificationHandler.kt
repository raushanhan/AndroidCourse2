package ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ru.kpfu.itis.androidcoursefirst.hw4themesnotifications.R

class NotificationHandler(
    private val appCtx: Context
) {

    private val notificationManager =
        appCtx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

//    fun showNotification(
//        channelId: String,
//        notificationId: Int,
//        notificationText: String,
//        notificationTitle: String,
//        notificationImportance: String
//    ) {
//        val builder = NotificationCompat.Builder(appCtx, channelId)
//            .setSmallIcon(R.drawable.baseline_railway_alert_24)
//            .setContentTitle(notificationTitle)
//            .setContentText(notificationText)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            println("TEST TAG - in handler 1")
//            val channel = NotificationChannel(
//                channelId, "name", NotificationManager.IMPORTANCE_HIGH
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        println("TEST TAG - in handler 2")
//        notificationManager.notify(notificationId, builder.build())
//    }

    fun showNotification(
        channelId: String,
        notificationId: Int,
        notificationText: String,
        notificationTitle: String,
        notificationImportance: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = when (notificationImportance) {
                "High" -> NotificationManager.IMPORTANCE_HIGH
                "Default" -> NotificationManager.IMPORTANCE_DEFAULT
                "Low" -> NotificationManager.IMPORTANCE_LOW
                else -> NotificationManager.IMPORTANCE_MAX
            }

            val channel = NotificationChannel(
                channelId,
                "Sample Channel Name",
                importance
            )
            channel.description = "Channel for sample notifications"
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(appCtx, channelId)
            .setSmallIcon(R.drawable.baseline_railway_alert_24)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(notificationId, builder.build())
    }
}