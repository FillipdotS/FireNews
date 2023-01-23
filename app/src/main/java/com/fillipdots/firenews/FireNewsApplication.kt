package com.fillipdots.firenews

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FireNewsApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        Log.i("WorkManager", "Called overridden work manager configuration")

        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}