package com.cocos.develop.coshub.data.repository

import com.cocos.develop.coshub.data.model.GithubUser
import com.cocos.develop.coshub.data.model.UsersRepository
import com.cocos.develop.coshub.data.room.GithubRepositoryEntity
import com.cocos.develop.coshub.data.room.GithubUserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.data.repository
 *
 * @author Amina
 * 03.11.2021
 */
interface GithubLocalRepo {

    fun githubUsers(): Single<List<GithubUser>>
    fun userRepos(repoUrl:String): Single<List<UsersRepository>>
    fun githubUser(login: String) : Single<GithubUser>
    fun putGithubUser(users: List<GithubUserEntity>): Completable
    fun putGithubUser(user: GithubUserEntity): Completable
    fun putGithubRepos (repos:List<GithubRepositoryEntity>): Completable
    fun putCountLike(countLike: Int, id:Int?): Completable

}