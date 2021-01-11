package dev.mustaq.simplesocial.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.mustaq.simplesocial.R
import dev.mustaq.simplesocial.adapter.PostsAdapter
import dev.mustaq.simplesocial.model.PostDataModel
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
Created by Mustaq Sameer on 10/1/21
 **/
class PostFragment : Fragment() {

    private val postsAdapter by lazy {
        PostsAdapter(
            requireContext(),
            ::onPostClicked,
            onFavouriteRemoveButtonClick = ::onFavouriteRemoveButtonClicked
        )
    }
    private val postViewModel: PostViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_posts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun onPostClicked(postDataModel: PostDataModel) {
        postViewModel.showComments(postDataModel)
    }

    private fun onFavouriteRemoveButtonClicked(id: Int, position: Int) {
        postViewModel.removeFromFavourites(id, position)
    }

    companion object {
        fun newInstance() = PostFragment()
    }
}