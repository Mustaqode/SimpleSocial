package dev.mustaq.simplesocial.mapper

import dev.mustaq.simplesocial.model.PostDataModel
import dev.mustaq.simplesocial.model.PostModel
import dev.mustaq.simplesocial.reponsehandler.CustomResponse
import dev.mustaq.simplesocial.reponsehandler.LocalException
import retrofit2.Response


/**
Created by Mustaq Sameer on 11/1/21
 **/
object PostDataMapper {

    fun map(response: Response<PostModel>) : CustomResponse<ArrayList<PostDataModel>, LocalException>{

    }
}