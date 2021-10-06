package com.cocos.develop.coshub.ui.profile

import com.cocos.develop.coshub.domain.GithubUser
import com.cocos.develop.coshub.ui.common.ProgressView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * homework com.cocos.develop.coshub.ui.profile
 *
 * @author Amina
 * 05.10.2021
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileView: MvpView, ProgressView {
    fun setUser(user: GithubUser)
}