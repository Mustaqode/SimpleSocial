package dev.mustaq.simplesocial.reponsehandler


/**
Created by Mustaq Sameer on 11/1/21
 **/

sealed class CustomResponse<out V, out E> {

    data class Success<out V>(val data: V) : CustomResponse<V, Nothing>()

    data class Failure<out E>(val error: E) : CustomResponse<Nothing, E>()

}