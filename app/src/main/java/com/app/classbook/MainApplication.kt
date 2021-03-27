package com.app.classbook

import android.app.Application
import com.app.classbook.util.AppPreference

val SharedPreference: AppPreference by lazy {
    MainApplication.sharedPreference
}
class MainApplication : Application() {
    companion object {
        lateinit var sharedPreference: AppPreference
    }
    override fun onCreate() {
        super.onCreate()
        sharedPreference = AppPreference(this)
    }
}