package com.cocos.develop.coshub.ui.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cocos.develop.coshub.R

/**
 * homework com.cocos.develop.coshub.ui.utils
 *
 * @author Amina
 * 22.10.2021
 */
fun ImageView.setTint(likeCounter: Int): Int {
    return if (likeCounter == 0) {
        this.setColorFilter(ContextCompat.getColor(context, R.color.secondaryColor))
        1
    } else {
        this.setColorFilter(ContextCompat.getColor(context, R.color.gray))
        0
    }
}
fun ImageView.loadInfo(url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.cocos_hub)
        .error(R.drawable.ic_load_error_vector)
        .circleCrop()
        .into(this)
}
