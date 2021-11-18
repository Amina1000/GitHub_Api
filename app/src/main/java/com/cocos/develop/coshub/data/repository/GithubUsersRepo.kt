package com.cocos.develop.coshub.data.repository

import com.cocos.develop.coshub.data.model.GithubUser
import com.cocos.develop.coshub.data.model.UsersRepository
import com.cocos.develop.coshub.data.room.GithubUserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 14.10.2021
 */
interface GithubUsersRepo {

    fun githubUsers(): Single<List<GithubUser>>
    fun userRepos(repoUrl:String, userId:Int?): Single<List<UsersRepository>>
    fun githubUser(login: String) : Single<GithubUser>
    fun updateCountLike(user: GithubUser): Completable
}