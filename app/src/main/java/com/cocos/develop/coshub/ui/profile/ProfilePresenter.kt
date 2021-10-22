package com.cocos.develop.coshub.ui.profile

import com.cocos.develop.coshub.domain.GithubUsersRepo
import com.cocos.develop.coshub.domain.UsersRepository
import com.cocos.develop.coshub.rx.SchedulerProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
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
        setRepoList()
    }

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    val userRepoList = mutableListOf<UsersRepository>()

    private fun setUser() {
        login?.let {
            currentDisposable.add(usersRepoImpl.githubUser(login)
                .observeOn(schedulerProvider.ui())
                .subscribe {
                    viewState.hideProgressBar()
                    viewState.setUser(it)
                })
        }

    }

    private fun setRepoList(){
        currentDisposable.add(usersRepoImpl.userRepos
            .observeOn(schedulerProvider.ui())
            .subscribe {
                userRepoListIn-> userRepoList.addAll(userRepoListIn)
                viewState.updateList()
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        currentDisposable.clear()
        super.onDestroy()
    }
}