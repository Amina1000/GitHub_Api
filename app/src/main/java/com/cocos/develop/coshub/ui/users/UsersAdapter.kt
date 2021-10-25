package com.cocos.develop.coshub.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cocos.develop.coshub.databinding.ItemUserBinding
import com.cocos.develop.coshub.domain.UserItemView
import com.cocos.develop.coshub.domain.UserListPresenter

/**
 * homework com.cocos.develop.coshub.ui.users
 *
 * @author Amina
 * 05.10.2021
 */
class UsersAdapter(private val presenter: UserListPresenter) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            loginTextView.text = text
        }
    }

}