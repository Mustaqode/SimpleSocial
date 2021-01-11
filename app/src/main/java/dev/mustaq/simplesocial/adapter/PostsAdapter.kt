package dev.mustaq.simplesocial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.mustaq.simplesocial.R
import dev.mustaq.simplesocial.model.PostDataModel
import kotlinx.android.synthetic.main.model_posts.view.*


/**
Created by Mustaq Sameer on 11/1/21
 **/
class PostsAdapter(
    val context: Context,
    val onPostClick: (PostDataModel) -> Unit
) : ListAdapter<PostDataModel, PostsAdapter.PostsViewHolder>( object :
DiffUtil.ItemCallback<PostDataModel>(){
    override fun areItemsTheSame(oldItem: PostDataModel, newItem: PostDataModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PostDataModel, newItem: PostDataModel): Boolean =
        oldItem == newItem
}
) {

    private val postsData : ArrayList<PostDataModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder =
        PostsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.model_posts, parent, false)
        )

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.title.text = currentItem.title
        holder.post.text = currentItem.body
    }

    fun updateList(comments: ArrayList<PostDataModel>) {
        postsData.clear()
        postsData.addAll(comments)
        submitList(postsData)
        notifyDataSetChanged()
    }

    inner class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title : AppCompatTextView = view.uiTvTitle
        val post : AppCompatTextView = view.uiTvBody

        init {
            view.setOnClickListener {
                onPostClick.invoke(currentList[adapterPosition])
            }
        }
    }
}