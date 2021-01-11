package dev.mustaq.simplesocial.mapper

import dev.mustaq.simplesocial.model.CommentsDataModel
import dev.mustaq.simplesocial.model.CommentsModel
import dev.mustaq.simplesocial.reponsehandler.CustomResponse
import dev.mustaq.simplesocial.reponsehandler.LocalException
import retrofit2.Response


/**
Created by Mustaq Sameer on 11/1/21
 **/
object CommentsDataMapper {

    fun map(response: Response<CommentsModel>) : CustomResponse<ArrayList<CommentsDataModel>, LocalException> {

    }
}