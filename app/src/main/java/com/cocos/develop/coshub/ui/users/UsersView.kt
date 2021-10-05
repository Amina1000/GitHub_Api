package com.cocos.develop.coshub.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * homework com.cocos.develop.coshub.ui.users
 *
 * @author Amina
 * 05.10.2021
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun init()
    fun updateList()
}