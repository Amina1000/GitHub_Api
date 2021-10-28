package com.cocos.develop.coshub.data.repository

import com.cocos.develop.coshub.data.GithubUser
import com.cocos.develop.coshub.data.UsersRepository
import com.cocos.develop.coshub.data.datasource.GitHubApi
import com.cocos.develop.coshub.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 04.10.2021
 */

class GithubUsersRepoImpl(
    private val githubApi: GitHubApi,
    private val schedulerProvider: SchedulerProvider
) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> =
        githubApi.getGitHubUsers().subscribeOn(schedulerProvider.io())

    override fun userRepos(repoUrl: String): Single<List<UsersRepository>> =
        githubApi.getUserRepos(repoUrl).subscribeOn(schedulerProvider.io())

    override fun githubUser(login: String): Single<GithubUser> =
        githubApi.getUserByLogin(login).subscribeOn(schedulerProvider.io())

}