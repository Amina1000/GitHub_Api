package com.cocos.develop.coshub.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * homework com.cocos.develop.coshub.data.room
 *
 * @author Amina
 * 01.11.2021
 */
@Database(entities = [GithubUserEntity::class, GithubRepositoryEntity::class], version = 1)
abstract class GithubDatabase :RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
}