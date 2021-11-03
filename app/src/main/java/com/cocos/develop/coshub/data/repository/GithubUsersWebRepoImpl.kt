package com.cocos.develop.coshub.data.repository

import com.cocos.develop.coshub.data.model.GithubUser
import com.cocos.develop.coshub.data.model.UsersRepository
import com.cocos.develop.coshub.data.datasource.GitHubApi
import com.cocos.develop.coshub.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 04.10.2021
 */

class GithubUsersWebRepoImpl(
    private val githubApi: GitHubApi

) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> =
        githubApi.getGitHubUsers()

    override fun userRepos(repoUrl: String, userId:Int?): Single<List<UsersRepository>> =
        githubApi.getUserRepos(repoUrl)

    override fun githubUser(login: String): Single<GithubUser> =
        githubApi.getUserByLogin(login)

}