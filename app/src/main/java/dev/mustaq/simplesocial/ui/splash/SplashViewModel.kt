package dev.mustaq.simplesocial.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mustaq.simplesocial.model.NavigationModel
import dev.mustaq.simplesocial.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
Created by Mustaq Sameer on 12/1/21
 **/
class SplashViewModel : ViewModel() {

    private val navigationLd = MutableLiveData<NavigationModel>()

    val navigation = navigationLd

    init {
        viewModelScope.launch {
            delay(3000)
            navigationLd.value = NavigationModel(
                MainActivity::class.java,
                finishCurrent = true
            )
        }
    }
}