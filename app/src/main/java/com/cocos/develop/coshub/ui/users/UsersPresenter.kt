package com.cocos.develop.coshub.ui.users

import com.cocos.develop.coshub.AndroidScreens
import com.cocos.develop.coshub.App
import com.cocos.develop.coshub.data.GithubUser
import com.cocos.develop.coshub.data.domain.AppState
import com.cocos.develop.coshub.data.domain.UserItemView
import com.cocos.develop.coshub.data.domain.UserListPresenter
import com.cocos.develop.coshub.data.repository.GithubUsersRepoImpl
import com.cocos.develop.coshub.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

/**
 * homework com.cocos.develop.coshub.ui.users
 *
 * @author Amina
 * 05.10.2021
 */
class UsersPresenter(app: App) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.let {
                view.setGitUser(it)
            }
        }
    }

    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepo = GithubUsersRepoImpl(app.api, schedulerProvider)
    private val router = app.router

    val usersListPresenter = UsersListPresenter()
    private var currentDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showProgressBar()
        loadData()
    }

    private fun loadData() {
        currentDisposable.add(usersRepo.githubUsers()
            .observeOn(schedulerProvider.ui())
            .subscribe(
                {userList-> renderData(AppState.Success(userList))},
                {error-> renderData(AppState.Error(error))}
            ))
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.data.let { dataUsers ->
                    usersListPresenter.users.addAll(dataUsers)
                    viewState.hideProgressBar()
                    usersListPresenter.itemClickListener = { itemView ->
                        dataUsers[itemView.pos].let {
                            router.navigateTo(AndroidScreens().profile(it))
                        }
                    }
                    viewState.updateList()
                }
            }
            is AppState.Loading -> {
                viewState.showProgressBar()
            }
            is AppState.Error -> {
                viewState.showErrorMessage(appState.error.message)
            }
        }
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        currentDisposable.dispose()
        super.onDestroy()
    }

}