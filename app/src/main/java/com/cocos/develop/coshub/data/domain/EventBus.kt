package com.cocos.develop.coshub.data.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject


/**
 * homework com.cocos.develop.coshub
 *
 * @author Amina
 * 20.10.2021
 */
object EventBus {
    open class Event
    private val bus = BehaviorSubject.create<Event>()

    fun post(event: Event){
        bus.onNext(event)
    }

    fun get(): Observable<Event> = bus
}