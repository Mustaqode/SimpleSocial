package dev.mustaq.simplesocial.ui.post

import androidx.core.os.bundleOf
import androidx.lifecycle.*
import dev.mustaq.simplesocial.model.NavigationModel
import dev.mustaq.simplesocial.model.PostDataModel
import dev.mustaq.simplesocial.reponsehandler.CustomResponse
import dev.mustaq.simplesocial.repository.PostRepository
import dev.mustaq.simplesocial.ui.comment.CommentsActivity
import dev.mustaq.simplesocial.ui.comment.CommentsViewModel

/**
Created by Mustaq Sameer on 10/1/21
 **/
class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val errorLd = MutableLiveData<String>()
    private val navigationLd = MutableLiveData<NavigationModel>()
    private val loaderLd = MutableLiveData<Boolean>()

    val error: LiveData<String> = errorLd
    val navigation: LiveData<NavigationModel> = navigationLd
    val loader: LiveData<Boolean> = loaderLd

    val allPosts = liveData {
        loaderLd.value = true
        when (val response = postRepository.getAllPosts()) {
            is CustomResponse.Success -> emit(response.data)
            is CustomResponse.Failure -> errorLd.value = response.error.message
        }.also { loaderLd.value = false }
    }

    fun showComments(postDataModel: PostDataModel) {
        navigationLd.value = NavigationModel(
            CommentsActivity::class.java,
            extras = bundleOf(CommentsViewModel.KEY_POST_DATA to postDataModel)
        )
    }

}