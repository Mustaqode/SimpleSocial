package dev.mustaq.simplesocial.ui.comment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mustaq.simplesocial.R
import dev.mustaq.simplesocial.adapter.CommentsAdapter
import dev.mustaq.simplesocial.helper.observeLiveData
import dev.mustaq.simplesocial.helper.showToast
import dev.mustaq.simplesocial.model.CommentsDataModel
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_comments.uiAnimationLoader
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
Created by Mustaq Sameer on 12/1/21
 **/
class CommentsActivity : AppCompatActivity() {

    private val commentsViewModel: CommentsViewModel by viewModel()
    private val commentsAdapter by lazy { CommentsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        commentsViewModel.processIntent(intent)
        setupUi()
        setListeners()
    }

    private fun setupUi() {
        setupRecyclerView()
        commentsViewModel.error.observeLiveData(this) { showToast(it) }
        commentsViewModel.loader.observeLiveData(this, ::handleLoaderVisibility)
        commentsViewModel.allComments.observeLiveData(this, ::updateList)
        commentsViewModel.addOrRemoveFavourites.observeLiveData(this, ::handleFavButton)
        commentsViewModel.isFavourite.observeLiveData(this, ::handleFavButton)
    }

    private fun setListeners() {
        uiIvFav.setOnClickListener { commentsViewModel.addToFavourites() }
        uiIvRemoveFav.setOnClickListener { commentsViewModel.removeFromFavourites() }
    }

    private fun setupRecyclerView() {
        uiRvComments.apply {
            layoutManager = LinearLayoutManager(this@CommentsActivity)
            adapter = commentsAdapter
        }
    }

    private fun handleFavButton(isAlreadyFav: Boolean) {
        if (isAlreadyFav) {
            uiIvRemoveFav.visibility = View.VISIBLE
            uiIvFav.visibility = View.GONE
        } else {
            uiIvRemoveFav.visibility = View.GONE
            uiIvFav.visibility = View.VISIBLE
        }
    }

    private fun handleLoaderVisibility(isLoading: Boolean) {
        uiAnimationLoader.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun updateList(comments: ArrayList<CommentsDataModel>) {
        commentsAdapter.updateList(comments)
    }
}