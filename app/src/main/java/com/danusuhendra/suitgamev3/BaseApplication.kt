package com.danusuhendra.suitgamev3

import android.app.Application
import com.danusuhendra.suitgamev3.di.AppComponent
import com.danusuhendra.suitgamev3.di.DaggerAppComponent

class BaseApplication : Application() {
    private lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    fun getComponent() : AppComponent {
        return appComponent
    }
}