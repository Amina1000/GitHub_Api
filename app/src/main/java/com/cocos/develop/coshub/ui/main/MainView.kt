package com.cocos.develop.coshub.ui.main

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * homework com.cocos.develop.coshub.ui.main
 *
 * @author Amina
 * 05.10.2021
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView