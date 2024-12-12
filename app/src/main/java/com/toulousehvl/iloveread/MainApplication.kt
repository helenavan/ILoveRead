package com.toulousehvl.iloveread

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    companion object {
        lateinit var instance: MainApplication
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        //TODO firebase init
    }

}