package com.cocos.develop.coshub.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.domain
 *
 * @author Amina
 * 14.10.2021
 */
interface GithubUsersRepo {

    val githubUsers: Observable<AppState>
    val userRepos: Single<List<UsersRepository>>
    fun githubUser(login: String) : Observable<GithubUser>

}