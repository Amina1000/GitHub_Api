package com.cocos.develop.coshub.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * homework com.cocos.develop.coshub.domain
 *
 * @author Amina
 * 04.10.2021
 */
@Parcelize
data class GithubUser(
    var login: String,
    var like: Boolean = false
) : Parcelable
