package com.cocos.develop.coshub.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 04.10.2021
 */

@Entity
class GithubUserEntity(
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,
    @PrimaryKey
    val id: Int?,
    @ColumnInfo(name = "login")
    val login: String?,
    @ColumnInfo(name = "organizations_url")
    val organizationsUrl: String?,
    @ColumnInfo(name = "repos_url")
    val reposUrl: String?,
    var like: Boolean = false
)
