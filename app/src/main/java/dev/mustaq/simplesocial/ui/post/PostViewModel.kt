package dev.mustaq.simplesocial.ui.post

import androidx.lifecycle.*
import dev.mustaq.simplesocial.helper.Trigger
import dev.mustaq.simplesocial.helper.trigger
import dev.mustaq.simplesocial.model.NavigationModel
import dev.mustaq.simplesocial.model.PostDataModel
import dev.mustaq.simplesocial.reponsehandler.CustomResponse
import dev.mustaq.simplesocial.repository.PostRepository

/**
Created by Mustaq Sameer on 10/1/21
 **/
class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val allPostsLd = MutableLiveData<Trigger>()
    private val errorLd = MutableLiveData<String>()
    private val navigationLd = MutableLiveData<NavigationModel>()
    private val loaderLd = MutableLiveData<Boolean>()

    init {
        allPostsLd.trigger()
    }

    val error: LiveData<String> = errorLd
    val navigation: LiveData<NavigationModel> = navigationLd
    val loader: LiveData<Boolean> = loaderLd

    val allPosts = allPostsLd.switchMap {
        loaderLd.value = true
        liveData {
            when (val response = postRepository.getAllPosts()) {
                is CustomResponse.Success -> emit(response.data)
                is CustomResponse.Failure -> errorLd.value = response.error.message
            }.also { loaderLd.value = false }
        }
    }

    fun showComments(postDataModel: PostDataModel) {

    }

    fun removeFromFavourites(id: Int, position: Int) {

    }

}