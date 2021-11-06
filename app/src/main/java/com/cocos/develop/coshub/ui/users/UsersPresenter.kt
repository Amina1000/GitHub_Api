package com.cocos.develop.coshub.ui.users

import com.cocos.develop.coshub.AndroidScreens
import com.cocos.develop.coshub.App
import com.cocos.develop.coshub.data.domain.AppState
import com.cocos.develop.coshub.data.domain.UserItemView
import com.cocos.develop.coshub.data.domain.UserListPresenter
import com.cocos.develop.coshub.data.model.GithubUser
import com.cocos.develop.coshub.data.repository.GithubUsersRepo
import com.cocos.develop.coshub.rx.SchedulerProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import org.koin.java.KoinJavaComponent.inject

/**
 * homework com.cocos.develop.coshub.ui.users
 *
 * @author Amina
 * 05.10.2021
 */
class UsersPresenter() :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.let {
                view.setGitUser(it)
                view.imageLoad(user.avatarUrl)
            }
        }
    }

    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepo : GithubUsersRepo by inject(GithubUsersRepo::class.java)
    private val router: Router by inject(Router::class.java)

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