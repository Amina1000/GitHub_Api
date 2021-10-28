package com.cocos.develop.coshub.ui.profile

import com.cocos.develop.coshub.App
import com.cocos.develop.coshub.data.GithubUser
import com.cocos.develop.coshub.data.domain.MinusLikeEvent
import com.cocos.develop.coshub.data.domain.PlusLikeEvent
import com.cocos.develop.coshub.data.UsersRepository
import com.cocos.develop.coshub.data.domain.AppState
import com.cocos.develop.coshub.data.repository.GithubUsersRepoImpl
import com.cocos.develop.coshub.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

/**
 * homework com.cocos.develop.coshub.ui.profile
 *
 * @author Amina
 * 05.10.2021
 */
class ProfilePresenter(
    private val githubUser: GithubUser?,
    app: App
) : MvpPresenter<ProfileView>() {

    private val router = app.router
    private val eventBus = app.eventBus

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showProgressBar()
        setUser()
        setRepoList()
    }

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepoImpl = GithubUsersRepoImpl(app.api, schedulerProvider)
    val userRepoList = mutableListOf<UsersRepository>()

    private fun setUser() {
        githubUser?.login?.let {
            currentDisposable.add(
                usersRepoImpl.githubUser(it)
                    .observeOn(schedulerProvider.ui())
                    .subscribe(
                        { gitUser -> viewState.setUser(gitUser) },
                        { error -> viewState.showErrorMessage(error.message) })
            )
        }

    }

    private fun setRepoList() {
        githubUser?.reposUrl?.let {
            currentDisposable.add(usersRepoImpl.userRepos(it)
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { userRepoListIn ->
                        viewState.hideProgressBar()
                        userRepoList.addAll(userRepoListIn)
                        viewState.updateList()
                    },
                    { error -> viewState.showErrorMessage(error.message) }
                ))
        }

    }

    fun onLikeClick(likeCounter: Int) {
        if (likeCounter == 1) {
            eventBus.post(PlusLikeEvent())
        } else {
            eventBus.post(MinusLikeEvent())
        }
        viewState.setCountLike()
    }

    fun setLikeCount(count: Int): Int {
        var total = 0
        currentDisposable.add(eventBus.get()
            .subscribe {
                if (it is PlusLikeEvent) {
                    total = count + 1
                } else if (it is MinusLikeEvent) {
                    if (count > 0) {
                        total = count - 1
                    }
                }
            })
        return total
    }

    fun openUserRepo(repoUrl:String?){
        viewState.openUserRepo(repoUrl)
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