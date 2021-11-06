package com.cocos.develop.coshub.data.di

import androidx.room.Room
import com.cocos.develop.coshub.data.datasource.GitHubApi
import com.cocos.develop.coshub.data.domain.EventBus
import com.cocos.develop.coshub.data.domain.NetworkStatusImpl
import com.cocos.develop.coshub.data.repository.GithubUserRepoCombinedImpl
import com.cocos.develop.coshub.data.repository.GithubUsersLocalRepoImpl
import com.cocos.develop.coshub.data.repository.GithubUsersRepo
import com.cocos.develop.coshub.data.repository.GithubUsersWebRepoImpl
import com.cocos.develop.coshub.data.room.GithubDatabase
import com.cocos.develop.coshub.rx.SchedulerProvider
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * homework com.cocos.develop.coshub.data.di
 *
 * @author Amina
 * 06.11.2021
 */
const val DB_NAME = "githubDB"
const val BASE_URL = "https://api.github.com"

val application = module {
    single { Room.databaseBuilder(get(), GithubDatabase::class.java, DB_NAME).build() }
    single { EventBus }
}

val ciceroneModule = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
}

val apiModule = module {
    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    single { OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build() }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
            .create(GitHubApi::class.java)
    }
}

val repoModule = module {
    single<GithubUsersRepo> {
        GithubUserRepoCombinedImpl(
            GithubUsersLocalRepoImpl(get()),
            GithubUsersWebRepoImpl(get()),
            NetworkStatusImpl(get()),
            SchedulerProvider()
        )
    }
}
