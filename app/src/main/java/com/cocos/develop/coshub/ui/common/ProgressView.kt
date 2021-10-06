package com.cocos.develop.coshub.ui.common

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * homework com.cocos.develop.coshub.ui.common
 *
 * @author Amina
 * 06.10.2021
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface ProgressView {
    /**
     * Показывает прогрессбар
     */
    fun showProgressBar()

    /**
     * Скрывает прогрессбар
     */
    fun hideProgressBar()
}