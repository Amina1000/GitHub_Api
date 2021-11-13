package com.cocos.develop.coshub.ui.main

import com.cocos.develop.coshub.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

/**
 * homework com.cocos.develop.coshub.ui.main
 *
 * @author Amina
 * 05.10.2021
 */
class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}