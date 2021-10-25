package com.cocos.develop.coshub.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.coshub.R
import com.cocos.develop.coshub.databinding.FragmentProfileBinding
import com.cocos.develop.coshub.domain.GithubUser
import com.cocos.develop.coshub.ui.common.BackButtonListener
import com.cocos.develop.coshub.ui.utils.app
import com.cocos.develop.coshub.ui.utils.errorMessage
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val ARG_USER = "ARG_USER_LOGIN"

class ProfileFragment : MvpAppCompatFragment(), ProfileView, BackButtonListener {

    companion object {
        fun newInstance(login: String) =
            ProfileFragment().apply { arguments = bundleOf(ARG_USER to login) }
    }

    private val login: String? by lazy {
        arguments?.getString(ARG_USER, "login 1")
    }
    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val presenter: ProfilePresenter by moxyPresenter {
        ProfilePresenter(
            login,
            requireActivity().app
        )
    }
    private var adapter: ProfileAdapter? = null
    private var countLike:Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.repositoriesRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ProfileAdapter(presenter)
        binding.repositoriesRecyclerView.adapter = adapter
        binding.likeButton.setOnClickListener {
            //presenter.onFavoriteClick(it.isEnabled)
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun setUser(user: GithubUser) {
        binding.loginTextView.text = user.login
        binding.likeButton.isEnabled = user.like
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setCountLike() {
        countLike = presenter.setLikeCount(countLike)
        binding.counterTextView.text = countLike.toString()
    }

    override fun showProgressBar() {
        binding.loadingLayout.progressBar.isVisible = true
    }

    override fun hideProgressBar() {
        binding.loadingLayout.progressBar.isVisible = false
    }

    override fun showErrorMessage(message: String?) {
        errorMessage(
            context,
            String.format("%s\n%s", getString(R.string.error_profile), message.toString())
        )
    }

}