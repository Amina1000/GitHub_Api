package com.cocos.develop.coshub.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import com.squareup.moshi.Json


/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 21.10.2021
 */

@Parcelize
class UsersRepository(
    @Json(name = "html_url")
    val htmlUrl: String?,
    @Json(name = "id")
    val id: Int?,
    var userId: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "description")
    val description: String?,
    var likeCounter:Int=0
):Parcelable
