package com.haurbano.applistener

interface AppEndObserver {
   fun addOnApplicationFinishedListener(listener: OnAppEndsListener)
   fun addOnApplicationFinishedListener(listener: () -> Unit)
}