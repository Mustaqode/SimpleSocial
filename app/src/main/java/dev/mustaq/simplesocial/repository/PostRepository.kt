package dev.mustaq.simplesocial.repository

import dev.mustaq.simplesocial.db.AppDb
import dev.mustaq.simplesocial.mapper.CommentsDataMapper
import dev.mustaq.simplesocial.mapper.PostDataMapper
import dev.mustaq.simplesocial.mapper.mapToPostEntity
import dev.mustaq.simplesocial.model.CommentsDataModel
import dev.mustaq.simplesocial.model.PostDataModel
import dev.mustaq.simplesocial.network.ServiceApi
import dev.mustaq.simplesocial.reponsehandler.CustomResponse
import dev.mustaq.simplesocial.reponsehandler.LocalException


/**
Created by Mustaq Sameer on 11/1/21
 **/

class PostRepository(private val serviceApi: ServiceApi, private val db: AppDb) {

    suspend fun getAllPosts(): CustomResponse<ArrayList<PostDataModel>, LocalException> =
        PostDataMapper.map(serviceApi.getAllPosts())

    suspend fun getCommentsForThePost(postId: Int): CustomResponse<ArrayList<CommentsDataModel>, LocalException> =
        CommentsDataMapper.map(serviceApi.getCommentsOfThePost(postId))

    suspend fun addPostToFavourites(postDataModel: PostDataModel)  {
        db.postDao().savePostToDb(postDataModel.mapToPostEntity())
    }

}