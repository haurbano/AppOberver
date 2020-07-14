package com.haurbano.applifecyclelistener

import android.app.Application
import android.util.Log
import com.haurbano.applistener.AppObserver

class ExampleApplication: Application() {
    private val appListener = AppObserver(this)
    override fun onCreate() {
        super.onCreate()
        appListener.registerObserver {
            Log.e("--haur","App finished")
        }
    }
}