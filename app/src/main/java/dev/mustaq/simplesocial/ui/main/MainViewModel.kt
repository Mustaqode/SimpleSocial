package dev.mustaq.simplesocial.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mustaq.simplesocial.repository.MiscRepository
import dev.mustaq.simplesocial.repository.PostRepository
import kotlinx.coroutines.launch


/**
Created by Mustaq Sameer on 10/1/21
 **/

typealias fragmentData = Pair<ArrayList<Fragment>, ArrayList<String>>

class MainViewModel(
    private val miscRepository: MiscRepository,
    private val postRepository: PostRepository
) : ViewModel() {

    private val fragmentsListLD = MutableLiveData<fragmentData>()
    private val internetMessageLD = MutableLiveData<String>()

    val fragmentsList: LiveData<fragmentData> = fragmentsListLD
    val internetMessage: LiveData<String> = internetMessageLD

    init {
        fragmentsListLD.value = miscRepository.getListOfFragments()
    }

    /**
     * Showing toast message when internet is down and also
     * making a dummy sync of fav post to the server when internet is available
     */
    fun handleInternetAvailability(isConnected: Boolean) {
        if (!isConnected)
            internetMessageLD.value = ERROR_INTERNET_UNAVAILABLE
        else {
            viewModelScope.launch {
                postRepository.syncFavToServerDummy()
            }
        }
    }

    companion object {
        private const val ERROR_INTERNET_UNAVAILABLE = "You are disconnected from the internet!"
    }


}