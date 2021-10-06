package com.cocos.develop.coshub.domain

/**
 * homework com.cocos.develop.coshub.domain
 *
 * @author Amina
 * 04.10.2021
 */
class GithubUsersRepo {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() : List<GithubUser> {
        return repositories
    }

    fun getUser(login: String) : GithubUser{
        return GithubUser(login)
    }

}