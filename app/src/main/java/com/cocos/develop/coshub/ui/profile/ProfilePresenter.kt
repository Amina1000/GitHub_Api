package com.cocos.develop.coshub.ui.profile

import com.cocos.develop.coshub.domain.GithubUsersRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

/**
 * homework com.cocos.develop.coshub.ui.profile
 *
 * @author Amina
 * 05.10.2021
 */
class ProfilePresenter(
    private val login: String?,
    private val usersRepo: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<ProfileView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showProgressBar()
        setUser()
    }

    private fun setUser() {
        login?.let {
            viewState.setUser(usersRepo.getUser(login))
            viewState.hideProgressBar()
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}