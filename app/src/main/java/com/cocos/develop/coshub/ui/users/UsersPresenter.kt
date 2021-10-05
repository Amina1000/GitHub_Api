package com.cocos.develop.coshub.ui.users

import com.cocos.develop.coshub.AndroidScreens
import com.cocos.develop.coshub.domain.GithubUser
import com.cocos.develop.coshub.domain.GithubUsersRepo
import com.cocos.develop.coshub.domain.UserItemView
import com.cocos.develop.coshub.domain.UserListPresenter
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

/**
 * homework com.cocos.develop.coshub.ui.users
 *
 * @author Amina
 * 05.10.2021
 */
class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) : MvpPresenter<UsersView>() {

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens().profile(users[itemView.pos].login))
        }
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}