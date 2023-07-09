package com.example.diagntest.di.component

import com.example.diagntest.MyApp
import com.example.diagntest.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MyApp)
}