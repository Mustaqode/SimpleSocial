package dev.mustaq.simplesocial.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.mustaq.simplesocial.repository.MiscRepository


/**
Created by Mustaq Sameer on 10/1/21
 **/

typealias fragmentData = Pair<ArrayList<Fragment>, ArrayList<String>>

class MainViewModel(private val miscRepository: MiscRepository) : ViewModel() {

    private val fragmentsListLD = MutableLiveData<fragmentData>()
    private val internetMessageLD = MutableLiveData<String>()

    val fragmentsList: LiveData<fragmentData> = fragmentsListLD
    val internetMessage: LiveData<String> = internetMessageLD

    init {
        fragmentsListLD.value = miscRepository.getListOfFragments()
    }

    fun handleInternetAvailability(isConnected: Boolean) {
        if (!isConnected)
            internetMessageLD.value = ERROR_INTERNET_UNAVAILABLE
    }

    companion object {
        private const val ERROR_INTERNET_UNAVAILABLE = "You are disconnected from the internet!"
    }


}