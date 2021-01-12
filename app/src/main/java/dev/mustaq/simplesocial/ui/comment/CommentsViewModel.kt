package dev.mustaq.simplesocial.ui.comment

import android.content.Intent
import androidx.lifecycle.*
import dev.mustaq.simplesocial.helper.defaultValue
import dev.mustaq.simplesocial.model.NavigationModel
import dev.mustaq.simplesocial.model.PostDataModel
import dev.mustaq.simplesocial.reponsehandler.CustomResponse
import dev.mustaq.simplesocial.repository.PostRepository


/**
Created by Mustaq Sameer on 12/1/21
 **/
class CommentsViewModel(private val postRepository: PostRepository) : ViewModel() {

    private var postDataModel: PostDataModel? = null

    private val addOrRemoveFavouritesLd = MutableLiveData<Boolean>()
    private val errorLd = MutableLiveData<String>()
    private val loaderLd = MutableLiveData<Boolean>()

    val error: LiveData<String> = errorLd
    val loader: LiveData<Boolean> = loaderLd

    val allComments = liveData {
        loaderLd.value = true
        when (val response =
            postRepository.getCommentsForThePost(postDataModel?.id.defaultValue())) {
            is CustomResponse.Success -> emit(response.data)
            is CustomResponse.Failure -> errorLd.value = response.error.message
        }.also {
            loaderLd.value = false
        }
    }

    val addOrRemoveFavourites = addOrRemoveFavouritesLd.switchMap { isAlreadyFav ->
        liveData {
            if (postDataModel != null) {
                if (!isAlreadyFav) {
                    postRepository.addPostToFavourites(postDataModel!!)
                    emit(true)
                } else {
                    postRepository.deletePostFromDb(postDataModel!!)
                    emit(false)
                }
            } else emit(!isAlreadyFav)
        }
    }

    val isFavourite = liveData {
        postDataModel ?: return@liveData
        emit(postRepository.checkWhetherThePostIsInDb(postDataModel!!))
    }

    fun processIntent(intent: Intent?) {
        intent ?: return
        postDataModel = intent.getParcelableExtra(KEY_POST_DATA)
    }

    fun addToFavourites() {
        addOrRemoveFavouritesLd.value = false
    }

    fun removeFromFavourites() {
        addOrRemoveFavouritesLd.value = true
    }

    companion object {
        const val KEY_POST_DATA = "key.post.data"
    }

}