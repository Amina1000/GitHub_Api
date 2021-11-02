package com.cocos.develop.coshub.data.domain

import com.cocos.develop.coshub.data.model.GithubUser

/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 04.10.2021
 */
interface IItemView {
    var pos: Int
}
interface UserItemView: IItemView {
    fun setGitUser(gitHunUser: GithubUser)
    fun imageLoad(url:String?)
}