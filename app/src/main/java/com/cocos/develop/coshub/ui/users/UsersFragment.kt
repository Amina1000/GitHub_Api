package com.cocos.develop.coshub.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cocos.develop.coshub.App
import com.cocos.develop.coshub.databinding.FragmentUsersBinding
import com.cocos.develop.coshub.domain.GithubUsersRepo
import com.cocos.develop.coshub.ui.main.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), App.instance.router) }
    private var adapter: UsersAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}