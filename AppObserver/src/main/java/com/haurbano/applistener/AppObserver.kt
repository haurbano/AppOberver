package com.haurbano.applistener

import android.app.Activity
import android.app.Application
import android.os.Bundle

class AppObserver(app: Application): AppEndObserver {

    private val activitiesHash = HashSet<Int>()
    private val listeners = mutableListOf<OnAppEndsListener>()

    init {
        app.registerActivityLifecycleCallbacks(object: Application.ActivityLifecycleCallbacks{
            override fun onActivityPaused(p0: Activity) {}

            override fun onActivityStarted(p0: Activity) {}

            override fun onActivityDestroyed(activity: Activity) {
                activitiesHash.remove(activity.hashCode())
                if (activitiesHash.isEmpty()) notifyListener()
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

            override fun onActivityStopped(p0: Activity) {}

            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                activitiesHash.add(activity.hashCode())
            }

            override fun onActivityResumed(p0: Activity) {}
        })
    }

    private fun notifyListener() {
        for (listener in listeners) {
            listener.appFinished()
        }
    }

    override fun registerObserver(listener: OnAppEndsListener) {
        listeners.add(listener)
    }

    override fun registerObserver(listener: () -> Unit) {
        listeners.add(object : OnAppEndsListener {
            override fun appFinished() {
                listener()
            }
        })
    }

}