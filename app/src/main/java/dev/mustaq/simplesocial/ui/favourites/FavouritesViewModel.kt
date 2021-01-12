package dev.mustaq.simplesocial.ui.favourites

import androidx.lifecycle.*
import dev.mustaq.simplesocial.helper.Trigger
import dev.mustaq.simplesocial.helper.trigger
import dev.mustaq.simplesocial.model.NavigationModel
import dev.mustaq.simplesocial.model.PostDataModel
import dev.mustaq.simplesocial.repository.PostRepository


/**
Created by Mustaq Sameer on 10/1/21
 **/
class FavouritesViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val allPostsLd = MutableLiveData<Trigger>()
    private val noPostLd = MutableLiveData<Trigger>()
    private val removeFavouriteLd = MutableLiveData<Pair<PostDataModel, Int>>()
    private val navigationLd = MutableLiveData<NavigationModel>()
    private val loaderLd = MutableLiveData<Boolean>()

    init {
        allPostsLd.trigger()
    }

    val noPost: LiveData<Trigger> = noPostLd
    val navigation: LiveData<NavigationModel> = navigationLd
    val loader: LiveData<Boolean> = loaderLd

    val allPosts = allPostsLd.switchMap {
        loaderLd.value = true
        liveData {
            postRepository.getAllPostsFromDb().apply {
                emit(this)
            }.also {
                loaderLd.value = false
                it.ifEmpty {
                    noPostLd.trigger()
                }
            }
        }
    }

    val removeFavourite = removeFavouriteLd.switchMap { (post, position) ->
        liveData {
            postRepository.deletePostFromDb(post)
            emit(position)
        }
    }

    fun showComments(postDataModel: PostDataModel) {

    }

    fun removeFromFavourites(post: PostDataModel, position: Int) {
        removeFavouriteLd.value = post to position
    }


}