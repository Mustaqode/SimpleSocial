package dev.mustaq.simplesocial.mapper

import dev.mustaq.simplesocial.constants.ERROR_SERVER
import dev.mustaq.simplesocial.model.PostDataModel
import dev.mustaq.simplesocial.reponsehandler.CustomResponse
import dev.mustaq.simplesocial.reponsehandler.LocalException
import retrofit2.Response


/**
Created by Mustaq Sameer on 11/1/21
 **/
object PostDataMapper {

    fun map(response: Response<ArrayList<PostDataModel>>) : CustomResponse<ArrayList<PostDataModel>, LocalException>{
        return if (response.isSuccessful && response.code() == 200){
            CustomResponse.Success(response.body() ?: arrayListOf())
        } else CustomResponse.Failure(LocalException(ERROR_SERVER))
    }
}