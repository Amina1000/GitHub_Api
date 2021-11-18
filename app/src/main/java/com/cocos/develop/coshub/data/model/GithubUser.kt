package com.cocos.develop.coshub.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import com.squareup.moshi.Json


/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 04.10.2021
 */

@Parcelize
data class GithubUser(
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "login")
    val login: String?,
    @Json(name = "organizations_url")
    val organizationsUrl: String?,
    @Json(name = "repos_url")
    val reposUrl: String?,
    var like: Boolean = false,
    var countLike:Int = 0
) : Parcelable
