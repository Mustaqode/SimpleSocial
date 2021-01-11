package dev.mustaq.simplesocial.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.mustaq.simplesocial.R
import dev.mustaq.simplesocial.adapter.PostsAdapter
import dev.mustaq.simplesocial.helper.observeLiveData
import dev.mustaq.simplesocial.helper.showError
import dev.mustaq.simplesocial.helper.startActivity
import dev.mustaq.simplesocial.model.PostDataModel
import kotlinx.android.synthetic.main.fragment_posts.*
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
        setupUi()
    }

    private fun setupUi() {
        postViewModel.error.observeLiveData(viewLifecycleOwner) { showError(it) }
        postViewModel.navigation.observeLiveData(viewLifecycleOwner) { startActivity(it) }
        postViewModel.loader.observeLiveData(viewLifecycleOwner, ::handleLoaderVisibility)
        postViewModel.allPosts.observeLiveData(viewLifecycleOwner, ::updateList)
    }

    private fun onPostClicked(postDataModel: PostDataModel) {
        postViewModel.showComments(postDataModel)
    }

    private fun onFavouriteRemoveButtonClicked(id: Int, position: Int) {
        postViewModel.removeFromFavourites(id, position)
    }

    private fun handleLoaderVisibility(isLoading: Boolean) {
        uiAnimationLoader.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun updateList(posts: ArrayList<PostDataModel>) {
        postsAdapter.updateList(posts)
    }

    companion object {
        fun newInstance() = PostFragment()
    }
}