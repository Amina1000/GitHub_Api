package com.cocos.develop.coshub.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cocos.develop.coshub.databinding.ItemRepositoriesBinding
import com.cocos.develop.coshub.domain.UsersRepository
import com.cocos.develop.coshub.ui.utils.setTint

/**
 * homework com.cocos.develop.coshub.ui.profile
 *
 * @author Amina
 * 21.10.2021
 */
class ProfileAdapter(private val repoList:List<UsersRepository>) :RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

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
                    nameTextView.text = userRepository.name
                    descriptionTextView.text = userRepository.description
                    imageViewIngBtnAddToFavorites.setTint(userRepository.likeCounter-1)
                    imageViewIngBtnAddToFavorites.setOnClickListener {
                        userRepository.likeCounter =
                            imageViewIngBtnAddToFavorites.setTint(userRepository.likeCounter)
                    }
                }
            }

        }
    }
}
