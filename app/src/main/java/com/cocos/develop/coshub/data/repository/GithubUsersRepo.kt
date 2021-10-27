package com.cocos.develop.coshub.data.repository

import com.cocos.develop.coshub.data.domain.AppState
import com.cocos.develop.coshub.data.GithubUser
import com.cocos.develop.coshub.data.UsersRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 14.10.2021
 */
interface GithubUsersRepo {

    fun githubUsers(): Single<List<GithubUser>>
    fun userRepos(repoUrl:String): Single<List<UsersRepository>>
    fun githubUser(login: String) : Single<GithubUser>

}