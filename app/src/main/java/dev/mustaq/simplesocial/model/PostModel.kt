package dev.mustaq.simplesocial.model


/**
Created by Mustaq Sameer on 10/1/21
 **/

data class PostModel(
   val postData: ArrayList<PostDataModel>
)

data class PostDataModel(
    val id: Int,
    val title: String,
    val body: String
)
