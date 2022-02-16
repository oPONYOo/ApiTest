package com.example.apitest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Test_App: Application() {
    companion object{
        lateinit var instance: Test_App
        private set
    }
}