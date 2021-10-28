package com.cocos.develop.coshub.ui.profile

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.cocos.develop.coshub.databinding.ItemRepositoriesBinding
import com.cocos.develop.coshub.data.UsersRepository
import com.cocos.develop.coshub.ui.utils.setTint

/**
 * homework com.cocos.develop.coshub.ui.profile
 *
 * @author Amina
 * 21.10.2021
 */
class ProfileAdapter(private val presenter: ProfilePresenter) :RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    private val repoList = presenter.userRepoList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRepositoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(repoList[position])

    override fun getItemCount() = repoList.size

    inner class ViewHolder(private val vb: ItemRepositoriesBinding) : RecyclerView.ViewHolder(vb.root) {
        //перепишем при помощи функции расширения
        fun bind(userRepository: UsersRepository) {
            with(vb) {
                itemView.apply {
                    nameTextView.text = userRepository.name.toString()
                    descriptionTextView.text = userRepository.description.toString()
                    imageViewIngBtnAddToFavorites.setTint(userRepository.likeCounter-1)
                    imageViewIngBtnAddToFavorites.setOnClickListener {
                        userRepository.likeCounter =
                            imageViewIngBtnAddToFavorites.setTint(userRepository.likeCounter)
                        presenter.onLikeClick(userRepository.likeCounter)
                    }
                }
                itemView.setOnClickListener {
                    presenter.openUserRepo(userRepository.htmlUrl)
                }
            }

        }
    }
}
