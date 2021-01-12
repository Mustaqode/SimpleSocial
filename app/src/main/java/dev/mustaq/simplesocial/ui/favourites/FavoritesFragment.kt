package dev.mustaq.simplesocial.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mustaq.simplesocial.R
import dev.mustaq.simplesocial.adapter.PostsAdapter
import dev.mustaq.simplesocial.helper.Trigger
import dev.mustaq.simplesocial.helper.observeLiveData
import dev.mustaq.simplesocial.helper.startActivity
import dev.mustaq.simplesocial.model.PostDataModel
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.fragment_favourites.uiAnimationLoader
import kotlinx.android.synthetic.main.fragment_favourites.uiRvPosts
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
Created by Mustaq Sameer on 10/1/21
 **/

class FavoritesFragment : Fragment() {

    private val postsAdapter by lazy {
        PostsAdapter(
            ::onPostClicked,
            true,
            ::onFavouriteRemoveButtonClicked
        )
    }

    private val favouritesViewModel: FavouritesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favourites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setListeners()
    }

    private fun setupUi() {
        setupRecyclerview()
        favouritesViewModel.navigation.observeLiveData(viewLifecycleOwner) { startActivity(it) }
        favouritesViewModel.loader.observeLiveData(viewLifecycleOwner, ::handleLoaderVisibility)
        favouritesViewModel.noPost.observeLiveData(viewLifecycleOwner, ::handleNoPostUi)
        favouritesViewModel.allPosts.observeLiveData(viewLifecycleOwner, ::updateList)
        favouritesViewModel.removeFavourite.observeLiveData(viewLifecycleOwner, ::removeFavFromList)
    }

    private fun setListeners() {
        uiSwipeRefreshPage.setOnRefreshListener {
            favouritesViewModel.refreshList()
        }
    }

    private fun setupRecyclerview() {
        uiRvPosts.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = postsAdapter
        }
    }

    private fun onPostClicked(postDataModel: PostDataModel) {
        favouritesViewModel.showComments(postDataModel)
    }

    private fun onFavouriteRemoveButtonClicked(postDataModel: PostDataModel, position: Int) {
        favouritesViewModel.removeFromFavourites(postDataModel, position)
    }

    private fun handleNoPostUi(trigger: Trigger) {
        uiTvNoPosts.visibility = View.VISIBLE
    }

    private fun updateList(posts: ArrayList<PostDataModel>) {
        postsAdapter.updateList(posts)
    }

    private fun removeFavFromList(position: Int) {
        postsAdapter.removeAt(position)
    }

    private fun handleLoaderVisibility(isLoading: Boolean) {
        uiAnimationLoader.visibility = if (isLoading) View.VISIBLE else View.GONE
        uiSwipeRefreshPage.isRefreshing = isLoading
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}