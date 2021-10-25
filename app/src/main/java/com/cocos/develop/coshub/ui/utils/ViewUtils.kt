package com.cocos.develop.coshub.ui.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
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
