package com.a6tak.practice.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.a6tak.practice.CHANNEL_ID
import com.a6tak.practice.R
import com.a6tak.practice.ui.MainActivity

fun notificationBuilder(context: Context, message: String, title: String): NotificationCompat.Builder {
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )


    return NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.img_4)
        .setContentTitle(title)
        .setContentText(message)
//            .setStyle(NotificationCompat.BigTextStyle()
//                .bigText("Much longer text that cannot fit one line..."))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
}