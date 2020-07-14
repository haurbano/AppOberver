package com.haurbano.applistener

interface AppEndObserver {
   fun registerObserver(listener: OnAppEndsListener)
   fun registerObserver(listener: () -> Unit)
}