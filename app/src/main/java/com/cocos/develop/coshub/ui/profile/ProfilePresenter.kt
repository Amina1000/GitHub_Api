package com.cocos.develop.coshub.ui.profile

import com.cocos.develop.coshub.domain.GithubUsersRepo
import com.cocos.develop.coshub.rx.SchedulerProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

/**
 * homework com.cocos.develop.coshub.ui.profile
 *
 * @author Amina
 * 05.10.2021
 */
class ProfilePresenter(
    private val login: String?,
    private val usersRepoImpl: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<ProfileView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showProgressBar()
        setUser()
    }

    private var currentDisposable: Disposable? = null
        set(value) {
            field?.takeIf { !it.isDisposed }?.dispose()
            field = value
        }

    private val schedulerProvider: SchedulerProvider = SchedulerProvider()

    private fun setUser() {
        login?.let {
            currentDisposable = usersRepoImpl.githubUser(login)
                .observeOn(schedulerProvider.ui())
                .subscribe {
                    viewState.hideProgressBar()
                    viewState.setUser(it)
                }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        currentDisposable = null
        super.onDestroy()
    }
}