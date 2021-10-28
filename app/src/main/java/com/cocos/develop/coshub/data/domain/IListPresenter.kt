package com.cocos.develop.coshub.data.domain

/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 04.10.2021
 */
interface ListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface UserListPresenter : ListPresenter<UserItemView>