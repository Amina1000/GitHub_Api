package com.cocos.develop.coshub.domain

import io.reactivex.rxjava3.core.Observable

/**
 * homework com.cocos.develop.coshub.domain
 *
 * @author Amina
 * 14.10.2021
 */
interface GithubUsersRepo {

    val githubUsers: Observable<AppState>
    fun githubUser(login: String) : Observable<GithubUser>

}