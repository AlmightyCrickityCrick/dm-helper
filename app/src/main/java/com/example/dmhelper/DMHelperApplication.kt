package com.example.dmhelper

import android.app.Application
import com.example.dmhelper.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DMHelperApplication: Application(){
        override fun onCreate() {
            super.onCreate()
            startKoin {
                androidContext(this@DMHelperApplication)
                modules(appModule)
            }
        }
}