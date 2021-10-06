package com.cocos.develop.coshub.ui.users

import com.cocos.develop.coshub.ui.common.ProgressView
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
interface UsersView : MvpView, ProgressView {
    fun init()
    fun updateList()
}