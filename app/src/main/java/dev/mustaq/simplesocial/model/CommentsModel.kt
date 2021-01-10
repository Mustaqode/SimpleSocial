package dev.mustaq.simplesocial.model


/**
Created by Mustaq Sameer on 10/1/21
 **/

data class CommentsModel(
    val commentsDataModel: ArrayList<CommentsDataModel>
)

data class CommentsDataModel(
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)