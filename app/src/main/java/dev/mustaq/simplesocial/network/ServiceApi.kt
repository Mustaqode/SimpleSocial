package dev.mustaq.simplesocial.network

import dev.mustaq.simplesocial.model.CommentsModel
import dev.mustaq.simplesocial.model.PostModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


/**
Created by Mustaq Sameer on 10/1/21
 **/

interface ServiceApi {

    @GET("/posts")
    suspend fun getAllPosts() : Response<PostModel>

    @GET("posts/{postId}/comments")
    suspend fun getCommentsOfThePost(@Path(value = "postId") postId: String) : Response<CommentsModel>

}

