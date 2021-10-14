package com.cocos.develop.coshub.ui.utils

import android.content.Context
import android.widget.Toast

/**
 * homework com.cocos.develop.coshub.ui.utils
 *
 * @author Amina
 * 14.10.2021
 */
fun errorMessage(context:Context?, message:String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}
