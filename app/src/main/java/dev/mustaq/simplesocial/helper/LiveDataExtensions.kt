package dev.mustaq.simplesocial.helper

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


/**
Created by Mustaq Sameer on 10/01/21
 **/

fun <T> LiveData<T>.observeLiveData(lifecycleOwner: LifecycleOwner, function: (T) -> Unit) {
    this.observe(lifecycleOwner, Observer {
        if (it != null)
            function.invoke(it)
    })
}