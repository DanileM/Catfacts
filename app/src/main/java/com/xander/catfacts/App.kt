package com.xander.catfacts

import android.app.Application
import android.content.Context
import com.xander.catfacts.di.appModule
import com.xander.catfacts.di.viewModelModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
        startKoin(this, listOf(appModule, viewModelModule))
    }

    companion object {
        private var instance: App? = null

        fun getInstance(): Context {
            return instance!!.applicationContext
        }
    }

}