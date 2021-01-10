package dev.mustaq.simplesocial.model

import android.os.Bundle


/**
Created by Mustaq Sameer on 10/1/21
 **/

data class NavigationModel(
    val clazz: Class<*>,
    val finishCurrent: Boolean = false,
    val extras: Bundle? = null
)