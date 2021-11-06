package com.cocos.develop.coshub

import android.app.Application
import androidx.room.Room
import com.cocos.develop.coshub.data.datasource.GitHubApi
import com.cocos.develop.coshub.data.di.apiModule
import com.cocos.develop.coshub.data.di.application
import com.cocos.develop.coshub.data.di.ciceroneModule
import com.cocos.develop.coshub.data.di.repoModule
import com.cocos.develop.coshub.data.domain.EventBus
import com.cocos.develop.coshub.data.room.GithubDatabase
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * homework com.cocos.develop.coshub
 *
 * @author Amina
 * 05.10.2021
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@App)
            // declare modules
            modules(application, ciceroneModule, repoModule, apiModule)
        }
    }

}