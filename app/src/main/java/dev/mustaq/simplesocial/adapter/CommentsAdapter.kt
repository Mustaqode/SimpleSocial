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
import dev.mustaq.simplesocial.model.CommentsDataModel
import kotlinx.android.synthetic.main.model_comments.view.*


/**
Created by Mustaq Sameer on 11/1/21
 **/

class CommentsAdapter(
    private val context: Context,
) : ListAdapter<CommentsDataModel, CommentsAdapter.CommentsViewHolder>(object :
    DiffUtil.ItemCallback<CommentsDataModel>() {
    override fun areItemsTheSame(oldItem: CommentsDataModel, newItem: CommentsDataModel): Boolean =
        oldItem.postId == newItem.postId

    override fun areContentsTheSame(
        oldItem: CommentsDataModel,
        newItem: CommentsDataModel
    ): Boolean =
        oldItem == newItem
}
) {

    private val commentsData: ArrayList<CommentsDataModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder =
        CommentsViewHolder(
            (LayoutInflater.from(parent.context)
                .inflate(R.layout.model_comments, parent, false))
        )

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.comment.text = currentItem.body
        holder.name.text = currentItem.name
        holder.email.text = currentItem.email
    }

    fun updateList(comments: ArrayList<CommentsDataModel>) {
        commentsData.clear()
        commentsData.addAll(comments)
        submitList(commentsData)
        notifyDataSetChanged()
    }

    inner class CommentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val comment: AppCompatTextView = view.uiTvComment
        val name: AppCompatTextView = view.uiTvName
        val email: AppCompatTextView = view.uiTvEmail
    }
}