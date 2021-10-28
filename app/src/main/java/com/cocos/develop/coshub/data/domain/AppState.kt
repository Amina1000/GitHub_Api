package com.cocos.develop.coshub.data.domain

import com.cocos.develop.coshub.data.GithubUser

/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 14.10.2021
 */
sealed class AppState {

    data class Success(val data:List<GithubUser>) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
