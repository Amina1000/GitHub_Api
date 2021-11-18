package com.cocos.develop.coshub.data.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.data.room
 *
 * @author Amina
 * 01.11.2021
 */
@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(repo: GithubRepositoryEntity): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg repos: GithubRepositoryEntity):Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(repos: List<GithubRepositoryEntity>):Completable

    @Update
    fun update(repo: GithubRepositoryEntity):Completable

    @Update
    fun update(vararg repos: GithubRepositoryEntity):Completable

    @Update
    fun update(repos: List<GithubRepositoryEntity>):Completable

    @Delete
    fun delete(repo: GithubRepositoryEntity):Completable

    @Delete
    fun delete(vararg repos: GithubRepositoryEntity):Completable

    @Delete
    fun delete(repos: List<GithubRepositoryEntity>):Completable

    @Query("SELECT * FROM GithubRepositoryEntity")
    fun getAll(): Single<List<GithubRepositoryEntity>>

    @Query("SELECT * FROM GithubRepositoryEntity WHERE userId = :userId")
    fun findForUser(userId: String): Single<List<GithubRepositoryEntity>>

}