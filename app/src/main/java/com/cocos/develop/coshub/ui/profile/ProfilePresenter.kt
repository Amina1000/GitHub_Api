package com.cocos.develop.coshub.ui.profile

import com.cocos.develop.coshub.App
import com.cocos.develop.coshub.domain.MinusLikeEvent
import com.cocos.develop.coshub.domain.PlusLikeEvent
import com.cocos.develop.coshub.domain.UsersRepository
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
    private val login: String?,
    app: App
) : MvpPresenter<ProfileView>() {

    private val usersRepoImpl = app.usersRepo
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
    val userRepoList = mutableListOf<UsersRepository>()


    private fun setUser() {
        login?.let {
            currentDisposable.add(usersRepoImpl.githubUser(login)
                .observeOn(schedulerProvider.ui())
                .subscribe {gitUser->
                    viewState.hideProgressBar()
                    viewState.setUser(gitUser)
                })
        }

    }

    private fun setRepoList() {
        currentDisposable.add(usersRepoImpl.userRepos
            .observeOn(schedulerProvider.ui())
            .subscribe { userRepoListIn ->
                userRepoList.addAll(userRepoListIn)
                viewState.updateList()
            })
    }

    fun onLikeClick(likeCounter: Int) {
        if (likeCounter == 1) {
            eventBus.post(PlusLikeEvent())
        } else {
            eventBus.post(MinusLikeEvent())
        }
        viewState.setCountLike()
    }

    fun setLikeCount(count:Int): Int {
        var total = 0
        currentDisposable.add(eventBus.get()
            .subscribe {
            if (it is PlusLikeEvent) {
                total = count +1
            } else if (it is MinusLikeEvent) {
                if (count > 0) {
                    total = count -1
                }
            }
        })
        return total
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