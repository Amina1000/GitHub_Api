package com.cocos.develop.coshub.ui.utils

import android.content.Context
import android.widget.Toast
import com.cocos.develop.coshub.App
import com.cocos.develop.coshub.data.model.GithubUser
import com.cocos.develop.coshub.data.model.UsersRepository
import com.cocos.develop.coshub.data.room.GithubRepositoryEntity
import com.cocos.develop.coshub.data.room.GithubUserEntity

/**
 * homework com.cocos.develop.coshub.ui.utils
 *
 * @author Amina
 * 14.10.2021
 */

fun errorMessage(context:Context?, message:String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}

val Context.app: App
    get() {
        return  applicationContext as App
    }

fun githubUserListMap(users: List<GithubUserEntity>) =
    users.map {
        githubUserMap(it)
    }

fun githubUserMap(githubUser: GithubUserEntity) = GithubUser(
    githubUser.avatarUrl,
    githubUser.id,
    githubUser.login,
    githubUser.organizationsUrl,
    githubUser.reposUrl,
    githubUser.like
)

fun usersReposMap(repos: List<GithubRepositoryEntity>) =
    repos.map {
        UsersRepository(
          it.htmlUrl,
            it.id,
            it.name,
            it.description,
            it.likeCounter,
        )
    }