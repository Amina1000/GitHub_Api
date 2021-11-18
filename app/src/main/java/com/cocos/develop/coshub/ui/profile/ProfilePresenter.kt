package com.cocos.develop.coshub.ui.profile

import com.cocos.develop.coshub.data.domain.EventBus
import com.cocos.develop.coshub.data.domain.MinusLikeEvent
import com.cocos.develop.coshub.data.domain.PlusLikeEvent
import com.cocos.develop.coshub.data.model.GithubUser
import com.cocos.develop.coshub.data.model.UsersRepository
import com.cocos.develop.coshub.data.repository.GithubUsersRepo
import com.cocos.develop.coshub.rx.SchedulerProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

/**
 * homework com.cocos.develop.coshub.ui.profile
 *
 * @author Amina
 * 05.10.2021
 */
class ProfilePresenter(
    private val githubUser: GithubUser?
) : MvpPresenter<ProfileView>() {

    @Inject
    lateinit var router:Router
    @Inject
    lateinit var eventBus :EventBus

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showProgressBar()
        setUser()
        setRepoList()
    }

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    @Inject
    lateinit var usersRepoImpl : GithubUsersRepo
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
            currentDisposable.add(usersRepoImpl.userRepos(it, githubUser.id)
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
        githubUser?.let{
            it.countLike = total
            currentDisposable.add(
            usersRepoImpl.updateCountLike(it)
                .observeOn(schedulerProvider.ui())
                .subscribe()
            )
        }

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