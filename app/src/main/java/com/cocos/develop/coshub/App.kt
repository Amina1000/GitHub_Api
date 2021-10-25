package com.cocos.develop.coshub

import android.app.Application
import com.cocos.develop.coshub.domain.EventBus
import com.cocos.develop.coshub.domain.GithubUsersRepoImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

/**
 * homework com.cocos.develop.coshub
 *
 * @author Amina
 * 05.10.2021
 */
class App:Application() {

    //Временно до даггера положим это тут
    // навигация
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    val eventBus = EventBus
    // репозиторий
    val usersRepo = GithubUsersRepoImpl()

}