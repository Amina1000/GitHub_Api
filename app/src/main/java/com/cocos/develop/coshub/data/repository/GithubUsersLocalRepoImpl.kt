package com.cocos.develop.coshub.data.repository

import com.cocos.develop.coshub.data.model.GithubUser
import com.cocos.develop.coshub.data.model.UsersRepository
import com.cocos.develop.coshub.data.room.GithubDatabase
import com.cocos.develop.coshub.data.room.GithubRepositoryEntity
import com.cocos.develop.coshub.data.room.GithubUserEntity
import com.cocos.develop.coshub.ui.utils.githubUserListMap
import com.cocos.develop.coshub.ui.utils.githubUserMap
import com.cocos.develop.coshub.ui.utils.usersReposMap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.data.repository
 *
 * @author Amina
 * 02.11.2021
 */
class GithubUsersLocalRepoImpl(db: GithubDatabase) : GithubLocalRepo {

    private val userDao = db.userDao
    private val repositoryDao = db.repositoryDao

    override fun githubUsers(): Single<List<GithubUser>> {
        return userDao.getAll().map {
            githubUserListMap(it)
        }
    }

    override fun userRepos(repoUrl: String): Single<List<UsersRepository>> {
        return repositoryDao.getAll().map {
            usersReposMap(it)
        }
    }

    override fun githubUser(login: String): Single<GithubUser> {
        return userDao.findByLogin(login).map{
            githubUserMap(it)
        }
    }

    override fun putGithubUser(users: List<GithubUserEntity>): Completable {
       return userDao.insert(users)
    }

    override fun putGithubUser(user: GithubUserEntity): Completable {
        return userDao.insert(user)
    }

    override fun putGithubRepos(repos: List<GithubRepositoryEntity>): Completable {
        return repositoryDao.insert(repos)
    }

}