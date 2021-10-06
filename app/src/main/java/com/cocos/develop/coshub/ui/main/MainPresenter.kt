package com.cocos.develop.coshub.ui.main

import com.cocos.develop.coshub.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

/**
 * homework com.cocos.develop.coshub.ui.main
 *
 * @author Amina
 * 05.10.2021
 */
class MainPresenter(private val router: Router, private val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}