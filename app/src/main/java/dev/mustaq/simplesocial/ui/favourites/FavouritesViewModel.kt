package dev.mustaq.simplesocial.ui.favourites

import androidx.core.os.bundleOf
import androidx.lifecycle.*
import dev.mustaq.simplesocial.helper.SingleLiveData
import dev.mustaq.simplesocial.helper.Trigger
import dev.mustaq.simplesocial.helper.trigger
import dev.mustaq.simplesocial.model.NavigationModel
import dev.mustaq.simplesocial.model.PostDataModel
import dev.mustaq.simplesocial.repository.PostRepository
import dev.mustaq.simplesocial.ui.comment.CommentsActivity
import dev.mustaq.simplesocial.ui.comment.CommentsViewModel


/**
Created by Mustaq Sameer on 10/1/21
 **/
class FavouritesViewModel(private val postRepository: PostRepository) : ViewModel() {

    private val allPostsLd = MutableLiveData<Trigger>()
    private val removeFavouriteLd = MutableLiveData<PostDataModel>()
    private val navigationLd = SingleLiveData<NavigationModel>()
    private val loaderLd = MutableLiveData<Boolean>()

    init {
        allPostsLd.trigger()
    }

    val navigation: LiveData<NavigationModel> = navigationLd
    val loader: LiveData<Boolean> = loaderLd

    val allPosts = allPostsLd.switchMap {
        loaderLd.value = true
        liveData {
            postRepository.getAllPostsFromDb().apply {
                emit(this)
            }.also {
                loaderLd.value = false
            }
        }
    }

    val removeFavourite = removeFavouriteLd.switchMap { post ->
        liveData {
            postRepository.deletePostFromDb(post)
            emit(Trigger)
            allPostsLd.trigger()
        }
    }

    fun showComments(postDataModel: PostDataModel) {
        navigationLd.value = NavigationModel(
            CommentsActivity::class.java,
            extras = bundleOf(CommentsViewModel.KEY_POST_DATA to postDataModel)
        )
    }

    fun removeFromFavourites(post: PostDataModel) {
        removeFavouriteLd.value = post
    }

    fun refreshList() {
        allPostsLd.trigger()
    }


}