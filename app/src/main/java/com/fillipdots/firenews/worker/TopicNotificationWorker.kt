package com.fillipdots.firenews.worker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import androidx.hilt.work.HiltWorker
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.fillipdots.firenews.R
import com.fillipdots.firenews.data.repository.NewsRepository
import com.fillipdots.firenews.data.repository.TopicNewsRepository
import com.fillipdots.firenews.data.repository.TopicRepository
import com.fillipdots.firenews.domain.model.Article
import com.fillipdots.firenews.domain.model.Topic
import com.fillipdots.firenews.domain.model.TopicNews
import com.fillipdots.firenews.ui.MainActivity
import com.fillipdots.firenews.util.TOPIC_NOTIFICATION_CHANNEL_ID
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlin.random.Random

@HiltWorker
class TopicNotificationWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val topicRepository: TopicRepository,
    private val newsRepository: NewsRepository,
    private val topicNewsRepository: TopicNewsRepository,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        Log.i(LOG_TAG, "Starting doWork")

        if (topicNewsRepository.getLatestTopicNews() != null && !topicNewsRepository.getLatestTopicNews()!!.hasBeenViewed) {
            Log.i(LOG_TAG, "Current TN hasBeenViewed: ${topicNewsRepository.getLatestTopicNews()!!.hasBeenViewed}")

            Log.i(LOG_TAG, "Previous topic news haven't been viewed, aborting")
            return Result.success()
        }

        val topArticles = newsRepository.getTopArticles(100)
        val userTopics = topicRepository.topics.first()

        val result = mutableMapOf<String, MutableList<Article>>()

        for (article: Article in topArticles) {
            for (topic: Topic in userTopics) {
                val match = article.title.contains(topic.name, ignoreCase = true)

                if (match) {
                    if (!result.containsKey(topic.name)) {
                        result[topic.name] = mutableListOf(article)
                    } else {
                        result[topic.name]?.add(article)
                    }
                }
            }
        }

        if (result.isNotEmpty()) {
            val topicNews = TopicNews(hasBeenViewed = false, articleMap = result)

            topicNewsRepository.update(topicNews)

            val foundTopicsWithNotification = userTopics.filter { topic ->
                topic.notificationsOn && result.containsKey(topic.name)
            }
            var topicsStringList = if (foundTopicsWithNotification.size > 3) {
                foundTopicsWithNotification.subList(0, 2).toString()
            } else {
                foundTopicsWithNotification.toString()
            }
            topicsStringList = topicsStringList.substring(1, topicsStringList.length - 1)

            if (foundTopicsWithNotification.isNotEmpty()) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    "com.fillipdots.firenews/topic_news".toUri(),
                    context,
                    MainActivity::class.java
                )

                val pendingIntent = TaskStackBuilder.create(context).run {
                    addNextIntentWithParentStack(intent)
                    getPendingIntent(123, PendingIntent.FLAG_IMMUTABLE)
                }

                val builder = NotificationCompat.Builder(context, TOPIC_NOTIFICATION_CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.fire_emoji)
                    .setContentTitle(context.getString(R.string.notification_topic_title))
                    .setContentText(context.getString(
                        R.string.notification_topic_content,
                        topicsStringList,
                        if (foundTopicsWithNotification.size > 3) context.getString(R.string.notification_topic_andmore) else ""
                    ))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)

                with(NotificationManagerCompat.from(context)) {
                    notify(Random.nextInt(), builder.build())
                }
            }

            // Intent
        } else {
            Log.i(LOG_TAG, "There were no results, not updating firestore")
        }

        Log.i(LOG_TAG, "Finishing doWork")

        return Result.success()
    }
}

private const val LOG_TAG = "TopicNotificationWorker"