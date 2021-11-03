package com.cocos.develop.coshub.data.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.coshub.data.domain
 *
 * @author Amina
 * 01.11.2021
 */
interface INetworkStatus {
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}