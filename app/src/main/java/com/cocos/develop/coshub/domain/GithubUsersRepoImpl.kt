package com.cocos.develop.coshub.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 * homework com.cocos.develop.coshub.domain
 *
 * @author Amina
 * 04.10.2021
 */
class GithubUsersRepoImpl:GithubUsersRepo {

    private val usersList = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    private val userRepoList = listOf(
        UsersRepository("movie","movie collections app"),
        UsersRepository("nasa", "nasa api application",1),
        UsersRepository("notes", "user notes application, help to organization your day"),
        UsersRepository("translator", "translator words, from English and Russian",1),
        UsersRepository("github client", "github client , github api application. " +
                "Users hub information, repositories and ratings")
    )
    private val behaviorSubject = BehaviorSubject.createDefault<AppState>(AppState.Success(usersList))

    override val githubUsers: Observable<AppState> = behaviorSubject

    override val userRepos: Single<List<UsersRepository>> = Single.just(userRepoList)

    override fun githubUser(login: String) : Single<GithubUser>{
        return Single.just(GithubUser(login))
    }



}