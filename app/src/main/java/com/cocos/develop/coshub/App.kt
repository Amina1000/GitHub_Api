package com.cocos.develop.coshub

import android.app.Application
import com.cocos.develop.coshub.data.di.AppComponent
import com.cocos.develop.coshub.data.di.AppModule
import com.cocos.develop.coshub.data.di.DaggerAppComponent
import com.facebook.stetho.Stetho

/**
 * homework com.cocos.develop.coshub
 *
 * @author Amina
 * 05.10.2021
 */

class App : Application() {

    val appComponent:AppComponent by lazy{
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

}