package com.cocos.develop.coshub.data.repository

import com.cocos.develop.coshub.data.model.GithubUser
import com.cocos.develop.coshub.data.model.UsersRepository
import com.cocos.develop.coshub.data.room.GithubDatabase
import com.cocos.develop.coshub.ui.utils.githubUserListMap
import com.cocos.develop.coshub.ui.utils.githubUserMap
import com.cocos.develop.coshub.ui.utils.usersReposMap
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.data.repository
 *
 * @author Amina
 * 02.11.2021
 */
class GithubUsersLocalRepoImpl(private val db: GithubDatabase) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> {
        return db.userDao.getAll().map {
            githubUserListMap(it)
        }
    }

    override fun userRepos(repoUrl: String): Single<List<UsersRepository>> {
        return db.repositoryDao.getAll().map {
            usersReposMap(it)
        }
    }


    override fun githubUser(login: String): Single<GithubUser> {
        return db.userDao.findByLogin(login).map{
            githubUserMap(it)
        }
    }
}