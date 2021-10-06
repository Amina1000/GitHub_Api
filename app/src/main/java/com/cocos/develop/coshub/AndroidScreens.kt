package com.cocos.develop.coshub

import com.cocos.develop.coshub.ui.profile.ProfileFragment
import com.cocos.develop.coshub.ui.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * homework com.cocos.develop.coshub
 *
 * @author Amina
 * 05.10.2021
 */
class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun profile(login: String) = FragmentScreen { ProfileFragment.newInstance(login) }
}

interface IScreens {
    fun users(): Screen
    fun profile(login: String): Screen
}