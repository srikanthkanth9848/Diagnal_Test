package com.example.diagntest

import android.app.Application
import com.example.diagntest.di.component.AppComponent
import com.example.diagntest.di.component.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
            private set
    }

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        initComponent()
    }

    private fun initComponent() {
        appComponent = DaggerAppComponent.builder().build()
//        appComponent = DaggerAppComponent.builder()
//            .build()
        appComponent.inject(instance)
    }
}