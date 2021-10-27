package com.cocos.develop.coshub.data.datasource

import com.cocos.develop.coshub.data.GithubUser
import com.cocos.develop.coshub.data.UsersRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * homework com.cocos.develop.coshub.data.datasource
 *
 * @author Amina
 * 25.10.2021
 */
interface GitHubApi {

    @GET("/users")
    fun getGitHubUsers(): Single<List<GithubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(
        @Path("username") login: String
    ): Single<GithubUser>

    @GET
    fun getUserRepos(@Url urlRepository: String): Single<List<UsersRepository>>
}