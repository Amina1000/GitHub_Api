package com.cocos.develop.coshub.domain

/**
 * homework com.cocos.develop.coshub.domain
 *
 * @author Amina
 * 04.10.2021
 */
interface IItemView {
    var pos: Int
}
interface UserItemView: IItemView {
    fun setLogin(text: String)
}