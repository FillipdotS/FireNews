package com.fillipdots.firenews.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

fun createNotificationChannel(
    name: String,
    descriptionText: String,
    importance: Int,
    channelId: String,
    context: Context
) {
    val channel = NotificationChannel(channelId, name, importance).apply {
        description = descriptionText
    }

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    notificationManager.createNotificationChannel(channel)
}