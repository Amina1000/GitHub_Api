package com.cocos.develop.coshub.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * homework com.cocos.develop.coshub.domain
 *
 * @author Amina
 * 21.10.2021
 */
@Parcelize
class UsersRepository(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    var likeCounter:Int=0
):Parcelable